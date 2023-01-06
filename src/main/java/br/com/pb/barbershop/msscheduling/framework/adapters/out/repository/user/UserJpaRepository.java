package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.user;

import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);

    Optional<User> findByIdAndProfileName(Long userId,String name);
}
