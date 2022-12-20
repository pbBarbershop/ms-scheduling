package br.com.pb.barbershop.msscheduling.framework.adapters;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    /*
    @GetMapping("/{SchedulingId}")
    public Scheduling getSchedulingDetails(String schedulingId) {
       return scheduling;
    }
    */

    // Todos os detalhes do agendamento no banco de dados
    @GetMapping("/scheduling")
    public ResponseEntity<List<Scheduling>> getAllSchedulingDetails() {
        return ResponseEntity.ok(schedulingService.getAllSchedulings());
    }

    // @PostMapping("/")
    // public SchedulingResponseDTO createSchedulingDetails(@RequestBody Scheduling scheduling) {
    //     this.scheduling = scheduling;
    //     return "Scheduling Updated Successfully";
    // }
    //
    // @PutMapping("/")
    // public String updateSchedulingDetails(@RequestBody Scheduling scheduling) {
    //     this.scheduling = scheduling;
    //     return "Scheduling Updated Successfully";
    // }
    //
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
     public void delete(@PathVariable("id") Long id) {
        schedulingService.delete(id);

     }


}


