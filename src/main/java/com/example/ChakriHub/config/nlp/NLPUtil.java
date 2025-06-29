package com.example.ChakriHub.config.nlp;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NLPUtil {

    public static List<String> extractSkills(String text) throws IOException {
        List<String> skills = new ArrayList<>();


        try (FileInputStream modelIn = new FileInputStream("en-token.bin")) {
            TokenizerModel tokenModel = new TokenizerModel(modelIn);
            TokenizerME tokenizer = new TokenizerME(tokenModel);
            String[] tokens = tokenizer.tokenize(text);

            try (FileInputStream skillsModelIn = new FileInputStream("skills-model.bin")) {
                TokenNameFinderModel skillsModel = new TokenNameFinderModel(skillsModelIn);
                NameFinderME nameFinder = new NameFinderME(skillsModel);


                Span[] spans = nameFinder.find(tokens);
                for (Span span : spans) {

                    String skill = String.join(" ", java.util.Arrays.copyOfRange(tokens, span.getStart(), span.getEnd()));
                    skills.add(skill);
                }
            }
        }

        return skills;
    }
}

