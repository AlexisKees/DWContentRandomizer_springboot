package dw.randomizer.repository;

import dw.randomizer.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Follower,Integer> {
}
