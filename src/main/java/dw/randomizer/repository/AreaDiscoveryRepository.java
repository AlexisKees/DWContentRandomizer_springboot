package dw.randomizer.repository;

import dw.randomizer.model.AreaDiscovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaDiscoveryRepository extends JpaRepository<AreaDiscovery,Integer> {
}
