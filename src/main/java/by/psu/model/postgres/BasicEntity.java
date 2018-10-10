package by.psu.model.postgres;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter @Setter
public abstract class BasicEntity {

    @Id
    @GenericGenerator(name = "useIdOrGenerate", strategy = "by.psu.model.postgres.generator.CustomUUIDGenerator")
    @GeneratedValue(generator = "useIdOrGenerate")
    @Column(name = "id", nullable = false)
    private UUID id;

}
