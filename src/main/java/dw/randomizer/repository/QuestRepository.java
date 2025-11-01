package dw.randomizer.repository;

import dw.randomizer.model.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest,Integer> {
}
