package org.mosesidowu.onlinepollingsystem.data.repository;

import org.mosesidowu.onlinepollingsystem.data.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {

}
