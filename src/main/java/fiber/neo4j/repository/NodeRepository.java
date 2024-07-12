package fiber.neo4j.repository;

import fiber.neo4j.entity.Cable;
import fiber.neo4j.entity.Node;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NodeRepository extends Neo4jRepository<Node, Long> {


}
