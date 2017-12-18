package edu.hm.sweI.eam.entities;

import edu.hm.sweI.eam.BaseEqualsHashCodeTest;

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
}
