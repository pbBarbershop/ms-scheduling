package br.com.pb.barbershop.msscheduling;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "MS Scheduling",
                description = "Microsserviço de agendamentos da barbearia",
                version = "1.0.0"
        ),
        servers = @Server(
                url = "http://localhost:8082/api/barbershop"
        )
)
public class MsSchedulingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsSchedulingApplication.class, args);
    }
}
