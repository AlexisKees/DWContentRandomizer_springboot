package dw.randomizer.repository;

import dw.randomizer.model.NPC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NPCRepository extends JpaRepository<NPC,Integer> {
}
