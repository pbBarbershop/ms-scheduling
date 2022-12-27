package br.com.pb.barbershop.msscheduling.framework.adapters;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
public class SchedulingController {

    private final SchedulingService schedulingService;

    @PutMapping("/{id}")
    public ResponseEntity<Scheduling> update(@PathVariable Long id, @RequestBody @Valid SchedulingDTO request) {
        return ResponseEntity.ok().body(schedulingService.update(id, request));
    }
}
