package br.com.pb.barbershop.msscheduling.framework.adapter.in;


import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDto;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.adapter.out.SchedulingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scheduling")
public class SchedulingController {
    private final SchedulingService schedulingService;

    @PostMapping
    public ResponseEntity<Scheduling> create(@RequestBody SchedulingDto schedulingRequest){
       return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.create(schedulingRequest));
    }
}
