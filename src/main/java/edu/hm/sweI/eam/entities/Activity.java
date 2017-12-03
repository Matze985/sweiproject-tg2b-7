package edu.hm.sweI.eam.entities;

import javax.persistence.*;
import java.util.Date;

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
    @Column(length = 512)
    private String tags;
    @Column(length = 128)
    private String title;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;

    public Activity() {
    }

    public Activity(String text, String tags, String title) {
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
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

    @Override
    public int compareTo(Activity o) {
        return this.getCreated().compareTo(o.getCreated());
    }
}
