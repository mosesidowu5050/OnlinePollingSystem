package org.mosesidowu.onlinepollingsystem.services.pollService;

import org.mosesidowu.onlinepollingsystem.dtos.requests.PollRequestDTO;
import org.mosesidowu.onlinepollingsystem.dtos.responses.PollResponseDTO;
import org.mosesidowu.onlinepollingsystem.dtos.responses.PollResultDTO;

import java.util.List;

public interface IPollService {

    PollResponseDTO createPoll(PollRequestDTO requestDTO);

    PollResponseDTO getPollById(Long pollId);

    List<PollResponseDTO> getAllActivePolls();

    List<PollResponseDTO> getAllPollsByAdmin(Long adminId);

    PollResponseDTO updatePoll(Long pollId, PollRequestDTO requestDTO);

    void deletePoll(Long pollId);

    PollResultDTO getPollResults(Long pollId);

    void closePoll(Long pollId);

}
