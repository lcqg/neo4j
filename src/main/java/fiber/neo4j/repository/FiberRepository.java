package fiber.neo4j.repository;

import fiber.neo4j.entity.Fiber;
import fiber.neo4j.entity.PathResult;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FiberRepository extends Neo4jRepository<Fiber, Long> {

    /**
     * 通过起终点查询光缆
     * @author 不愿透露
     * @date 2024/3/15 14:20
     * @param stationId
     * @return java.util.List<fiber.neo4j.entity.Fiber>
     */
    @Query("MATCH (fiber:Fiber) WHERE fiber.fromStationId = $stationId OR fiber.toStationId = $stationId RETURN fiber")
    List<Fiber> findByFromStationIdOrToStationId(@Param("stationId") Long stationId);

    @Query("MATCH (fiber:Fiber) WHERE fiber.uuid = $uuid RETURN fiber")
    Fiber findByUUID(@Param("uuid") Long uuid);

    @Query("MATCH path=(s:routePoint {resourceId: $fromResourceId})-[:CONNECTS*]->(e:routePoint {resourceId: $toResourceId}) RETURN relationships(path)")
    List<Fiber> findPathBetweenRoutePoints(@Param("fromResourceId") Long fromResourceId, @Param("toResourceId") Long toResourceId);

    @Query("MATCH path = (start:RoutePoint {name: $startName})-[:CONNECTED_TO*]->(end:RoutePoint {name: $endName})" +
            "RETURN nodes(path) AS routePoints, relationships(path) AS fibers")
    List<PathResult> findPath(@Param("startName") String startName, @Param("endName") String endName);

    @Query("MATCH fiber=allshortestPaths((fromStation:RoutePoint{name:$startName})-[*]-(toStation:RoutePoint{name:$endName})) return fiber")
    List<Fiber> findShortestPath(@Param("startName") String startName, @Param("endName") String endName);

    @Query("MATCH (r1:RoutePoint {name: 'R1'}), (r6:RoutePoint {name: 'R6'}), " +
            "path = shortestPath((r1)-[:CONNECTED_TO*]-(r6)) " +
            "RETURN path")
    List<Map<String, Object>> findShortestRoutePath();

}
