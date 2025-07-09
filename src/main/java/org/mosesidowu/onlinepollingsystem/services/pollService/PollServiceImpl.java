package org.mosesidowu.onlinepollingsystem.services.pollService;

import org.mosesidowu.onlinepollingsystem.dtos.requests.PollRequestDTO;
import org.mosesidowu.onlinepollingsystem.dtos.responses.PollResponseDTO;
import org.mosesidowu.onlinepollingsystem.dtos.responses.PollResultDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements IPollService {

    @Override
    public PollResponseDTO createPoll(PollRequestDTO requestDTO) {
        return null;
    }

    @Override
    public PollResponseDTO getPollById(Long pollId) {
        return null;
    }

    @Override
    public List<PollResponseDTO> getAllActivePolls() {
        return null;
    }

    @Override
    public List<PollResponseDTO> getAllPollsByAdmin(Long adminId) {
        return null;
    }

    @Override
    public PollResponseDTO updatePoll(Long pollId, PollRequestDTO requestDTO) {
        return null;
    }

    @Override
    public void deletePoll(Long pollId) {

    }

    @Override
    public PollResultDTO getPollResults(Long pollId) {
        return null;
    }

    @Override
    public void closePoll(Long pollId) {

    }
}
