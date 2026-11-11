package com.pluralsight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * RoomTest (Workbook 4a, Section 3-2 Creating Unit Tests, Exercise 1)
 *
 * One test per rule, named method_situation_expectedResult, each in the
 * Arrange / Act / Assert shape from the module.
 *
 * The questions in the text, answered by the tests:
 *   "Should you be able to check in a room if it is already occupied?
 *    What if it is dirty?"  No: checkIn_RoomWasPreviouslyUsed_RoomIsNotOccupied
 *   "Should housekeeping be able to clean the room if it is occupied?"
 *    No: cleanRoom_RoomIsOccupied_RoomRemainsDirty
 *
 * Run with the green arrow in IntelliJ or mvn test.
 */
class RoomTest {

    @Test
    public void checkIn_RoomInitialStatus_RoomIsOccupiedAndDirty() {
        // Arrange
        Room room = new Room(2, 100.0);

        // Act
        room.checkIn();

        // Assert
        assertTrue(room.isOccupied());
        assertTrue(room.isDirty());
    }

    @Test
    public void checkOut_RoomIsOccupied_RoomIsNotOccupied() {
        // Arrange
        Room room = new Room(1, 50.0);
        room.checkIn();

        // Act
        room.checkOut();

        // Assert
        assertFalse(room.isOccupied());
    }

    // After a check out the room is dirty, so a second check in must be refused
    @Test
    public void checkIn_RoomWasPreviouslyUsed_RoomIsNotOccupied() {
        // Arrange
        Room room = new Room(2, 100.0);
        room.checkIn();
        room.checkOut();

        // Act
        room.checkIn();

        // Assert
        assertFalse(room.isOccupied());
    }

    @Test
    public void cleanRoom_RoomIsOccupied_RoomRemainsDirty() {
        // Arrange
        Room room = new Room(2, 100.0);
        room.checkIn();

        // Act
        room.cleanRoom();

        // Assert
        assertTrue(room.isOccupied());
        assertTrue(room.isDirty());
    }

    @Test
    public void cleanRoom_RoomWasPreviouslyUsed_RoomIsNotDirty() {
        // Arrange
        Room room = new Room(1, 50.0);
        room.checkIn();
        room.checkOut();

        // Act
        room.cleanRoom();

        // Assert
        assertFalse(room.isDirty());
    }


}
