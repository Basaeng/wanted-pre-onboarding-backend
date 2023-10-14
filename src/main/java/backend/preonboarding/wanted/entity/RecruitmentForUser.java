package backend.preonboarding.wanted.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RecruitmentForUser {
    private Recruitment recruitment;
    private List<Long> companyRecruitmentIds;

}
