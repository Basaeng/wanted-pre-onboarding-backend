package backend.preonboarding.wanted.controller;

import backend.preonboarding.wanted.entity.Recruitment;
import backend.preonboarding.wanted.entity.RecruitmentDTO;
import backend.preonboarding.wanted.repository.CompanyRepository;
import backend.preonboarding.wanted.repository.RecruitmentRepository;
import backend.preonboarding.wanted.service.RecruitmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class WebController {

    private RecruitmentRepository recruitmentRepository;
    private CompanyRepository companyRepository;
    private RecruitmentService recruitmentService;

    public WebController(RecruitmentRepository recruitmentRepository, CompanyRepository companyRepository,
                         RecruitmentService recruitmentService) {
        this.recruitmentRepository = recruitmentRepository;
        this.companyRepository = companyRepository;
        this.recruitmentService = recruitmentService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Recruitment> InsertRecruitment(@RequestBody RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = recruitmentService.insertRecruitment(recruitmentDTO);
        return ResponseEntity.ok(recruitment);
    }

}
