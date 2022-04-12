package BusinessObjects;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    @Test
    public void addSinger()
    {
        assertTrue( true );
    }

    @org.junit.jupiter.api.Test
    void displayAllSingers() {
        ArrayList<Singer> singerList = new ArrayList<>(2){{
            add(new Singer(1, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2000, "rock"));
            add(new Singer(2, "Kylie Minogue", LocalDate.parse("1965-12-03"), 3000, "pop"));


        }};
        App app = new App();
        app.displayAllSingers(singerList);
    }

    @org.junit.jupiter.api.Test
    void displayAllSingersFilter() {
    }

    @org.junit.jupiter.api.Test
    void displayOneSingers() {
    }

    @org.junit.jupiter.api.Test
    void hashmap() {
    }

    @org.junit.jupiter.api.Test
    void treeMap() {
    }

    @org.junit.jupiter.api.Test
    void displayPriorityQueue() {
    }

    @org.junit.jupiter.api.Test
    void priorityQueueSimulation() {
    }

    @org.junit.jupiter.api.Test
    void displayQueue() {
    }

    @org.junit.jupiter.api.Test
    void twoFieldPriorityQueue() {
    }
}
