package com.gbep.game.sentences;

import com.gbep.game.sentences.entity.Pair;
import com.gbep.game.sentences.entity.SentenceDataset;
import com.gbep.game.sentences.repository.SentenceRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SentencesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentencesApplication.class, args);
    }

//    @Bean
//    CommandLineRunner runner(SentenceRepo repo) {
//        return args -> {
//            List<Pair> list = new ArrayList<>();
//            list.add(new Pair("1", "performance",
//                    "Your manager stops you and says she needs to have a word about your * in the recent project"));
//            list.add(new Pair("2", "sandwiching",
//                    "In an attempt to inject some positivity into their feedback, many managers rely on * negative feedback between two positive comments."));
//            list.add(new Pair("3", "feedback",
//                    "You know how the * sandwich goes: say something nice, say what you really want to say, say something nice again"));
//
//            SentenceDataset data = new SentenceDataset(
//                    "english_2",
//                    list
//            );
//
//            repo.insert(data);
//        };
//    }
}
