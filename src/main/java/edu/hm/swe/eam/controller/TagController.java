package edu.hm.swe.eam.controller;

import edu.hm.swe.eam.entities.Tag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static edu.hm.swe.eam.Constants.API_BASE;

@RestController
@RequestMapping(API_BASE + "/tag")
public class TagController {
    private static final Logger LOGGER = LogManager.getLogger(ActivityController.class);

    private final TagRepository tagRepository;

    @Autowired
    public TagController(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Tag> listAll() {
        List<Tag> tags = new ArrayList<>();
        tagRepository.findAll().forEach(tags::add);
        LOGGER.info("found " + tags.size() + " tags!");
        return tags;
    }

    @PostMapping
    public List<Tag> create(@RequestBody List<Tag> input) {
        List<Tag> tags = new ArrayList<>();
        input.forEach(tag -> {
            Tag existing = tagRepository.findByTag(tag.getTag());
            if (existing != null) {
                tags.add(existing);
            } else {
                tags.add(tagRepository.save(new Tag(tag.getTag())));
            }
        });
        return tags;
    }
}
