package com.ssafy.dadok.api.service;

import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public  class EmailService {
    private JavaMailSender javaMailSender;
    private MimeMessage createMessage(String email) throws Exception{
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("Dadok 회원가입 인증 메일입니다.");
        String form = "";
        form+= "<div style='margin:100px;'>";
        form+= "<h1> 안녕하세요 Dadok입니다. </h1>";
        form+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        form+= "<h3 style='color:blue;'>아래 링크를 클릭하시면 인증이 완료됩니다.</h3>";
        form+= "<br>";
        form+= "<a href='http://localhost:8080/user/email/"+email+"'>해당 링크를 클릭해 인증을 완료해주세요!</a>";
        form+= "<div><br/> ";
        form+= "</div>";

        message.setText(form,"utf-8","html");
        message.setFrom("tjdtn6725@naver.com");

        return  message;
    }

    public void sendMail(String email) throws Exception{
        try{
            MimeMessage mimeMessage = createMessage(email);
            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            mailException.printStackTrace();
            throw   new IllegalAccessException();
        }
    }

/*    public long sendCertificationMail(String    email)  throws BaseException {
        if(userProvider.checkEmail(email) == 1){
            throw   new BaseException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
        try{
            String  code = UUID.randomUUID().toString().substring(0, 6);
            sendMail(code, email);

            return  mailDao.createVerificationCode(code, email);
        }catch (Exception exception){
            exception.printStackTrace();
            throw   new BaseException(BaseResponseStatus.DATABASE_ERROR);
        }
    }*/
}

