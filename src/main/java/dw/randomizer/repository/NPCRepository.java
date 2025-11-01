package dw.randomizer.repository;

import dw.randomizer.model.NPC;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NPCRepository extends JpaRepository<NPC,Integer> {
}
