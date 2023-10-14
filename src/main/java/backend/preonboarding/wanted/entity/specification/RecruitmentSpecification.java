package backend.preonboarding.wanted.entity.specification;

import backend.preonboarding.wanted.entity.Company;
import backend.preonboarding.wanted.entity.Recruitment;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class RecruitmentSpecification {

    public static Specification<Recruitment> findMatchString(String value) {
        if (value == null || value.isEmpty()) {
            return Specification.where(null);
        } else {

            return (root, query, cb) -> {
                Predicate predicateRecruitment = cb.or(
                        cb.like(root.get("country"), "%" + value + "%"),
                        cb.like(root.get("region"), "%" + value + "%"),
                        cb.like(root.get("position"), "%" + value + "%"),
                        cb.like(root.get("detail"), "%" + value + "%"),
                        cb.like(root.get("techStack"), "%" + value + "%")
                );

                Join<Recruitment, Company> companyJoin = root.join("company");
                Predicate predicateCompany = cb.like(companyJoin.get("name"), "%" + value + "%");

                return cb.or(predicateRecruitment, predicateCompany);
            };
        }
    }
}
