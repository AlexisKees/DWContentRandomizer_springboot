package dw.randomizer.repository;

import dw.randomizer.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatureRepository extends JpaRepository<Creature,Integer> {
}
