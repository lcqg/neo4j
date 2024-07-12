package fiber.binlog4j;

import com.gitee.Jmysy.binlog4j.core.BinlogEvent;
import com.gitee.Jmysy.binlog4j.core.IBinlogEventHandler;
import com.gitee.Jmysy.binlog4j.springboot.starter.annotation.BinlogSubscriber;
import fiber.neo4j.entity.RoutePoint;
import fiber.neo4j.repository.RoutePointRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@BinlogSubscriber(clientName = "master")
@Component
public class RoutePointEventHandler implements IBinlogEventHandler<RoutePoint> {

    @Resource
    private RoutePointRepository routePointRepository;

    @Override
    public void onInsert(BinlogEvent<RoutePoint> event) {
        RoutePoint routePoint = event.getData();
        routePoint.setUuid(routePoint.getId());
        routePointRepository.save(routePoint);
    }

    @Override
    public void onUpdate(BinlogEvent<RoutePoint> event) {
        RoutePoint routePoint = event.getData();
        routePoint.setUuid(routePoint.getId());
        routePointRepository.save(routePoint);
    }

    @Override
    public void onDelete(BinlogEvent<RoutePoint> event) {
        RoutePoint routePoint = event.getData();
        routePoint.setUuid(routePoint.getId());
        routePointRepository.delete(routePoint);
    }

    @Override
    public boolean isHandle(String database, String table) {
        return database.equals("fiber") && table.equals("route_point");
    }

}
