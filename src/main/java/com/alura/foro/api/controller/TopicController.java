package com.alura.foro.api.controller;

import com.alura.foro.api.domain.topic.CreateTopicDTO;
import com.alura.foro.api.domain.topic.Topic;
import com.alura.foro.api.domain.topic.TopicDetailsDTO;
import com.alura.foro.api.domain.topic.TopicRepository;
import com.alura.foro.api.domain.topic.validators.create.TopicValidService;
import com.alura.foro.api.domain.topic.validators.create.TopicValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TopicValidator topicValidator;

    @Autowired
    List<TopicValidService> validTopic;

    @PostMapping
    @Transactional
    public ResponseEntity createTopic(@RequestBody @Valid CreateTopicDTO createTopicDTO, UriComponentsBuilder uriBuilder){

        validTopic.forEach(v -> v.isDuplicated(createTopicDTO));

        Topic topic = new Topic(createTopicDTO);

        topicRepository.save(topic);

        var uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicDetailsDTO(topic));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<TopicDetailsDTO>> readTopics(
            @PageableDefault (size = 5, direction = Direction.DESC) Pageable pagination){

        var page = topicRepository.findAll(pagination).map(TopicDetailsDTO::new);

        return ResponseEntity.ok(page);

    }

}
