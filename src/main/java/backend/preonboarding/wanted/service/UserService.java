package backend.preonboarding.wanted.service;

import backend.preonboarding.wanted.entity.User;
import backend.preonboarding.wanted.entity.UserDTO;
import backend.preonboarding.wanted.repository.RecruitmentRepository;
import backend.preonboarding.wanted.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserRepository userRepository;
    public RecruitmentRepository recruitmentRepository;

    public UserService(UserRepository userRepository, RecruitmentRepository recruitmentRepository) {
        this.userRepository = userRepository;
        this.recruitmentRepository = recruitmentRepository;
    }

    public User applyService(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).get();
        user.setRecruitment(recruitmentRepository.findById(userDTO.getRecruitmentId()).get());

        return userRepository.saveAndFlush(user);
    }
}
