package com.app.security.secret_provider;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequiredArgsConstructor
@Controller
@RequestMapping("/secret/provider")
public class SecretProviderController {

    private final SecretProviderService secretProviderService;

    @GetMapping
    public String index() {
        return "secret_index";
    }

    @PostMapping("/submit")
    public String submitSecret(@RequestParam("secret") String secret, Model model) {
        String savedSecret = secretProviderService.saveSecret(secret);
        String url = secretProviderService.buildSecretUrl(savedSecret);
        model.addAttribute("link", url);
        return "secret_result";
    }

    @GetMapping("/{uuid}")
    public String getSecret(@PathVariable String uuid, Model model) {
        Optional<Secret> secret = secretProviderService.getSecret(uuid);
        if (secret.isPresent()) {
            model.addAttribute("secret", secret.get().secretMessage());
            secretProviderService.deleteSecret(uuid);
            return "secret";
        }
        return "secret_error";
    }

}
