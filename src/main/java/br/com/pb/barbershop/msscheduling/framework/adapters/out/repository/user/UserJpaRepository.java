package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.user;

import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
}
