package by.psu.model.postgres;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
@Data
@EqualsAndHashCode(callSuper = true, exclude = {"email", "phone", "priceDistance"})
public class UserProfile extends BasicEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price_distance")
    private Integer priceDistance;
}


