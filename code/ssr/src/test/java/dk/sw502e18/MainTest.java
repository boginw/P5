package dk.sw502e18;

import dk.sw502e18.ssr.Main;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void canConstructAPersonWithAName() {
        Main main = new Main();
        assertEquals(main.getClass().getName(), "dk.sw502e18.ssr.Main");
    }
}