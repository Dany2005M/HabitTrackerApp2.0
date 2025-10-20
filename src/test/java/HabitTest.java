import com.github.dany2005m.habittracker.Habit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HabitTest {
    private Habit habit;
    @BeforeEach
    void setUP(){
        habit  = new Habit("Test Habit");
    }


    @Test
    void whenDone_shouldIncreaseStreak() {
        habit.setDone();
        assertEquals(1, habit.getStreak());
    }

    @Test
    void whenDone_totalDoneShouldIncrease() {
        habit.setDone();
        assertEquals(1, habit.getTotalDone());
    }

    @Test
    void whenDone_doneShouldBeTrue() {
        habit.setDone();
        assertTrue(habit.isDone());
    }

    @Test
    void whenUnDone_doneShouldBeFalse() {
        habit.unDone();
        assertFalse(habit.isDone());
    }

    @Test
    void doneTrue_thenNothingHappens() {
        habit.setDoneForTest();
        assertEquals(0, habit.getStreak());
        assertEquals(0, habit.getTotalDone());
    }
}