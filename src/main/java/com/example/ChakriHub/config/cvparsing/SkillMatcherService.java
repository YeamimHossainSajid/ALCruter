package com.example.ChakriHub.config.cvparsing;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkillMatcherService {

    private static final List<String> SKILLS = List.of(
            "Java", "Python", "SQL", "Machine Learning", "Data Analysis", "JavaScript", "HTML", "CSS");

    private final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

    public List<String> extractSkills(String text) {
        Set<String> foundSkills = new HashSet<>();
        String[] tokens = text.toLowerCase().split("\\W+");

        for (String skill : SKILLS) {
            for (String token : tokens) {
                if (token.equals(skill.toLowerCase()) || levenshteinDistance.apply(token, skill.toLowerCase()) <= 2) {
                    foundSkills.add(skill);
                    break;
                }
            }
        }
        return new ArrayList<>(foundSkills);
    }

    public List<String> matchCandidates(String cvText, String jobDescription) {
        List<String> cvSkills = extractSkills(cvText);
        List<String> jobSkills = extractSkills(jobDescription);

        List<String> matchedSkills = new ArrayList<>();
        for (String skill : cvSkills) {
            if (jobSkills.contains(skill)) {
                matchedSkills.add(skill);
            }
        }
        return matchedSkills;
    }
}

