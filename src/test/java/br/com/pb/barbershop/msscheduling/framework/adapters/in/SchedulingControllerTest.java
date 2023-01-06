package br.com.pb.barbershop.msscheduling.framework.adapters.in;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import br.com.pb.barbershop.msscheduling.aplication.service.SchedulingService;
import br.com.pb.barbershop.msscheduling.domain.dto.PageableDTO;
import br.com.pb.barbershop.msscheduling.domain.dto.SchedulingDTO;
import br.com.pb.barbershop.msscheduling.framework.adapters.in.rest.SchedulingController;
import br.com.pb.barbershop.msscheduling.mocks.SchedulingMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = SchedulingController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class SchedulingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Spy
    private ObjectMapper objectMapper;

    @MockBean
    private SchedulingService service;

    private static final Long ID = 1L;
    private static final String URL = "/scheduling";
    private static final String ID_URL = "/scheduling/1";

    @Test
    void create() throws Exception {
        SchedulingDTO scheduling = SchedulingMock.getSchedulingDTOMock();

        SchedulingDTO schedulingResponse = SchedulingMock.getSchedulingDTOMock();

        when(service.create(scheduling)).thenReturn(schedulingResponse);
        String json = objectMapper.writeValueAsString(scheduling);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
            .post(URL)
            .accept(MediaType.APPLICATION_JSON)
            .content(json)
            .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    void findAll() throws Exception {
        PageableDTO pageableDTO = new PageableDTO();
        when(service.findAll(any(), any())).thenReturn(pageableDTO);
        MvcResult result = mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get(URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void findById() throws Exception {
        SchedulingDTO schedulingDTO = new SchedulingDTO();
        SchedulingDTO schedulingResponse = new SchedulingDTO();

        when(service.findById(any())).thenReturn(schedulingResponse);
        MvcResult result = mockMvc
            .perform(
                MockMvcRequestBuilders
                    .get(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void update() throws Exception {
        SchedulingDTO schedulingDTO = SchedulingMock.getSchedulingDTOMock();
        SchedulingDTO schedulingResponse = new SchedulingDTO();
        when(service.update(any(), any())).thenReturn(schedulingResponse);
        String json = objectMapper.writeValueAsString(schedulingDTO);
        MvcResult result = mockMvc
            .perform(
                MockMvcRequestBuilders
                    .put(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(json)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    void delete() throws Exception {
        doNothing().when(service).delete(1L);

        MvcResult result = mockMvc
            .perform(
                MockMvcRequestBuilders
                    .delete(ID_URL)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            )
            .andReturn();

        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
    }
}
