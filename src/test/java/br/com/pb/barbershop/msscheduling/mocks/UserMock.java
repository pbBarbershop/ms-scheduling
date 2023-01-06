package br.com.pb.barbershop.msscheduling.mocks;

import br.com.pb.barbershop.msscheduling.domain.model.user.User;

public class UserMock {

    public static User getUserMock() {
        return User.builder().id(1L).name("João").email("joao@mail.com").phone("55985082020").build();
    }
}
