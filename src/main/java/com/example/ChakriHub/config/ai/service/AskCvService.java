package com.example.ChakriHub.config.ai.service;

import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.auth.repository.UserRepo;
import com.example.ChakriHub.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AskCvService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CandidateRepository candidateRepo;
    @Autowired
    CohereService cohereService;
//    @Autowired
//    OpenRouterService openRouterService;


    public String askCv(Long id,String question) {
        User user = userRepo.findById(id).get();
        String cv=user.getCandidate().getCvInText();
        String answer = cohereService.askCreator(cv+"         bro just read the full above text and think that it is you and based on that try to answer the below question.                write within 2 lines and fast and straight answer.no need additional talking  "+question);
        return answer;
    }
    public String CvSummery(String text) {
        String answer = cohereService.askCreator(text+" summarize the cv and write the fit possition.no extra lines or words");
        return answer;
    }

}
