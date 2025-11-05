package dw.randomizer.repository;

import dw.randomizer.model.Biome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiomeRepository extends JpaRepository<Biome,Integer> {

}
