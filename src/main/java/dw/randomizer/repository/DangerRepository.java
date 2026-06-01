package dw.randomizer.repository;

import dw.randomizer.model.Danger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DangerRepository extends JpaRepository<Danger,Integer> {
}
