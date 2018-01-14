package edu.hm.sweI.eam.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Matthias Rude on 03.12.2017.
 * Email: Matze.development@gmail.com
 */
@Entity
public class Activity implements Comparable<Activity> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 2048)
    private String text;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable
    @JsonIgnoreProperties("activities")
    private List<Tag> tags = new ArrayList<>();

    @Column(length = 128)
    private String title;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Activity() {
    }

    public Activity(String text, List<Tag> tags, String title) {
        this.text = text;
        this.tags = tags;
        this.title = title;
    }

    public Activity(Long id, String text, List<Tag> tags, String title) {
        this.id = id;
        this.text = text;
        this.tags = tags;
        this.title = title;
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getActivities().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getActivities().remove(this);
    }

    @Override
    public int compareTo(Activity o) {
        return this.getCreated().compareTo(o.getCreated());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
