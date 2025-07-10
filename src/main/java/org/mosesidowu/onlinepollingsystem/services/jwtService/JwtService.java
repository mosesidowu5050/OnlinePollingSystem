package org.mosesidowu.onlinepollingsystem.services.jwtService;

public interface JwtService {

    void blacklistToken(String token);

    boolean isBlacklisted(String token);
}
