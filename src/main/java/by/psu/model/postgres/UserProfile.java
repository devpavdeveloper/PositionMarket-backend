package by.psu.model.postgres;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_profile")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = {"email", "phone", "priceDistance"})
@ToString(exclude = {"email", "phone", "priceDistance"})
public class UserProfile extends BasicEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "price_distance")
    private Integer priceDistance;

}


