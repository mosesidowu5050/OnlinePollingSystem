package org.mosesidowu.onlinepollingsystem.services;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtServiceImpl implements JwtService {

    private final Set<String> blacklist = new HashSet<>();

    @Override
    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
