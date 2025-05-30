package fitloop.common.exception.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import fitloop.common.exception.errorcode.CommonErrorCode;
import fitloop.common.exception.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class FitLoopAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.from(CommonErrorCode.UNAUTHORIZED);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(CommonErrorCode.UNAUTHORIZED.getStatus().value());
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}
