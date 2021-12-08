package com.br.email.adapters.out.persistence;

import com.br.email.adapters.out.persistence.entities.EmailEntity;
import com.br.email.core.domain.Email;
import com.br.email.core.domain.PageInfo;
import com.br.email.core.ports.out.EmailRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Primary
public class MysqlEmailRepository implements EmailRepositoryPort {

    private final SpringDataMysqlEmailRepository emailRepository;

    public MysqlEmailRepository(final SpringDataMysqlEmailRepository orderRepository) {
        this.emailRepository = orderRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Email save(Email email) {
        EmailEntity emailEntity = emailRepository.save(modelMapper.map(email, EmailEntity.class));
        return modelMapper.map(emailEntity, Email.class);
    }

    @Override
    public List<Email> findAll(PageInfo pageInfo) {
        Pageable pageable = PageRequest.of(pageInfo.getPageNumber(), pageInfo.getPageSize());
        return emailRepository.findAll(pageable).stream().map(entity -> modelMapper.map(entity, Email.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Email> findById(UUID emailId) {
        Optional<EmailEntity> emailEntity = emailRepository.findById(emailId);
        if (emailEntity.isPresent()) {
            return Optional.of(modelMapper.map(emailEntity.get(), Email.class));
        } else {
            return Optional.empty();
        }
    }
}
