package fiber.neo4j.entity;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Transient;

@NodeEntity
@Data
public class RoutePoint {
    @Id
    private Long uuid;
    @Transient
    private Long id;
    private String name;
    private String resourceId;
    private FacilityStage exist;
}
