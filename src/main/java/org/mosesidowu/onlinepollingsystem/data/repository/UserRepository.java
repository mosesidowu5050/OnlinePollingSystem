package org.mosesidowu.onlinepollingsystem.data.repository;

import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUsersByEmail(String email);

    Optional<User> findUsersByOauth2Id(String oauth2Id);

    Optional<User> findById(Long userId);

    User save(User user);
}
