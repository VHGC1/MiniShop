package br.com.vitor.minishop.controller;

import br.com.vitor.minishop.service.UploadImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequiredArgsConstructor
@RolesAllowed({"ROLE_BACKOFFICE_USER", "ROLE_ADMIN_USER"})
public class UpdateImageController {
    private final UploadImageService uploadImageService;

    @GetMapping(value = "/requestURL/")
    public ResponseEntity requestURL(@RequestParam String imageName) {
        String urlRequested = uploadImageService.getPressignedURL(imageName);

        return ResponseEntity.ok(urlRequested);
    }
}
