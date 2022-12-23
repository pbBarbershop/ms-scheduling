package br.com.pb.barbershop.msscheduling.framework.adapters;


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



@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingUseCase schedulingUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO>findById(@PathVariable Long id){
        return ResponseEntity.ok().body(schedulingUseCase.findById(id));
    }

}


