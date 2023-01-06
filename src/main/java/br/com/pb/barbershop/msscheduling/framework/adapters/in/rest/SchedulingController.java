package br.com.pb.barbershop.msscheduling.framework.adapters.in.rest;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.PageableDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingFilter;
import br.com.pb.barbershop.msscheduling.domain.model.scheduling.Scheduling;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Scheduling")
@RestController
@RequestMapping("/scheduling")
@RequiredArgsConstructor
public class SchedulingController {

    private final SchedulingUseCase schedulingService;

    @Operation(summary = "Cadastrar agendamento")
    @PostMapping
    public ResponseEntity<SchedulingDTO> create(@RequestBody @Valid SchedulingDTO scheduling) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.create(scheduling));
    }

    @Operation(summary = "Listar agendamentos")
    @GetMapping
    public ResponseEntity<PageableDTO> findAll(SchedulingFilter schedulingFilter, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.schedulingService.findAll(schedulingFilter, pageable));
    }

    @Operation(summary = "Buscar agendamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<SchedulingDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(schedulingService.findById(id));
    }

    @Operation(summary = "Atualizar agendamento")
    @PutMapping("/{id}")
    public ResponseEntity<SchedulingDTO> update(@PathVariable Long id, @RequestBody @Valid SchedulingDTO request) {
        return ResponseEntity.ok().body(schedulingService.update(id, request));
    }

    @Operation(summary = "Excluir agendamento")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        schedulingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
