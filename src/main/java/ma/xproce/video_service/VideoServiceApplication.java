package ma.xproce.video_service;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import ma.xproce.video_service.dao.entities.*;
import ma.xproce.video_service.dao.repositories.*;

@SpringBootApplication
public class VideoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoServiceApplication.class, args);
    }

    @Bean
    ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
    @Bean
    CommandLineRunner start(CreatorRepository creatorRepository, VideoRepository videoRepository) {
        return args -> {
            List<Creator> creators = creatorRepository.saveAll(List.of(
                Creator.builder().name("Imane").email("imane@mail.com").build(),
                Creator.builder().name("Badr").email("badr@mail.com").build()
            ));

            videoRepository.saveAll(List.of(
                Video.builder().name("GraphQL intro").url("http://x.com/1")
                    .description("Introduction").datePublication("28/11/2023")
                    .creator(creators.get(0)).build(),
                Video.builder().name("Spring Boot").url("http://x.com/2")
                    .description("Tuto Boot").datePublication("29/11/2023")
                    .creator(creators.get(1)).build()
            ));
        };
    }
}