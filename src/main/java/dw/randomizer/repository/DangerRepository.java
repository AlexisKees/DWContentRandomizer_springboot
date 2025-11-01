package dw.randomizer.repository;

import dw.randomizer.model.Danger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DangerRepository extends JpaRepository<Danger,Integer> {
}
