package br.com.pb.barbershop.msscheduling.framework.adapters;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
public class SchedulingController {


    private final SchedulingService schedulingService;
    
    private final SchedulingUseCase schedulingUseCase;


    @PostMapping()
    public ResponseEntity<SchedulingResponse> createScheduling(@RequestBody @Valid SchedulingDTO scheduling) {

        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.createScheduling(scheduling));
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable) {
        return schedulingService.listSchedulings(schedulingFilter, pageable);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        schedulingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
     }   

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO>findById(@PathVariable Long id){
        return ResponseEntity.ok().body(schedulingUseCase.findById(id));

    }
}


