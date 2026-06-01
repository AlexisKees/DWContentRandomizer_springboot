package dw.randomizer.repository;

import dw.randomizer.model.AreaDanger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaDangerRepository extends JpaRepository<AreaDanger,Integer> {
}
