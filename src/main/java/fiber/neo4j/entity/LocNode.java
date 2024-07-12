package fiber.neo4j.entity;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity("Loc")
public class LocNode {
    private String name;

    public LocNode(String name) {
        this.name = name;
    }

    @Relationship(type = "ROAD")
    private Set<RoadRelationship> roads = new HashSet<>();

    public void addRoadTo(LocNode destination, int cost) {
        RoadRelationship road = new RoadRelationship(cost);
        roads.add(road);
        destination.roads.add(road);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<RoadRelationship> getRoads() {
        return roads;
    }

    public void setRoads(Set<RoadRelationship> roads) {
        this.roads = roads;
    }
}
