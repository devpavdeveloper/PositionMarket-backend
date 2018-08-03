package by.psu.email;

import by.psu.config.MailConfig;
import by.psu.reporting.HtmlBuilder;
import by.psu.security.model.User;
import by.psu.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.ResourceUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailService {

    private final JavaMailSender emailSender;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    //do not delete
    public void sendSimpleMessage(final Mail mail){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());
        emailSender.send(message);

    }

    public void sendHtmlMessage(final Mail mail){

        MailConfig mailConfig = MailConfig.getInstance();
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getProperty("mail.username"), mailConfig.getProperty("mail.password"));
            }
        };

        Session session = Session.getDefaultInstance(mailConfig.getPropsInstance(), auth);
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setContent(mail.getContent(), "text/html; charset=utf-8");
            Transport.send(message);
        }
        catch(MessagingException mex){
            mex.printStackTrace();
        }

    }

    public String createConfirmationString(User user, String token, String appUrl){

        StringBuilder sb = new StringBuilder();
        sb.append("Уважаемый, "+user.getLogin()+". Вы можете активировать ваш аккаунт пройдя по ссылке: ");
        sb.append("<a href=\"http://");
        String confirmationUrl
                = appUrl + "/auth/register?token=" + token + "&email=" + user.getEmail();
        //String message = messages.getMessage("message.regSucc", null, event.getLocale()); --do not delete
        sb.append(confirmationUrl+"\">");
        sb.append(confirmationUrl+"</a>");
        return sb.toString();
    }

    public String createPasswordRecoveryString(User user, String token, String appUrl){
        StringBuilder sb = new StringBuilder();
        sb.append("Ссылка для восстановления пароля: ");
        sb.append("<a href=\"http://");
        String confirmationUrl
                = appUrl + "/auth/account_recovery?token=" + token + "&email=" + user.getEmail();
        //String message = messages.getMessage("message.regSucc", null, event.getLocale()); --do not delete
        sb.append(confirmationUrl+"\">");
        sb.append(confirmationUrl+"</a>");
        return sb.toString();
    }

    public String createOrderEmail(){
        StringBuilder htmlTemplate = new StringBuilder();
        String parsedResult = "";
        /*File templateFile = null;
        try{
            templateFile = ResourceUtils.getFile("classpath:email_order.html");
            try(BufferedReader br = new BufferedReader(new FileReader(templateFile))){
                String line = "";
                while((line = br.readLine())!=null){
                    htmlTemplate.append(line);
                }
                parsedResult = htmlTemplate.toString().replace("${order_created_date}", Util.convertDateToReadableLocalFormat(orderIssue.getDateEvent()));
                parsedResult = parsedResult.replace("${user}", orderIssue.getUser().getLogin());
                parsedResult = parsedResult.replace("${order_date}", Util.convertDateToReadableLocalFormat(orderIssue.getDateOrder()));
                htmlTemplate = new StringBuilder();
                htmlTemplate.append(parsedResult);
                String mainTable = HtmlBuilder.createOrderTable(orderIssue, HtmlBuilder.TABLE_TYPES.MAIN_TABLE);
                String totalsTable = HtmlBuilder.createOrderTable(orderIssue, HtmlBuilder.TABLE_TYPES.TOTALS_TABLE);
                parsedResult = parsedResult.replace("${main_table}", mainTable);
                parsedResult = parsedResult.replace("${main_table_totals}", totalsTable);
                return parsedResult;
            }
            catch(IOException e){}
        }
        catch(IOException e){}*/
        return parsedResult;
    }
}
