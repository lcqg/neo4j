package fiber.binlog4j;

import com.gitee.Jmysy.binlog4j.core.BinlogEvent;
import com.gitee.Jmysy.binlog4j.core.IBinlogEventHandler;
import com.gitee.Jmysy.binlog4j.springboot.starter.annotation.BinlogSubscriber;
import fiber.neo4j.entity.FacilityStage;
import fiber.neo4j.entity.Fiber;
import fiber.neo4j.entity.RoutePoint;
import fiber.neo4j.repository.FiberRepository;
import fiber.neo4j.repository.RoutePointRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@BinlogSubscriber(clientName = "master")
@Component
public class FiberEventHandler implements IBinlogEventHandler<Fiber> {

    @Resource
    private FiberRepository fiberRepository;

    @Resource
    private RoutePointRepository routePointRepository;

    @Override
    public void onInsert(BinlogEvent<Fiber> event) {
        Fiber fiber = event.getData();
        if (fiber.getFromStationId() != null && fiber.getToStationId() != null) {
            fiber.setUuid(fiber.getId());
            Map<Long, RoutePoint> routePointMap = routePointRepository.findByUuids(Arrays.asList(fiber.getFromStationId(), fiber.getToStationId()))
                    .stream().collect(Collectors.toMap(RoutePoint::getUuid, it -> it));
            List<RoutePoint> routePoints = new ArrayList<>();
            if (routePointMap != null && routePointMap.get(fiber.getFromStationId()) != null) {
                routePoints.add(routePointMap.get(fiber.getFromStationId()));
            } else {
                RoutePoint from = new RoutePoint();
                from.setUuid(fiber.getFromStationId());
                routePoints.add(from);
            }
            if (routePointMap != null && routePointMap.get(fiber.getToStationId()) != null) {
                routePoints.add(routePointMap.get(fiber.getToStationId()));
            } else {
                RoutePoint to = new RoutePoint();
                to.setUuid(fiber.getToStationId());
                routePoints.add(to);
            }
            fiber.setRoutePointTests(routePoints);
            fiberRepository.save(fiber);
        }
    }

    @Override
    public void onUpdate(BinlogEvent<Fiber> event) {
        Fiber fiber = event.getData();
        Fiber oldFiber = fiberRepository.findByUUID(fiber.getId());
        if (oldFiber != null) {
            fiberRepository.delete(oldFiber);
        }
        if (fiber.getFromStationId() != null && fiber.getToStationId() != null) {
            fiber.setUuid(fiber.getId());
            Map<Long, RoutePoint> routePointMap = routePointRepository.findByUuids(Arrays.asList(fiber.getFromStationId(), fiber.getToStationId()))
                    .stream().collect(Collectors.toMap(RoutePoint::getUuid, it -> it));
            List<RoutePoint> routePoints = new ArrayList<>();
            if (routePointMap != null && routePointMap.get(fiber.getFromStationId()) != null) {
                routePoints.add(routePointMap.get(fiber.getFromStationId()));
            } else {
                RoutePoint from = new RoutePoint();
                from.setUuid(fiber.getFromStationId());
                from.setExist(FacilityStage.UNDER_RUNNING);
                routePoints.add(from);
            }
            if (routePointMap != null && routePointMap.get(fiber.getToStationId()) != null) {
                routePoints.add(routePointMap.get(fiber.getToStationId()));
            } else {
                RoutePoint to = new RoutePoint();
                to.setUuid(fiber.getToStationId());
                to.setExist(FacilityStage.UNDER_RUNNING);
                routePoints.add(to);
            }
            fiber.setRoutePointTests(routePoints);
            fiberRepository.save(fiber);
        }
    }

    @Override
    public void onDelete(BinlogEvent<Fiber> event) {
        Fiber fiber = event.getData();
        fiber.setUuid(fiber.getId());
        RoutePoint from = new RoutePoint();
        from.setUuid(fiber.getFromStationId());
        RoutePoint to = new RoutePoint();
        to.setUuid(fiber.getToStationId());
        fiber.setRoutePointTests(Arrays.asList(from, to));
        fiberRepository.delete(fiber);
    }

    @Override
    public boolean isHandle(String database, String table) {
        return database.equals("fiber") && table.equals("fiber");
    }

}
