package org.mosesidowu.onlinepollingsystem.services;

public interface JwtService {

    void blacklistToken(String token);

    boolean isBlacklisted(String token);
}
