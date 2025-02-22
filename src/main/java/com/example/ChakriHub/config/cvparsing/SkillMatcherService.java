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
            // Programming & Software Development
            "Java", "Python", "C++", "C#", "JavaScript", "TypeScript", "Swift", "Kotlin",
            "Dart", "Go", "Rust", "PHP", "Ruby", "Perl", "Shell Scripting", "SQL", "NoSQL",
            "MongoDB", "PostgreSQL", "Firebase", "MySQL", "Spring Boot", "Django", "Flask",
            "React", "Angular", "Vue.js", "Node.js", "Express.js", "Android Development",
            "iOS Development", "Flutter", "React Native", "Blockchain", "Smart Contracts",
            "Solidity", "Docker", "Kubernetes", "Git", "CI/CD", "DevOps", "AWS", "Azure",
            "Google Cloud", "Microservices", "GraphQL", "REST API", "Web Development",
            "Cybersecurity", "Penetration Testing", "Ethical Hacking", "Networking",
            "Linux Administration", "AI", "Machine Learning", "Deep Learning", "NLP",
            "Computer Vision", "Big Data", "Hadoop", "Spark", "Data Engineering",
            "Data Science", "Data Analysis", "ETL", "Power BI", "Tableau", "TensorFlow",
            "PyTorch", "Jupyter Notebook",

            //  Business & Management
            "Business Strategy", "Project Management", "Scrum", "Agile Methodologies",
            "Risk Management", "Lean Management", "Six Sigma", "Operations Management",
            "Supply Chain Management", "Business Analysis", "Market Research",
            "Customer Relationship Management (CRM)", "Salesforce", "Negotiation",
            "Public Speaking", "Leadership", "People Management", "Organizational Behavior",

            //  Finance & Accounting
            "Financial Analysis", "Investment Banking", "Accounting", "Auditing",
            "Taxation", "Budgeting", "Financial Modeling", "QuickBooks", "SAP", "Oracle",
            "Cost Accounting", "Risk Assessment", "Portfolio Management", "Cryptocurrency",

            // Marketing & Advertising
            "Digital Marketing", "SEO", "Content Marketing", "Social Media Marketing",
            "Google Ads", "Facebook Ads", "Affiliate Marketing", "Influencer Marketing",
            "Brand Management", "Copywriting", "Public Relations", "Market Segmentation",
            "Consumer Behavior", "Email Marketing", "Lead Generation", "Growth Hacking",
            "PPC Advertising", "Video Marketing", "Google Analytics",

            // Design & Multimedia
            "Graphic Design", "UI/UX Design", "Adobe Photoshop", "Adobe Illustrator",
            "Adobe XD", "Figma", "Sketch", "3D Modeling", "Blender", "AutoCAD",
            "Motion Graphics", "Video Editing", "Adobe Premiere Pro", "Final Cut Pro",
            "After Effects", "Animation", "Game Design", "Character Design",

            //  Engineering & Architecture
            "Mechanical Engineering", "Electrical Engineering", "Civil Engineering",
            "Structural Engineering", "Industrial Engineering", "AutoCAD",
            "SolidWorks", "MATLAB", "Fusion 360", "PCB Design", "Robotics", "IoT",

            //  Healthcare & Medicine
            "Medical Research", "Clinical Trials", "Pharmaceuticals", "Biomedical Science",
            "Nursing", "Surgery", "Dentistry", "Physiotherapy", "Healthcare Management",
            "Medical Coding", "EHR Systems", "Telemedicine",

            // Legal & Compliance
            "Corporate Law", "Intellectual Property Law", "Contract Law", "Compliance",
            "GDPR", "Cyber Law", "Employment Law", "Legal Research", "Paralegal Skills",

            //  Education & Training
            "Teaching", "Curriculum Development", "E-Learning", "Educational Psychology",
            "Instructional Design", "Training & Development", "Language Teaching",

            // Sales & Customer Support
            "B2B Sales", "B2C Sales", "Cold Calling", "Customer Service", "CRM Software",
            "Retail Management", "Client Relationship Management",

            // Other Industry Skills
            "Data Entry", "Virtual Assistance", "Translation", "Transcription",
            "Content Writing", "Technical Writing", "Creative Writing", "Event Planning"
    );


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

    public double calculateMatchPercentage(String cvText, String jobDescription) {
        List<String> cvSkills = extractSkills(cvText);
        List<String> jobSkills = extractSkills(jobDescription);

        if (jobSkills.isEmpty()) {
            return 0.0;
        }

        long matchedCount = cvSkills.stream().filter(jobSkills::contains).count();
        return (matchedCount / (double) jobSkills.size()) * 100;
    }

}

