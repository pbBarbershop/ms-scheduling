package br.com.pb.barbershop.msscheduling.framework.adapters.out.repository.user;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.UserRepositoryPortOut;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import br.com.pb.barbershop.msscheduling.domain.model.user.User;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPortOut {

    private final UserJpaRepository repository;

    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId);
    }
}
