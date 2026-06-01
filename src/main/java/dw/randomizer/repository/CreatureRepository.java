package dw.randomizer.repository;

import dw.randomizer.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends JpaRepository<Creature,Integer> {
}
