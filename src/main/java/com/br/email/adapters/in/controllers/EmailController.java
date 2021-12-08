package com.br.email.adapters.in.controllers;


import com.br.email.adapters.dtos.EmailDto;
import com.br.email.core.domain.Email;
import com.br.email.core.domain.PageInfo;
import com.br.email.core.ports.in.EmailServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/email")
public class EmailController {

    @Autowired
    EmailServicePort emailServicePort;

    @PostMapping
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        return new ResponseEntity<>(emailServicePort.sendEmail(email), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Email>> getAllEmails(@PageableDefault(page = 0, size = 5, sort = "emailId", direction = Sort.Direction.DESC) Pageable pageable){
        PageInfo pageInfo = new PageInfo();
        BeanUtils.copyProperties(pageable, pageInfo);
        List<Email> emailList = emailServicePort.findAll(pageInfo);
        return new ResponseEntity<>(new PageImpl<Email>(emailList, pageable, emailList.size()), HttpStatus.OK);
    }

    @GetMapping("/{emailId}")
    public ResponseEntity<Object> getOneEmail(@PathVariable(value="emailId") UUID emailId){
        Optional<Email> emailModelOptional = emailServicePort.findById(emailId);
        if(!emailModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found.");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(emailModelOptional.get());
        }
    }
}
