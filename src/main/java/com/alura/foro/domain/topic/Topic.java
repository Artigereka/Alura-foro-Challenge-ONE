package com.alura.foro.domain.topic;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "topics")
@Entity(name = "Topic")
@EqualsAndHashCode(of = "id")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private Long UserId;
    private Long course;

    public Topic(CreateTopic createTopic){
        this.title = createTopic.title();
        this.body = createTopic.body();
        this.UserId = createTopic.userId();
        this.course = createTopic.course();
    }

}
