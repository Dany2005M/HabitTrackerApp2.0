import com.github.dany2005m.habittracker.DatabaseManager;
import com.github.dany2005m.habittracker.Habit;
import com.github.dany2005m.habittracker.HabitManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HabitManagerTest {

    private HabitManager manager;
    private static final String TEST_DB_URL = "jdbc:sqlite:habittracker_test.db";

    @BeforeEach
    void setUp() {
        DatabaseManager.setDatabaseUrl(TEST_DB_URL);
        DatabaseManager.initializeDatabase();
        manager = new HabitManager();
    }

    @Test
    void addHabit_ShouldIncreaseHabitListSize() {
        manager.addHabit("Go for a run");
        assertEquals(1, manager.getHabits().size());
        assertEquals("Go for a run", manager.getHabits().get(0).getName());
    }

    @Test
    void addHabit_WithDuplicateName_ShouldNotAddHabit() {
        manager.addHabit("Go for a run");
        manager.addHabit("go for a run"); // Try to add duplicate
        assertEquals(1, manager.getHabits().size(), "Duplicate habits should not be added.");
    }

    @Test
    void nextDay_WhenHabitIsNotDone_ShouldResetStreak() {
        manager.addHabit("Read");
        Habit habit = manager.habitInList("Read");
        habit.setStreak(5);

        manager.nextDay();

        assertEquals(0, habit.getStreak(), "Streak should be reset to 0.");
        assertEquals(2, manager.getDayCounter(), "Day counter should increment.");
    }

    @Test
    void nextDay_WhenHabitIsDone_ShouldKeepStreakAndResetDoneFlag() {
        manager.addHabit("Exercise");
        manager.markHabitAsComplete("Exercise");
        Habit habit = manager.habitInList("Exercise");

        manager.nextDay();

        assertEquals(1, habit.getStreak(), "Streak should be maintained.");
        assertFalse(habit.isDone(), "Done flag should be reset for the new day.");
        assertEquals(2, manager.getDayCounter(), "Day counter should increment.");
    }

    @Test
    void test_saveAndLoadHabits() {
        manager.addHabit("Test Habit 1");
        manager.addHabit("Test Habit 2");
        manager.markHabitAsComplete("Test Habit 2");
        manager.saveToDatabase();

        // Act: Create a new manager and load from the database
        HabitManager newManager = new HabitManager();
        newManager.loadFromDatabase();

        // Assert: Check if the loaded data matches
        assertEquals(2, newManager.getHabits().size());
        Habit loadedHabit = newManager.habitInList("Test Habit 2");
        assertNotNull(loadedHabit);
        assertTrue(loadedHabit.isDone(), "Completion status should be saved and loaded.");
        assertEquals(1, loadedHabit.getStreak(), "Streak should be saved and loaded.");
    }

    @Test
    void test_saveAndLoadDayCounter() {
        // Arrange: Manually advance the day and save
        manager.nextDay(); // Day is now 2
        manager.nextDay(); // Day is now 3
        assertEquals(3, manager.getDayCounter());
        manager.saveDayCounterToDatabase();

        // Act: Create a new manager and load the day counter
        HabitManager newManager = new HabitManager();
        assertEquals(1, newManager.getDayCounter(), "New manager should start at day 1.");
        newManager.loadDayCounterFromDatabase(); // Load the saved state

        // Assert: Check if the day counter was loaded
        assertEquals(3, newManager.getDayCounter(), "Day counter should be loaded from database.");
    }
}