package fiber.neo4j.repository;

import fiber.neo4j.entity.RoutePoint;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutePointRepository extends Neo4jRepository<RoutePoint, Long> {

    @Query("MATCH (rp:RoutePoint) WHERE rp.uuid IN $uuids RETURN rp")
    List<RoutePoint> findByUuids(@Param("uuids") List<Long> uuids);

}
