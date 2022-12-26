package br.com.pb.barbershop.msscheduling.aplication.ports.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.out.SchedulingRepository;
import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;


@Service
@RequiredArgsConstructor
public class SchedulingUseCase implements SchedulingService {

    private final SchedulingRepository repository;
    
    private final ModelMapper mapper;


    public Page<Scheduling> listSchedulings(SchedulingFilter schedulingFilter, Pageable pageable) {
        Scheduling scheduling = Scheduling.builder()
                .id(schedulingFilter.getId())
                .clientName(schedulingFilter.getClientName())
                .clientEmail(schedulingFilter.getClientEmail())
                .clientContact(schedulingFilter.getClientContact())
                .dateScheduling(schedulingFilter.getDateScheduling())
                .build();

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Scheduling> example = Example.of(scheduling, exampleMatcher);

        return repository.findAll(example, pageable);
    }

    private void checkIfIdExists(Long id) {
        repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        checkIfIdExists(id);
        repository.deleteById(id);
    }


    

    @Override
    public SchedulingDTO findById(Long id){
        return mapper.map(getScheduling(id), SchedulingDTO.class);
    }

    public Scheduling getScheduling(Long id){
        Optional<Scheduling> scheduling = repository.findById(id);
        return scheduling.orElseThrow(() -> new ObjectNotFoundException("ID não encontrado!"));
    }


}


