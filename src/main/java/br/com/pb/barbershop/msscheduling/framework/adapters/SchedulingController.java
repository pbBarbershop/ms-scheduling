package br.com.pb.barbershop.msscheduling.framework.adapters;


import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scheduling")
public class SchedulingController {

    private final SchedulingService schedulingService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<SchedulingDTO> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable) {
        return schedulingService.listSchedulings(schedulingFilter, pageable);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        schedulingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

     }


}


