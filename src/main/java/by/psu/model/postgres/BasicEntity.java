package by.psu.model.postgres;

import by.psu.model.postgres.converters.DeserializeUUID;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
public abstract class BasicEntity {

    @Id
    @GenericGenerator(name = "useIdOrGenerate", strategy = "by.psu.model.postgres.generator.CustomUUIDGenerator")
    @GeneratedValue(generator = "useIdOrGenerate")
    @Column(name = "id", nullable = false, unique = true)
    @JsonDeserialize(using = DeserializeUUID.class)
    //@Type(type="pg-uuid")
    private UUID id;

}
