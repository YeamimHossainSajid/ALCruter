package com.example.ChakriHub.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class MatchedCandidateResponseDto {
    private Long candidateId;
    private String candidateName;
    private List<String> matchedSkills;
    private double matchPercentage;
    private String username;
    private String cvSummery;

    public String getCvSummery() {
        return cvSummery;
    }

    public void setCvSummery(String cvSummery) {
        this.cvSummery = cvSummery;
    }




    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public List<String> getMatchedSkills() {
        return matchedSkills;
    }

    public void setMatchedSkills(List<String> matchedSkills) {
        this.matchedSkills = matchedSkills;
    }

    public double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }
}

