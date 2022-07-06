package com.example.demo.services.mailsender;

public interface MailSenderService {

    public void send(String emailTo, String subject, String message);

}
