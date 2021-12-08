package com.br.email.core.ports.out;

import com.br.email.core.domain.Email;

public interface SendEmailServicePort {
    void sendEmail(Email email);
}
