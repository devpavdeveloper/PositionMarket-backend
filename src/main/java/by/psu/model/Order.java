package by.psu.model;

import by.psu.utility.Util;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private String firstName;
    private String lastName;
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
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : attractions)
            stringBuilder.append(string + " руб. бел. " + '\n');

        return  "Имя: " + firstName + '\n' +
                "Фамилия: " + lastName + '\n' +
                "Компания: " + company + '\n' +
                "Адрес: " + (Objects.isNull(address) ? "Не назначено" : getAddress()) + '\n' +
                "Почтовый индекс: " + postcode + '\n' +
                "Дата исполнения: " + Util.convertDateToReadableLocalFormat(date, Util.dateFormatTypes.DATE_ONLY) + '\n' +
                "Телефон: " + phoneNumber + '\n' +
                "Email: " + email + '\n' +
                stringBuilder.toString() + '\n' +
                "Комментарий: " + comments + '\n' +
                "Итоговая цена: " + total + " бел. руб.";
    }
}
