package backend.preonboarding.wanted.repository;

import backend.preonboarding.wanted.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
