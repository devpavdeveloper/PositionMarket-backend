package by.psu.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public abstract class AbstractDTO implements Serializable {

    private UUID id;

}
