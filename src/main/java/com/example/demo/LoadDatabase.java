package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadDatabase{
    @Bean
    CommandLineRunner initDataBase(GenderRepository genderRepository, JobRepository jobRepository){
        return args -> {
        	genderRepository.save(new Gender("M"));
        	genderRepository.save(new Gender("F"));
        	
        	jobRepository.save(new Job("developer", 30000));
        	jobRepository.save(new Job("administrator", 30000));
        	jobRepository.save(new Job("CEO", 30000));
        };
    }
}