package br.com.pfhaidar.gestao_vagas.modules.company.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pfhaidar.gestao_vagas.modules.company.entities.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity, UUID> {

    // Select * from job where description like %filter%

    List<JobEntity> findByDescriptionContainingIgnoreCase(String description);
}
