package br.com.pfhaidar.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pfhaidar.gestao_vagas.exceptions.UserNotFoundException;
import br.com.pfhaidar.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.pfhaidar.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;

@Service
public class ProfileCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UserNotFoundException();
                });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
            .description(candidate.getDescription())
            .username(candidate.getUsername())
            .email(candidate.getEmail())
            .name(candidate.getName())
            .id(candidate.getId())
            .build();
        return candidateDTO;
    }
}
