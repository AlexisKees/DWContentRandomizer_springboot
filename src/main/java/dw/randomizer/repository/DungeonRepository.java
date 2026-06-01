package dw.randomizer.repository;

import dw.randomizer.model.Dungeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DungeonRepository extends JpaRepository<Dungeon,Integer> {
}
