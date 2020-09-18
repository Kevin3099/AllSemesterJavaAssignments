package JUnitTesting;

import org.junit.jupiter.api.*;

import java.awt.*;

import static java.awt.Color.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class controllerTest {
    Color color1;
    Color color2;
    int allCells;


    @BeforeEach
    void setUp() {
color1 = WHITE;
color2 = BLACK;
allCells = 0;
    }

    @Test
    void editImg() {
        assertEquals("Checking if color is white",WHITE,color1);
        assertEquals("Checking if color is white",BLACK,color2);
    }
    @Test
    void noiseReduction() {
    }

    @Test
    void counting() {
        assertEquals("Checking counting is 0",0,allCells);

    }
}