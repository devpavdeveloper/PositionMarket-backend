package by.psu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter @Setter
@Table(name = "tags")
public class Tag extends Basic {
    private String ruTitle;
    private String enTitle;
}
