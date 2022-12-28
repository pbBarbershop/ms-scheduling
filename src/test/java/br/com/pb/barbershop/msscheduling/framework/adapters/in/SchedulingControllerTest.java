package br.com.pb.barbershop.msscheduling.framework.adapters.in;

import br.com.pb.barbershop.msscheduling.aplication.ports.in.SchedulingUseCase;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.domain.model.Scheduling;
import br.com.pb.barbershop.msscheduling.framework.adapters.SchedulingController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@WebMvcTest(controllers = SchedulingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class SchedulingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ID_URL = "/scheduling/1";

    private Scheduling scheduling;

    @MockBean
    private SchedulingUseCase schedulingService;

    @Test
    public void SchedulingControllerShouldUpdateSchedulingAndThenReturnScheduling() throws Exception {
        SchedulingDTO schedulingDTO = getSchedulingDTO();

        when(schedulingService.update(any(), any())).thenReturn(scheduling);

        String input = objectMapper.writeValueAsString(schedulingDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.put(ID_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(input)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    public SchedulingDTO getSchedulingDTO() {
        return SchedulingDTO.builder().customerName("theo").customerPhone("55984072019").customerEmail("theoo@mail.com")
                .date(LocalDate.of(2022, 12, 30)).time(LocalTime.of(15, 0)).build();
    }
}
