package fiber.neo4j.repository;

import fiber.neo4j.entity.Cable;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CableRepository extends Neo4jRepository<Cable, Long> {


}
