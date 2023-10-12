package backend.preonboarding.wanted.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company = null;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "region", nullable = false)
    private String region;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "reward", nullable = false)
    private int reward;

    @Column(name = "detail", columnDefinition = "LONGTEXT")
    private String detail;

    @Column(name = "tech_stack", nullable = false)
    private String techStack;
}
