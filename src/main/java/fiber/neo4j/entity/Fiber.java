package fiber.neo4j.entity;

import lombok.Data;
import org.neo4j.ogm.annotation.*;

import java.util.List;


@NodeEntity
@Data
public class Fiber {
    @Id
    private Long uuid;
    @Transient
    private Long id;
    private String name;
    private FacilityStage exists;
    private Long fromStationId;
    private Long toStationId;
    private String nameId;
    @Relationship("CONNECTED_TO")
    private List<RoutePoint> routePointTests;
}
