package backend.preonboarding.wanted.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private Long id;

    private Long recruitmentId;
}
