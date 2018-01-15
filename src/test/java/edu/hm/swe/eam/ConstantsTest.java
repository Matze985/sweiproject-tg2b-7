package edu.hm.swe.eam;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Matthias Rude on 15.01.2018.
 * Email: Matze.development@gmail.com
 */
public class ConstantsTest {

    @Test
    public void constantsCorrect() {
        assertEquals("/api", Constants.API_BASE);
    }
}
