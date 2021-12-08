package com.br.email.adapters.out.persistence;

import com.br.email.adapters.out.persistence.entities.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataMysqlEmailRepository extends JpaRepository<EmailEntity, UUID> {
}
