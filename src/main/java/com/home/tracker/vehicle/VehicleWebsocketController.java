package com.home.tracker.vehicle;

import com.home.tracker.vehicle.dto.VehicleResponseDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class VehicleWebsocketController {

    @MessageMapping("/position")
    @SendTo("/topic/vehicle")
    public VehicleResponseDTO broadcastPosition(VehicleResponseDTO vehicle) {
        return vehicle;
    }

    @MessageMapping("/vehicle/position")
    @SendTo("/topic/vehicle")
    public String broadcastPosition(String message) {
        return message;
    }

}
