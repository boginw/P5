package dk.sw502e18.ssr;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void canConstructAPersonWithAName() {
        Main main = new Main();
        assertEquals(main.getClass().getName(), "dk.sw502e18.ssr.Main");
    }
}