package edu.hm.sweI.eam.controller;

import edu.hm.sweI.eam.entities.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static edu.hm.sweI.eam.controller.Constants.API_BASE;

interface ActivityRepository extends CrudRepository<Activity, Long> {
}

/**
 * Created by Matthias Rude on 03.12.2017.
 * Email: Matze.development@gmail.com
 */
@RestController
@RequestMapping(API_BASE +"/activity")
public class ActivityController {
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @GetMapping
    public List<Activity> listAll() {
        List<Activity> activities = new ArrayList<>();
        activityRepository.findAll().forEach(activities::add);
        Collections.sort(activities);
        Collections.reverse(activities);
        return activities;
    }

    @GetMapping("{id}")
    public Activity find(@PathVariable Long id) {
        return activityRepository.findOne(id);
    }

    @PostMapping
    public Activity create(@RequestBody Activity input) {
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
