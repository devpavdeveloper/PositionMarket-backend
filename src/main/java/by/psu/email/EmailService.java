package by.psu.email;

import by.psu.config.MailConfig;
import by.psu.model.Order;
import by.psu.reporting.HtmlBuilder;
import by.psu.security.model.User;
import by.psu.utility.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Value("static/email_order.html")
    private Resource resource;

    @Autowired
    public EmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    //do not delete
    public void sendSimpleMessage(final Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        message.setTo(mail.getTo());
        message.setFrom(mail.getFrom());
        emailSender.send(message);
    }

    public void sendHtmlMessage(final Mail mail) {

        MailConfig mailConfig = MailConfig.getInstance();
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailConfig.getProperty("mail.username"), mailConfig.getProperty("mail.password"));
            }
        };

        Session session = Session.getDefaultInstance(mailConfig.getPropsInstance(), auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mail.getFrom()));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            message.setSubject(mail.getSubject());
            message.setContent(mail.getContent(), "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException mex) {
            logger.error("MessagingException: ", mex);
            mex.printStackTrace();
        }

    }

    public String createConfirmationString(User user, String token, String appUrl) {

        StringBuilder sb = new StringBuilder();
        sb.append("Уважаемый, " + user.getLogin() + ". Вы можете активировать ваш аккаунт пройдя по ссылке: ");
        sb.append("<a href=\"http://");
        String confirmationUrl
                = appUrl + "/auth/register?token=" + token + "&email=" + user.getEmail();
        //String message = messages.getMessage("message.regSucc", null, event.getLocale()); --do not delete
        sb.append(confirmationUrl + "\">");
        sb.append(confirmationUrl + "</a>");
        return sb.toString();
    }

    public String createPasswordRecoveryString(User user, String token, String appUrl) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ссылка для восстановления пароля: ");
        sb.append("<a href=\"http://");
        String confirmationUrl
                = appUrl + "/auth/account_recovery?token=" + token + "&email=" + user.getEmail();
        //String message = messages.getMessage("message.regSucc", null, event.getLocale()); --do not delete
        sb.append(confirmationUrl + "\">");
        sb.append(confirmationUrl + "</a>");
        return sb.toString();
    }

    public String createOrderEmail(Order order) {
        StringBuilder htmlTemplate = new StringBuilder();
        htmlTemplate.append("<h2>Уважаемый ${user}, на ваше имя был оформлен заказ аттракционов на сайте Sumo.by</h2>\n" +
                "<table border=\"1\" cellpadding=\"5\" style=\"border-collapse: collapse; border: 1px solid #ccc\">\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th style=\"background-color: #2E86C1; color: white;\">Дата исполнения заказа</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <th>${order_date}</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "</table>\n" +
                "<table border=\"1\" cellpadding=\"5\" style=\"border-collapse: collapse; border: 1px solid #ccc;\">\n" +
                "    <caption>\n" +
                "        <h3 style=\"padding: 10px 0px;background-color: #2E86C1; color: white;\">Таблица деталей заказа</h3>\n" +
                "    </caption>\n" +
                "    <thead>\n" +
                "    <tr>\n" +
                "        <th>Заказчик</th>\n" +
                "        <th>Компания</th>\n" +
                "        <th>Адрес доставки</th>\n" +
                "        <th>Почтовый индекс</th>\n" +
                "        <th>Номер телефона</th>\n" +
                "        <th>Email</th>\n" +
                "        <th>Комментарии</th>\n" +
                "        <th>Заказанные аттракционы</th>\n" +
                "        <th>Стоимость, BYN</th>\n" +
                "    </tr>\n" +
                "    </thead>\n" +
                "    ${main_table}\n" +
                "</table>\n" +
                "<h4><span style=\"color: red;\">*</span>\n" +
                "    Если вы не оформляли заказ, то проигнорируйте и удалите данное письмо.</h4>\n");
      /*  try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                htmlTemplate.append(line);
            }*/
            String parsedResult = htmlTemplate.toString().replace("${user}", order.getFirstName() + " " + order.getLastName());
            parsedResult = parsedResult.replace("${order_date}",
                    Util.convertDateToReadableLocalFormat(order.getDate(), Util.dateFormatTypes.DATE_ONLY));
            htmlTemplate = new StringBuilder();
            htmlTemplate.append(parsedResult);
            String mainTable = HtmlBuilder.createOrderTable(order);
            parsedResult = parsedResult.replace("${main_table}", mainTable);
            return parsedResult;
       /* } catch (IOException e) {
            logger.error("createOrderEmail (templateFile): ", e);
        }*/

    }
}
