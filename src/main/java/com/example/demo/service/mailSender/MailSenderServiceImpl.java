package com.example.demo.service.mailSender;

import com.example.demo.model.entity.MyList;
import com.example.demo.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Override
    public void send(String emailTo, String subject, String message){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(emailTo);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

         mailSender.send(simpleMailMessage);
    }

    @Override
    public void sendScheduledDates(MyList myList, List<User> users){
        for (User user : users) {
            if (!StringUtils.isEmpty(user.getEmail())) {
                String message = String.format(
                        "%s, в Вашем списке %s изменилась планируемая дата покупки на %s .",
                        user.getName(),
                        myList.getName(),
                        myList.getDate_of_purchase()
                );

                send(user.getEmail(), "Изменилась дата", message);
            }
        }
    }

}
