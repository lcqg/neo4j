package fiber.neo4j.repository;

import fiber.neo4j.entity.Cable;
import fiber.neo4j.entity.LocNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocNodeRepository extends Neo4jRepository<LocNode, Long> {


}
