package fiber.neo4j.entity;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Cable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "CONNECTS_TO")
    private Node startPoint;

    @Relationship(type = "CONNECTS_TO")
    private Node endPoint;

    private double dis;

    // 构造函数、Getter和Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Node startPoint) {
        this.startPoint = startPoint;
    }

    public Node getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Node endPoint) {
        this.endPoint = endPoint;
    }

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }
}
