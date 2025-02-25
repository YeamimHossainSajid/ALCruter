package com.example.ChakriHub.config.ai.service;

import com.example.ChakriHub.auth.model.User;
import com.example.ChakriHub.auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AskCvService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CohereService cohereService;


    public String askCv(Long id,String question) {
        User user = userRepo.findById(id).get();
        String cv=user.getCandidate().getCvInText();
        String answer = cohereService.askCreator(cv+"         bro just read the full above text and based on that try to answer the below question.do answer long.try to keep it under 200 words or below"+question);
        return answer;
    }

}
