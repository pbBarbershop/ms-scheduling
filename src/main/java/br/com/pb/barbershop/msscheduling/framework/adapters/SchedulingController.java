package br.com.pb.barbershop.msscheduling.framework.adapters;

import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.reponse.SchedulingResponseDTO;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/scheduling")
public class SchedulingController {
    

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping("/{SchedulingId}")
    public Scheduling getSchedulingDetails(String schedulingId) {
        return scheduling;
    }

    // Todos os detalhes do produto no banco de dados
    @GetMapping("/")
    public Scheduling getAllSchedulingDetails() {
        return scheduling;
    }

    @PostMapping("/")
    public SchedulingResponseDTO createSchedulingDetails(@RequestBody Scheduling scheduling) {
        this.scheduling = scheduling;
        return "Scheduling Updated Successfully";
    }

    @PutMapping("/")
    public String updateSchedulingDetails(@RequestBody Scheduling scheduling) {
        this.scheduling = scheduling;
        return "Scheduling Updated Successfully";
    }

    @DeleteMapping("/{SchedulingId}")
    public String deleteSchedulingDetails(String schedulingId) {
        this.scheduling = scheduling;
        return "Scheduling Deleted Successfully";
    }


}


