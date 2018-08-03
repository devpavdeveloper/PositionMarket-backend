package by.psu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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

}
