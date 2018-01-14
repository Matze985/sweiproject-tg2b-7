package edu.hm.sweI.eam.entities;

import edu.hm.sweI.eam.BaseEqualsHashCodeTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matthias Rude on 18.12.2017.
 * Email: Matze.development@gmail.com
 */
public class TagTest extends BaseEqualsHashCodeTest<Tag> {
    public TagTest() {
        super(() -> {
                    Tag tag = new Tag("text");
                    tag.setId(1000L);
                    return tag;
                },
                new Tag("diffText"));
    }

    @Test
    public void setTagSetsTagCorrectly() {
        Tag tag = new Tag("test");
        tag.setTag("testString");
        assertEquals("testString", tag.getTag());
    }

    @Test
    public void addActivitySetsActivityCorrectly() {
        Activity testActivity = new Activity();
        Tag tag = new Tag("test");
        tag.addActivity(testActivity);
        assertEquals(testActivity, tag.getActivities().get(0));

        //add same activity again
        tag.addActivity(testActivity);
        assertEquals(1, tag.getActivities().size());
        assertEquals(testActivity, tag.getActivities().get(0));
    }

    @Test
    public void setActivitiesSetsActivitiesCorrectly() {
        Activity testActivity1 = new Activity();
        Activity testActivity2 = new Activity();
        List<Activity> activityList = new ArrayList<>();
        activityList.add(testActivity1);
        activityList.add(testActivity2);

        assertEquals(2, activityList.size());

        Tag tag = new Tag("test");
        tag.setActivities(activityList);

        assertEquals(activityList, tag.getActivities());
    }
}
