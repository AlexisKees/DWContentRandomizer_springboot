package dw.randomizer.repository;

import dw.randomizer.model.Steading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SteadingRepository extends JpaRepository<Steading,Integer> {
}
