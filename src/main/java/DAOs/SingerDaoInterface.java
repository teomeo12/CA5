package DAOs;

import DTOs.Singer;
import Exceptions.DaoException;

import java.time.LocalDate;
import java.util.List;

public interface SingerDaoInterface {
    public List<Singer> findAllSingers() throws DaoException;

    public Singer findSingerById(int id) throws DaoException;

    public void deleteSingerById(int id) throws DaoException;

    public Singer addSinger(String name, LocalDate dob, double rate, String genre) throws DaoException;

    public List<Singer> filterAllSingers() throws DaoException;

    public String findAllSingersJSON() throws DaoException;

    public String findSingersByIDJSON(int id) throws DaoException;

    public String findSingersByIDJSONServer(int id) throws DaoException;

    public String findAllSingersJSONServer() throws DaoException;

    public void addSinger(Singer singer) throws DaoException;

    public Singer findSingersHighRate() throws DaoException;

    public String findSingersHighestRateJSONServer() throws DaoException;
}

