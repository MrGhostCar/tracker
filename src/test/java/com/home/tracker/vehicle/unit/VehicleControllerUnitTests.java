package com.home.tracker.vehicle.unit;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.tracker.controller.VehicleController;
import com.home.tracker.dto.CoordinatesDTO;
import com.home.tracker.service.VehicleService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(VehicleController.class)
public class VehicleControllerUnitTests {

  @MockBean VehicleService vehicleService;

  @Autowired MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void givenInputsInvalid_whenUpdateVehiclesCalled_thenBadRequestReturned()
      throws Exception {
    CoordinatesDTO invalidCoords = new CoordinatesDTO(300.0, 300.0);
    UUID testUUID = UUID.randomUUID();

    mockMvc
        .perform(
            post("/vehicle/" + testUUID)
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(invalidCoords)))
        .andExpect(status().isBadRequest());
  }

  @Test
  public void givenInputsValid_whenUpdateVehiclesCalled_thenOkStatusReturned()
          throws Exception {
    CoordinatesDTO validCoords = new CoordinatesDTO(40.0, 50.0);
    UUID testUUID = UUID.randomUUID();

    mockMvc
            .perform(
                    post("/vehicle/" + testUUID)
                            .contentType(APPLICATION_JSON_UTF8)
                            .content(objectMapper.writeValueAsString(validCoords)))
            .andExpect(status().isOk());
  }
}
