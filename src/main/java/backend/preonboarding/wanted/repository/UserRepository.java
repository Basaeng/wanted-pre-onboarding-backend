package backend.preonboarding.wanted.repository;

import backend.preonboarding.wanted.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
