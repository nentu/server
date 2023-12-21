package com.example.server.сonfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Deprecated
@Component
public class Http403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        var writer = response.getWriter();
//        writer.println("{\"redirectUrl\": \"http://localhost:8080/realms/web/protocol/openid-connect/auth?client_id=lab4&response_type=code&scope=openid&redirect_uri=http://localhost:5173/auth\"}"); //TODO
//        writer.flush();
//        writer.close();
    }
}