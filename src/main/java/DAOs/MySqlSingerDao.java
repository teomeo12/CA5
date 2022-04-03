package DAOs;

/** OOP Feb 2022
 *
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 *
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a particular database (e.g. MySQL or Oracle etc...)
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics.
 *
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by callimng the DAO methods.
 *
 */

import BusinessObjects.LocalDateAdapter;
import BusinessObjects.Singer;
import Exceptions.DaoException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MySqlSingerDao extends MySqlDao implements SingerDaoInterface
{
    @Override
    public List<Singer> findAllSingers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Singer> singerList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM SINGER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int userId = resultSet.getInt("SINGER_ID");
                String name = resultSet.getString("NAME");
                LocalDate dob = resultSet.getDate("DOB").toLocalDate();
                double rate = resultSet.getDouble("RATE");
                String genre = resultSet.getString("GENRE");
                Singer s = new Singer(userId, name, dob, rate, genre);
                singerList.add(s);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllSingerResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllSingers() " + e.getMessage());
            }
        }
        return singerList;     // may be empty
    }
    @Override
    public List<Singer> filterAllSingers() throws DaoException
    {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Singer> singerList = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

          //  String query = "SELECT * FROM `singer` ORDER BY DOB ASC ;";
            String query = "SELECT * FROM SINGER";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next())
            {
                int userId = resultSet.getInt("SINGER_ID");
                String name = resultSet.getString("NAME");
                LocalDate dob = resultSet.getDate("DOB").toLocalDate();
                double rate = resultSet.getDouble("RATE");
                String genre = resultSet.getString("GENRE");
                Singer s = new Singer(userId, name, dob, rate, genre);
                singerList.add(s);
            }

        } catch (SQLException e)
        {
            throw new DaoException("findAllSingerResultSet() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllSingers() " + e.getMessage());
            }
        }
        return singerList;     // may be empty
    }
    @Override
    public Singer findSingerById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int singerID =id;
        Singer singer =null;


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();
            String query = "SELECT * FROM `SINGER` where SINGER_ID = ?;";
            ps = connection.prepareStatement(query);
            ps.setString(1, String.valueOf(id));

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            if (resultSet.next())
            {
                int userId = resultSet.getInt("SINGER_ID");
                String name = resultSet.getString("NAME");
                LocalDate dob = resultSet.getDate("DOB").toLocalDate();
                double rate = resultSet.getDouble("RATE");
                String genre = resultSet.getString("GENRE");
                 singer  = new Singer(userId, name, dob, rate, genre);
              //  returnedSinger=s;
            }
        } catch (SQLException e)
        {
            throw new DaoException("findSingerBy ID() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllSingers() " + e.getMessage());
            }
        }
        return singer;     // may be empty
   }

    public void deleteSingerById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
      //  int singerID =id;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM SINGER where SINGER_ID = ?;";

            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            //Using a PreparedStatement to execute SQL...
              ps.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("deleteSingerByID() " + e.getMessage());
        } finally
        {
            try
            {
                if (ps != null)
                {
                    ps.close();
                    System.out.println();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("deleteSingerByID() " + e.getMessage());
            }
        }
  //  return singer;
    }

    @Override
    public Singer addSinger(String name, LocalDate dob, double rate, String genre) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Singer singer =null;

       //int SINGER_ID = id;
        String NAME = name;
        LocalDate DOB = dob;
        double RATE = rate;
        String GENRE = genre;
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "INSERT INTO SINGER\n" +
                    "VALUES (null,?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
          //  preparedStatement.setInt(1, SINGER_ID);
            preparedStatement.setString(1, NAME);
            preparedStatement.setDate(2, Date.valueOf(DOB));
            preparedStatement.setDouble(3, RATE);
            preparedStatement.setString(4, GENRE);

           preparedStatement.executeUpdate();
            singer = new Singer( NAME, DOB, RATE,GENRE);

        } catch (SQLException e)
        {
            throw new DaoException("addSinger() " + e.getMessage());
        } finally
        {
            try
            {

                if (preparedStatement != null)
                {
                    preparedStatement.close();
                }
                if (connection != null)
                {
                    freeConnection(connection);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findUserByUsernamePassword() " + e.getMessage());
            }
        }
        return singer;     // reference to User object, or null value
    }



    @Override
    public String findAllSingersJSON() throws DaoException
    {
        List<Singer> singerList = findAllSingers();
        Gson gsonParser = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String singerJsonString = gsonParser.toJson(singerList);
        System.out.println(singerJsonString);

        return singerJsonString;     // may be empty
    }
    @Override
    public String findSingersByIDJSON(int id) throws DaoException
    {
        Singer singer = findSingerById(id);
        Gson gsonParser = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String singerJsonStringBYID = gsonParser.toJson(singer);
       // System.out.println(singer);

        return singerJsonStringBYID;     // may be empty
    }

}

