package dw.randomizer.repository;

import dw.randomizer.model.Discovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiscoveryRepository extends JpaRepository<Discovery,Integer> {
}
