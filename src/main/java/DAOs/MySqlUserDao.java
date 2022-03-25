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


import BusinessObjects.Singer;
//import DTOs.Singer;
import Exceptions.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MySqlUserDao extends MySqlDao implements UserDaoInterface
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

            String query = "SELECT * FROM USER";
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

//

    @Override
    public Singer addSinger(Singer singer) throws DaoException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int SINGER_ID = singer.getId();
        String NAME = singer.getName();
        LocalDate DOB = singer.getDob();
        double RATE = singer.getRate();
        String GENRE = singer.getGenre();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

//            String query = "INSERT INTO user VALUES('NAME','DOB','RATE','GENRE')";
//            preparedStatement = connection.prepareStatement(query);

            String query = "INSERT INTO USER\n" +
                    "VALUES (?,?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, SINGER_ID);
            preparedStatement.setString(2, NAME);
            preparedStatement.setDate(3, Date.valueOf(DOB));
            preparedStatement.setDouble(4, RATE);
            preparedStatement.setString(5, GENRE);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            throw new DaoException("addUser() " + e.getMessage());
        } finally
        {
            try
            {
                if (resultSet != null)
                {
                    resultSet.close();
                }
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


}

