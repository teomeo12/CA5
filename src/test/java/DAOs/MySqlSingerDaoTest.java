package DAOs;

import BusinessObjects.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import BusinessObjects.Singer;
import Exceptions.DaoException;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
public class MySqlSingerDaoTest extends TestCase{


    @Test
    void findAllSingers() {
    }

    @Test
    void filterAllSingers() {
    }

    @Test
    void findSingerById() throws DaoException {
        System.out.println("Test for Feature 8 - FindSingerByID");

        int id = 5;
        Singer expectedSinger = new Singer (5, "Mattiel", LocalDate.parse("2000-12-04"), 14000, "pop");
        MySqlSingerDao mySqlSingerDao = new MySqlSingerDao();
        Assertions.assertEquals(expectedSinger, mySqlSingerDao.findSingerById(id));
    }

    @Test
    void deleteSingerById() throws DaoException {
        System.out.println("Test for Feature 9 - delete Singer");
        MySqlSingerDao mySqlSingerDao = new MySqlSingerDao();
        mySqlSingerDao.deleteSingerById(1);
        Singer newSinger = new Singer( 1, "King of Leon", LocalDate.parse("1999-12-05"), 9000, "rock");
        mySqlSingerDao.addSinger(newSinger.getName(), newSinger.getDob(), newSinger.getRate(), newSinger.getGenre());
        mySqlSingerDao.deleteSingerById(1);

        assertNull(mySqlSingerDao.findSingerById(1));
    }

    @Test
    void addSinger() throws DaoException {
        System.out.println("Test for Feature 9 - add Singer");
        MySqlSingerDao mySqlSingerDao = new MySqlSingerDao();
       // mySqlSingerDao.deleteSingerById(1);
        Singer newSinger = new Singer( 1, "King of Leon", LocalDate.parse("1999-12-05"), 9000, "rock");
        mySqlSingerDao.addSinger(newSinger.getName(), newSinger.getDob(), newSinger.getRate(), newSinger.getGenre());

        mySqlSingerDao.findSingerById(1);

    }

    @Test
    void findAllSingersJSON() {
    }

    @Test
    void findSingersByIDJSON() {
    }

    @Test
    void findSingersByIDJSONServer() throws DaoException {
        System.out.println("Test for Feature 8 - FindSingerByID");

        int id = 5;
        Singer expectedSinger = new Singer (5, "Mattiel", LocalDate.parse("2000-12-04"), 14000, "pop");
        MySqlSingerDao mySqlSingerDao = new MySqlSingerDao();
        Assertions.assertEquals(expectedSinger, mySqlSingerDao.findSingerById(id));
    }

    @Test
    void findAllSingersJSONServer() {
    }
}
