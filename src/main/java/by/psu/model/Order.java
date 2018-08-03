package by.psu.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class Order {
    public Order() {
    }

    public Order(String fullname, String lastname, String company, String address, String postcode, Date date, String phoneNumber, String email, String comments) {
        this.fullname = fullname;
        this.lastname = lastname;
        this.company = company;
        this.address = address;
        this.postcode = postcode;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.comments = comments;
    }

    private String fullname;
    private String lastname;
    private String company;
    private String address;
    private String postcode;
    private Date date;
    private String phoneNumber;
    private String email;
    private String comments;
    private Double total;
    private List<String> attractions;

    @Override
    public String toString() {
        return "Заказ SUMO.BY \n" +
                "Имя: " + fullname + '\n' +
                "Фамилия: '" + lastname + '\n' +
                "Компания: " + company + '\n' +
                "Адрес: " + address + '\n' +
                "Почтовый индекс: " + postcode + '\n' +
                "Дата: " + date + '\n' +
                "Телефонный номер: " + phoneNumber + '\n' +
                "Email: " + email + '\n' +
                "Коомментарий: " + comments + '\n' +
                "Итог по цене: " + total + '\n' +
                "Аттракционы: " + attractions.toString();
    }
}
