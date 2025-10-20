package davidemancini.U5_W3_D1.repositories;

import davidemancini.U5_W3_D1.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {
}
