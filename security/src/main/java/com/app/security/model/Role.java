package com.app.security.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Embeddable
public class Role implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

}
