package com.app.security.service.security;

import java.time.LocalDateTime;
import java.util.Set;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPTS = 3;

    private final Cache loginAttemptsCache;
    private final Cache blockedUsersCache;

    public LoginAttemptService(CacheManager cacheManager) {
        this.loginAttemptsCache = cacheManager.getCache("loginAttempts");
        this.blockedUsersCache = cacheManager.getCache("blockedUsers");
    }

    public void loginSucceeded(String key) {
        loginAttemptsCache.evict(key);
    }

    public void loginFailed(String key) {
        Integer currentAttempts = loginAttemptsCache.get(key, Integer.class);
        int attemptsToUpdate = currentAttempts == null ? 1 : currentAttempts + 1;

        loginAttemptsCache.put(key, attemptsToUpdate);
        System.out.println("Logged a failed attempt for user: " + key + " Total attempts: " + attemptsToUpdate);

        if (attemptsToUpdate >= MAX_ATTEMPTS) {
            blockedUsersCache.put(key, LocalDateTime.now().plusMinutes(5));
            System.out.println("User blocked: " + key);
        }
    }

    public boolean isBlocked(String key) {
        return blockedUsersCache.get(key, LocalDateTime.class) != null;
    }

    public Set<String> getBlockedUsers() {
        com.github.benmanes.caffeine.cache.Cache<String, LocalDateTime> nativeCache =
                (com.github.benmanes.caffeine.cache.Cache<String, LocalDateTime>) blockedUsersCache.getNativeCache();
        return nativeCache.asMap().keySet();
    }

}
