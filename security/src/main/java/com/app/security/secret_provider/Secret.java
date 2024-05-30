package com.app.security.secret_provider;

import java.util.UUID;

public record Secret(String secretMessage, String uuid) {

    public Secret(String secretMessage) {
        this(secretMessage, UUID.randomUUID().toString());
    }

}
