package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryPortOut {
    Optional<User> findById(Long userId);
    Optional<User> findByIdAndProfile(Long userId, String profileName);
}
