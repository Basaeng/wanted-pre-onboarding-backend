package backend.preonboarding.wanted.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RecruitmentDTO {
    private Long id;
    private Long companyId;
    private String country;
    private String region;
    private String position;
    private Integer reward;
    private String detail;
    private String techStack;
}
