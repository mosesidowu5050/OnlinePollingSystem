package org.mosesidowu.onlinepollingsystem.data.repository;

import org.mosesidowu.onlinepollingsystem.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
