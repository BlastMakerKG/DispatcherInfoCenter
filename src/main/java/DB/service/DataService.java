package DB.service;

import DB.model.*;
import java.util.List;

public interface DataService {
    void createDataTable();

    void dropDataTable();

    void saveData(String data);

    void removeDataById(long id);

    List<Data> getAllData();

    void cleanDataTable();
}
