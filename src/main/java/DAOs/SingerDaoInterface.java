package DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import BusinessObjects.Singer;
import Exceptions.DaoException;
import java.time.LocalDate;
import java.util.List;

public interface SingerDaoInterface
{
    public List<Singer> findAllSingers() throws DaoException;
   public Singer findSingerById(int id) throws DaoException;
    public void deleteSingerById(int id) throws DaoException;
    public Singer addSinger(int id,String name, LocalDate dob, double rate, String genre) throws DaoException;
    public List<Singer> filterAllSingers ()throws DaoException;
    public String findAllSingersJSON() throws DaoException;
    public String findSingersByIDJSON(int id) throws DaoException;
    //    public List<Singer> findAllUsersLastNameContains(String subString) throws DaoException;
//
//    public Singer findUserByUsernamePassword(String username, String password) throws DaoException;



}

