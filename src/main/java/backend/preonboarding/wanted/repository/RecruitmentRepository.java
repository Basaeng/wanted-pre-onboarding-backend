package backend.preonboarding.wanted.repository;


import backend.preonboarding.wanted.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, JpaSpecificationExecutor<Recruitment> {

    @Query(value = "select r.id from Recruitment r where r.company.id =:companyId")
    List<Long> findIdByCompanyId(@Param("companyId")Long companyId);
}
