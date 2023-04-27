package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.service.HealthCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
@RequiredArgsConstructor
public class HealthCheckController {

    private final HealthCheckService service;

    /**
     * Health Check manual
     * caso queira implementar um healthcheck mais robusto veja o link abaixo
     *
     * @see <a href="https://www.baeldung.com/spring-boot-actuators">Spring Boot Actuator</a>
     */
    @GetMapping
    public ResponseEntity<Void> check() {
        service.check();
        return ResponseEntity.ok().build();
    }
}
