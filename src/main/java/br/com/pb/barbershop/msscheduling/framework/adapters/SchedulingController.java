package br.com.pb.barbershop.msscheduling.framework.adapters;

import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
public class SchedulingController {
    

    private final SchedulingService schedulingService;

    @PostMapping()
    public ResponseEntity<SchedulingResponse> createScheduling(@RequestBody @Valid SchedulingDTO scheduling) {

        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.createScheduling(scheduling));
    }


}


