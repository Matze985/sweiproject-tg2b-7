package edu.hm.swe.eam;

import org.junit.Test;

/**
 * Created by Matthias Rude on 15.01.2018.
 * Email: Matze.development@gmail.com
 */
public class ApplicationTest {
    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void testToCallMain() {
        Application.main(new String[]{
                "--spring.main.web-environment=false",
                "--spring.autoconfigure.exclude=blahblahblah",
        });
    }
}
