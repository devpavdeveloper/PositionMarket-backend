package by.psu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter @Setter
@Table(name = "attraction")
public class Attraction extends Basic {

    @Column(name = "title_attraction")
    private String titleAttraction;

    @Column(name = "attraction_information_link")
    private String attractionInformationLink;

    @Column(name = "side_image")
    private String sideImage;

    @Column(name = "pickup_service_price")
    private Double pickupServicePrice;

    @Column(name = "delivery_service_price")
    private Double deliveryServicePrice;

    @Column(name = "installation_service_price")
    private Double installationServicePrice;

    @Column(name = "full_service_price")
    private Double fullServicePrice;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "attraction_to_type_attraction",
            joinColumns = @JoinColumn(name = "id_attraction"),
            inverseJoinColumns = @JoinColumn(name = "id_attraction_type"))
    private Set<TypeAttraction> typeAttractions;
}