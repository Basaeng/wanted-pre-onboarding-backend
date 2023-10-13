package backend.preonboarding.wanted.service;

import backend.preonboarding.wanted.entity.Company;
import backend.preonboarding.wanted.entity.Recruitment;
import backend.preonboarding.wanted.entity.RecruitmentDTO;
import backend.preonboarding.wanted.exception.InsertFailException;
import backend.preonboarding.wanted.exception.UpdateFailException;
import backend.preonboarding.wanted.repository.CompanyRepository;
import backend.preonboarding.wanted.repository.RecruitmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecruitmentService {
    private RecruitmentRepository recruitmentRepository;

    private CompanyRepository companyRepository;

    public RecruitmentService(RecruitmentRepository recruitmentRepository, CompanyRepository companyRepository) {
        this.recruitmentRepository = recruitmentRepository;
        this.companyRepository = companyRepository;
    }

    public Recruitment insertRecruitment(RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = new Recruitment();
        if (recruitmentDTO.getCompanyId() == null) {
            throw new IllegalArgumentException("companyId cannot be null");
        }

        Company company = companyRepository.findById(recruitmentDTO.getCompanyId())
                .orElseThrow(() -> new InsertFailException("Company ID " + recruitmentDTO.getCompanyId() + " not found"));

        recruitment.setCompany(company);
        recruitment.setCountry(recruitmentDTO.getCountry());
        recruitment.setRegion(recruitmentDTO.getRegion());
        recruitment.setPosition(recruitmentDTO.getPosition());
        recruitment.setReward(recruitmentDTO.getReward());
        recruitment.setDetail(recruitmentDTO.getDetail());
        recruitment.setTechStack(recruitmentDTO.getTechStack());

        try {
            return recruitmentRepository.saveAndFlush(recruitment);
        } catch (DataIntegrityViolationException e) {
            throw new InsertFailException("missing field");
        }

    }

    public Recruitment updateRecruitment(RecruitmentDTO recruitmentDTO) {
        if (recruitmentDTO.getId() == null) {
            throw new UpdateFailException("ID not found");
        }

        Recruitment recruitment = recruitmentRepository.findById(recruitmentDTO.getId())
                .orElseThrow(() -> new UpdateFailException("Recruitment not found"));

        if (recruitmentDTO.getCompanyId() != null) {
            Company company = companyRepository.findById(recruitmentDTO.getCompanyId())
                    .orElseThrow(() -> new UpdateFailException("Company not found"));
            recruitment.setCompany(company);
        }
        if (recruitmentDTO.getCountry() != null) {
            recruitment.setCountry(recruitmentDTO.getCountry());
        }
        if (recruitmentDTO.getRegion() != null) {
            recruitment.setRegion(recruitmentDTO.getRegion());
        }
        if (recruitmentDTO.getPosition() != null) {
            recruitment.setPosition(recruitmentDTO.getPosition());
        }
        if (recruitmentDTO.getReward() != null) {
            recruitment.setReward(recruitmentDTO.getReward());
        }
        if (recruitmentDTO.getDetail() != null) {
            recruitment.setDetail(recruitmentDTO.getDetail());
        }
        if (recruitmentDTO.getTechStack() != null) {
            recruitment.setTechStack(recruitmentDTO.getTechStack());
        }

        return recruitmentRepository.saveAndFlush(recruitment);
    }

    public Recruitment deleteRecruitment(Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).orElse(null);
        if (recruitment != null) {
            recruitmentRepository.delete(recruitment);
        }
        return recruitment;
    }

}
