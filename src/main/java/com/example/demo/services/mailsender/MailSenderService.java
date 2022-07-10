package com.example.demo.services.mailsender;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;

import java.util.List;

public interface MailSenderService {

    void send(String emailTo, String subject, String message);

    void sendScheduledDates(MyList myList, List<User> users);

}
