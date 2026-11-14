package com.lipton.gateway.api;

import com.lipton.gateway.models.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/register")
    public Response register() {
        var eventId = UUID.ofEpochMillis(System.currentTimeMillis());

        return new Response(eventId);
    }
}
