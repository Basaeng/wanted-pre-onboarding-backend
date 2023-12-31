package backend.preonboarding.wanted.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "recruitmentId", referencedColumnName = "id")
    private Recruitment recruitment = null;
}
