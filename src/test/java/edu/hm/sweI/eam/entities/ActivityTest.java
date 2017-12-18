package edu.hm.sweI.eam.entities;

import edu.hm.sweI.eam.BaseEqualsHashCodeTest;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

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
}
