package com.pluralsight;

import org.junit.jupiter.api.Test;                                 // marks a method as a test JUnit should run

import static org.junit.jupiter.api.Assertions.*;                  // assertEquals, assertNotEquals, assertTrue, assertFalse, assertArrayEquals

/*
 * CarTest: one test class per application class, same package name,
 * but under src/test/java instead of src/main/java. Nothing here ships
 * with the program; it only runs when you run the tests.
 *
 * Every test has three parts, in this order:
 *   Arrange  build the object and the values you need
 *   Act      call the ONE method you are testing
 *   Assert   compare what you expected with what you got
 *
 * Test names follow MethodName_StateUnderTest_ExpectedBehavior, so a red
 * test tells you what broke without opening the file:
 *   brake_SpeedChangeGreaterThanActualSpeed_SpeedIsZero
 *
 * The pom.xml has the junit-jupiter dependency with scope test. After
 * adding it, press the Maven reload icon or @Test stays red.
 */
class CarTest {

    @Test
    public void accelerate_NormalSpeedChange_SpeedIncreased() {
        // Arrange: the object under test, the input, the expected result (no magic numbers)
        Car bugatti = new Car("Bugatti", "Chevron");
        int speedChange = 15;
        int expectedSpeed = 15;

        // Act: the one method call being tested
        bugatti.accelerate(speedChange);

        // Assert: expected first, actual second. If they differ the test is red
        // and the message reads "expected: <15> but was: <...>"
        int actualSpeed = bugatti.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    public void brake_NormalSpeedChange_SpeedDecreased() {
        // Arrange
        Car prius = new Car("Toyota", "Prius");
        int speedUpBy = 15;
        int slowDownBy = 10;
        int expectedSpeed = 5;
        prius.accelerate(speedUpBy);                                // set-up call: the car has to move before it can brake. Still Arrange, not Act

        // Act
        prius.brake(slowDownBy);

        // Assert
        int actualSpeed = prius.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
    }

    // A second scenario of the same method gets its own test: braking harder
    // than the car is moving must stop it at 0, never go negative
    @Test
    public void brake_SpeedChangeGreaterThanActualSpeed_SpeedIsZero() {
        // Arrange
        Car rav4 = new Car("Toyota", "Rav4");
        int speedUpBy = 15;
        int slowDownBy = 20;
        int expectedSpeed = 0;
        rav4.accelerate(speedUpBy);

        // Act
        rav4.brake(slowDownBy);

        // Assert
        int actualSpeed = rav4.getSpeed();
        assertEquals(expectedSpeed, actualSpeed);
        // assertEquals(expectedSpeed + 1, actualSpeed);           // uncomment and run: the test fails, expected: <1> but was: <0>
    }

    // assertTrue / assertFalse: for a method that returns a boolean, no expected value needed
    @Test
    public void isMoving_NewCar_False() {
        // Arrange
        Car mustang = new Car("Ford", "Mustang");

        // Act
        boolean moving = mustang.isMoving();

        // Assert
        assertFalse(moving);
    }

    @Test
    public void isMoving_AfterAccelerate_True() {
        // Arrange
        Car mustang = new Car("Ford", "Mustang");
        mustang.accelerate(10);

        // Act
        boolean moving = mustang.isMoving();

        // Assert
        assertTrue(moving);
    }
}
