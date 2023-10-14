package backend.preonboarding.wanted.controller;

import backend.preonboarding.wanted.entity.*;
import backend.preonboarding.wanted.repository.CompanyRepository;
import backend.preonboarding.wanted.repository.RecruitmentRepository;
import backend.preonboarding.wanted.repository.UserRepository;
import backend.preonboarding.wanted.service.RecruitmentService;
import backend.preonboarding.wanted.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class WebController {

    private RecruitmentRepository recruitmentRepository;
    private CompanyRepository companyRepository;
    private UserRepository userRepository;
    private RecruitmentService recruitmentService;
    private UserService userService;

    public WebController(RecruitmentRepository recruitmentRepository, CompanyRepository companyRepository, UserRepository userRepository,
                         RecruitmentService recruitmentService, UserService userService) {
        this.recruitmentRepository = recruitmentRepository;
        this.companyRepository = companyRepository;
        this.userRepository = userRepository;
        this.recruitmentService = recruitmentService;
        this.userService = userService;
    }

    @PostMapping("/insert")
    public ResponseEntity<Recruitment> InsertRecruitment(@RequestBody RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = recruitmentService.insertRecruitment(recruitmentDTO);
        return ResponseEntity.ok(recruitment);
    }

    @PostMapping("/update")
    public ResponseEntity<Recruitment> UpdateRecruitment(@RequestBody RecruitmentDTO recruitmentDTO) {
        Recruitment recruitment = recruitmentService.updateRecruitment(recruitmentDTO);
        return ResponseEntity.ok(recruitment);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Recruitment> DeleteRecruitment(@PathVariable Long id) {
        Recruitment recruitment = recruitmentService.deleteRecruitment(id);
        return ResponseEntity.ok(recruitment);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Recruitment>> RecruitmentList() {
        List<Recruitment> recruitmentList = recruitmentRepository.findAll();
        return ResponseEntity.ok(recruitmentList);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<RecruitmentForUser> RecruitmentList(@PathVariable Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).get();

        List<Long> idByCompanyId = recruitmentRepository.findIdByCompanyId(recruitment.getCompany().getId());
        RecruitmentForUser recruitmentForUser = new RecruitmentForUser(recruitment, idByCompanyId);

        return ResponseEntity.ok(recruitmentForUser);
    }

    @GetMapping("/some/url")
    public ResponseEntity<List<Recruitment>> SearchRecruitment(@RequestParam String value) {
        log.info("value={}", value);
        List<Recruitment> results = recruitmentService.searchRecruitment(value);
        return ResponseEntity.ok(results);
    }

    @PostMapping("/apply")
    public ResponseEntity<User> ApplyRecruitment(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.applyService(userDTO));
    }
}
