package dw.randomizer.repository;

import dw.randomizer.model.Dungeon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DungeonRepository extends JpaRepository<Dungeon,Integer> {
}
