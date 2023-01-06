package br.com.pb.barbershop.msscheduling.aplication.ports.out;

import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryPortOut {
    Optional<User> findById(Long userId);
}
