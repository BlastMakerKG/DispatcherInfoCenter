package DB.dao;

import DB.model.Data;

import java.util.List;

public interface DataDao {
    void createDataTable();

    void dropDataTable();

    void saveData(String data);

    void removeDataById(long id);

    List<Data> getAllData();

    void cleanDataTable();
}
