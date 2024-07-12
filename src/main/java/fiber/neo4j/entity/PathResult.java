package fiber.neo4j.entity;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class PathResult {
    private Fiber fiber;
    private RoutePoint fromStation;
    private RoutePoint toStation;

    public Fiber getFiber() {
        return fiber;
    }

    public void setFiber(Fiber fiber) {
        this.fiber = fiber;
    }

    public RoutePoint getFromStation() {
        return fromStation;
    }

    public void setFromStation(RoutePoint fromStation) {
        this.fromStation = fromStation;
    }

    public RoutePoint getToStation() {
        return toStation;
    }

    public void setToStation(RoutePoint toStation) {
        this.toStation = toStation;
    }

}
