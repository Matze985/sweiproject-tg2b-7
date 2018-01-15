package edu.hm.swe.eam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Matthias Rude on 12.12.2017.
 * Email: Matze.development@gmail.com
 */
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 128, nullable = false, unique = true)
    private String tag;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnoreProperties("tags")
    private List<Activity> activities = new ArrayList<>();

    public Tag() {
    }

    public Tag(String text) {
        tag = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void addActivity(Activity input) {
        if (activities.contains(input)) {
            return;
        }
        activities.add(input);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", activities=" + activities +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag1 = (Tag) o;
        return Objects.equals(tag, tag1.tag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(tag);
    }
}
