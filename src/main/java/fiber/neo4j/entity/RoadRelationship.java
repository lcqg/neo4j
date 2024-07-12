package fiber.neo4j.entity;

import org.neo4j.ogm.annotation.RelationshipEntity;

@RelationshipEntity
public class RoadRelationship {
    private int cost;

    public RoadRelationship(int cost) {
        this.cost = cost;
    }

    // Getter and setter methods
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
