package org.mosesidowu.onlinepollingsystem.services.voteService;

import org.mosesidowu.onlinepollingsystem.dtos.responses.VoteResponseDTO;

public interface IVoteService {

    VoteResponseDTO castVote(Long pollId, Long optionId, Long userId);

    boolean hasUserVoted(Long pollId, Long userId);

}
