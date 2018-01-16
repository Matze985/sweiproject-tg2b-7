package edu.hm.swe.eam;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Matthias Rude on 15.01.2018.
 * Email: Matze.development@gmail.com
 */
public class ConstantsTest {

    @Test
    public void constantsCorrect() {
        assertEquals("/api", Constants.API_BASE);
    }

    @Test
    public void instantiatePrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<Constants> constructor = Constants.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        Constants constants = constructor.newInstance();
        assertNotNull(constants);
    }
}
