package com.br.email.core.ports.in;

import com.br.email.core.domain.Email;
import com.br.email.core.domain.PageInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailServicePort {

    Email sendEmail(Email email);
    List<Email> findAll(PageInfo pageInfo);
    Optional<Email> findById(UUID emailId);
    Email save(Email email);
}
