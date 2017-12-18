package edu.hm.sweI.eam.controller;

import edu.hm.sweI.eam.entities.Activity;
import edu.hm.sweI.eam.entities.Tag;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.hm.sweI.eam.Constants.API_BASE;

interface ActivityRepository extends CrudRepository<Activity, Long> {
}

/**
 * Created by Matthias Rude on 03.12.2017.
 * Email: Matze.development@gmail.com
 */
@RestController
@RequestMapping(API_BASE +"/activity")
public class ActivityController {
    private static final Logger LOGGER = LogManager.getLogger(ActivityController.class);

    private final ActivityRepository activityRepository;
    private final TagRepository tagRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository, TagRepository tagRepository) {
        this.activityRepository = activityRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public List<Activity> listAll() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        Collections.sort(activities);
        Collections.reverse(activities);
        LOGGER.info("found " + activities.size() + " activities!");
        LOGGER.debug("DEBUG TEST");
        return activities;
    }

    @GetMapping("{id}")
    public Activity find(@PathVariable Long id) {
        return activityRepository.findOne(id);
    }

    @PostMapping
    public Activity create(@RequestBody Activity input) {
        LOGGER.info(input.getTitle());
        LOGGER.info(input.getText());

        List<Tag> tags = new ArrayList<>(input.getTags());
        input.getTags().clear();
        tags.forEach(tag -> {
            Tag fromDB = tagRepository.findByTag(tag.getTag());
            input.addTag(fromDB);
        });

        input.getTags().forEach(LOGGER::info);
        return activityRepository.save(new Activity(input.getText(), input.getTags(), input.getTitle()));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        activityRepository.delete(id);
    }

    @PutMapping("{id}")
    public Activity update(@PathVariable Long id, @RequestBody Activity input) {
        Activity activity = activityRepository.findOne(id);
        if (activity == null) {
            return null;
        } else {
            activity.setText(input.getText());
            activity.setTags(input.getTags());
            activity.setTitle(input.getTitle());
            return activityRepository.save(activity);
        }
    }
}
