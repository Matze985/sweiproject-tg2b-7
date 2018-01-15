package edu.hm.sweI.eam.entities;

import edu.hm.sweI.eam.BaseEqualsHashCodeTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matthias Rude on 18.12.2017.
 * Email: Matze.development@gmail.com
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ActivityTest extends BaseEqualsHashCodeTest<Activity> {
    public ActivityTest() {
        super(() -> {
                    Activity activity = new Activity("text", new ArrayList<>(), "title");
                    activity.setId(1000L);
                    return activity;
                },
                new Activity("diffText", new ArrayList<>(), "diffTitle"));
    }

    @Test
    public void createActivityWithIDCreatesActivityCorrectly(){
        Activity activity = new Activity(200L, "text", new ArrayList<>(), "title");
        assertEquals(new Long(200), activity.getId());
        assertEquals("text", activity.getText());
        assertEquals("title", activity.getTitle());
        assertEquals("title", activity.getTitle());
    }

    @Test
    public void setCreatedSetsCreatedCorrectly(){
        Activity activity = new Activity();
        Date date = new Date();
        activity.setCreated(date);
        assertEquals(date, activity.getCreated());
    }

    @Test
    public void setUpdatedSetsUpdatedCorrectly(){
        Activity activity = new Activity();
        Date date = new Date();
        activity.setUpdated(date);
        assertEquals(date, activity.getUpdated());
    }

    @Test
    public void setTextSetsTextCorrectly() {
        Activity activity = new Activity();
        activity.setText("testText");
        assertEquals("testText", activity.getText());
    }

    @Test
    public void setTitleSetsTitleCorrectly() {
        Activity activity = new Activity();
        activity.setTitle("testText");
        assertEquals("testText", activity.getTitle());
    }

    @Test
    public void addTagAddsTagCorrectly() {
        Tag tag = new Tag("tag");
        Activity activity = new Activity();
        activity.addTag(tag);

        assertEquals(1, activity.getTags().size());
        assertEquals(tag, activity.getTags().get(0));

        assertEquals(1, activity.getTags().get(0).getActivities().size());
        assertEquals(activity, activity.getTags().get(0).getActivities().get(0));
    }

    @Test
    public void removeTagRemovesTagCorrectly() {
        Tag tag1 = new Tag("tag");
        Tag tag2 = new Tag("tag");
        Activity activity = new Activity();
        activity.addTag(tag1);
        activity.addTag(tag2);

        assertEquals(2, activity.getTags().size());
        assertEquals(tag1, activity.getTags().get(0));

        assertEquals(1, activity.getTags().get(0).getActivities().size());
        assertEquals(activity, activity.getTags().get(0).getActivities().get(0));

        activity.removeTag(tag1);
        assertEquals(1, activity.getTags().size());
        assertEquals(tag2, activity.getTags().get(0));
    }
}
