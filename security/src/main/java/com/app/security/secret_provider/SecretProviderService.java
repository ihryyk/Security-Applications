package com.app.security.secret_provider;

import java.util.Optional;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service

public class SecretProviderService {

    private final Cache secretCache;

    public SecretProviderService(CacheManager secretCacheManager) {
        this.secretCache = secretCacheManager.getCache("secrets");
    }

    public String saveSecret(String secretText) {
        Secret secret = new Secret(secretText);
        secretCache.put(secret.uuid(), secret);
        return secret.uuid();
    }

    public String buildSecretUrl(String uuid) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/secret/provider/{uuid}").buildAndExpand(uuid)
                .toUriString();
    }

    public Optional<Secret> getSecret(String uuid) {
        Secret secret = secretCache.get(uuid, Secret.class);
        return Optional.ofNullable(secret);
    }

    public void deleteSecret(String uuid) {
        secretCache.evict(uuid);
    }

}
