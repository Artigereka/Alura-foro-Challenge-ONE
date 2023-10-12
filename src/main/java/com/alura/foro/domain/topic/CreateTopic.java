package com.alura.foro.domain.topic;

public record TopicDetailsDTO(
        String id,
        String title,
        String message,
        String author,
        String course
) {

}
