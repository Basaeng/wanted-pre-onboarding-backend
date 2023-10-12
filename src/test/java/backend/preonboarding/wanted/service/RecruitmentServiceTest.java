package backend.preonboarding.wanted.service;

import backend.preonboarding.wanted.entity.Company;
import backend.preonboarding.wanted.entity.Recruitment;
import backend.preonboarding.wanted.entity.RecruitmentDTO;
import backend.preonboarding.wanted.exception.InsertFailException;
import backend.preonboarding.wanted.repository.CompanyRepository;
import backend.preonboarding.wanted.repository.RecruitmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class RecruitmentServiceTest {
    @InjectMocks
    private RecruitmentService recruitmentService;

    @Mock
    private RecruitmentRepository recruitmentRepository;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertRecruitmentSuccess() {
        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
        recruitmentDTO.setCompanyId(1L);
        recruitmentDTO.setCountry("Country");
        recruitmentDTO.setRegion("Region");
        recruitmentDTO.setPosition("Position");
        recruitmentDTO.setReward(1000);
        recruitmentDTO.setDetail("Detail");
        recruitmentDTO.setTechStack("TechStack");
        Company mockCompany = new Company();
        when(companyRepository.findById(1L)).thenReturn(Optional.of(mockCompany));

        Recruitment expectedRecruitment = new Recruitment();
        expectedRecruitment.setCompany(mockCompany);
        when(recruitmentRepository.saveAndFlush(any())).thenReturn(expectedRecruitment);

        Recruitment result = recruitmentService.insertRecruitment(recruitmentDTO);

        assertThat(result.getCompany()).isEqualTo(mockCompany);
    }

    @Test
    void testInsertRecruitmentFailure() {
        RecruitmentDTO recruitmentDTO = new RecruitmentDTO();
        recruitmentDTO.setCompanyId(1L);

        when(companyRepository.findById(1L)).thenReturn(Optional.empty());
        when(recruitmentRepository.saveAndFlush(any())).thenThrow(DataIntegrityViolationException.class);

        assertThrows(InsertFailException.class, () -> recruitmentService.insertRecruitment(recruitmentDTO));
    }


    @Test
    void testDeleteRecruitment() {

    }

    @Test
    void testSearchRecruitment() {

    }
}
