package org.mosesidowu.onlinepollingsystem.data.repository;

import org.mosesidowu.onlinepollingsystem.data.models.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollRepository extends JpaRepository<Poll,Long> {

}
