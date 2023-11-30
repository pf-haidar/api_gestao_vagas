package br.com.pfhaidar.gestao_vagas.modules.candidate.useCases;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import br.com.pfhaidar.gestao_vagas.exceptions.JobNotFoundException;
import br.com.pfhaidar.gestao_vagas.exceptions.UserNotFoundException;
import br.com.pfhaidar.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.pfhaidar.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.pfhaidar.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.pfhaidar.gestao_vagas.modules.candidate.repository.ApplyJobRepository;
import br.com.pfhaidar.gestao_vagas.modules.company.entities.JobEntity;
import br.com.pfhaidar.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

    @InjectMocks
    private ApplyJobCandidateUseCase applyJobCandidateUseCase;

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private JobRepository jobRepository;

    @Mock
    private ApplyJobRepository applyJobRepository;

    @Test
    @DisplayName("Should not be able to apply job with candidate not found")
    public void should_not_be_able_to_apply_job_with_candidate_not_found() {
        try {
            applyJobCandidateUseCase.execute(null, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }

    @Test
    @DisplayName("Should not be able to apply job with job not found")
    public void should_not_be_able_to_apply_job_with_job_not_found() {
        var candidateId = UUID.randomUUID();

        var candidate = new CandidateEntity();
        candidate.setId(candidateId);

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(candidate));

        try {
            applyJobCandidateUseCase.execute(candidateId, null);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(JobNotFoundException.class);
        }
    }

    @Test
    public void should_be_able_to_create_a_new_apply_job() {
        var candidateId = UUID.randomUUID();
        var jobId = UUID.randomUUID();

        var applyJob = ApplyJobEntity.builder()
                .candidateId(candidateId)
                .jobId(jobId)
                .build();

        var applyJobCreated = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

        // applyJob.setId(UUID.randomUUID());

        when(candidateRepository.findById(candidateId)).thenReturn(Optional.of(new CandidateEntity()));
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(new JobEntity()));

        when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreated);

        var result = applyJobCandidateUseCase.execute(candidateId, jobId);
        assertThat(result).hasFieldOrProperty("id");
        assertNotNull(result.getId());
    }
}
