package backend.preonboarding.wanted.repository;


import backend.preonboarding.wanted.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

}
