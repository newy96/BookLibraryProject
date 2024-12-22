package com.book.library.BookLibrary;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.book.library.BookLibrary.entities")
@ComponentScan("com.book.library.BookLibrary.*")
public class BookLibraryApiApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public DataLoader dataLoader() {
        return new DataLoader();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BookLibraryApiApplication.class, args);

        DataLoader dataLoader = context.getBean(DataLoader.class);
        dataLoader.seedRoles();
    }
}
