package DB.service;

import DB.dao.DataDao;
import DB.dao.DataDaoHibernateImpl;
import DB.model.Data;

import java.util.List;

public class DataServiceImpl implements DataService{

    DataDao dataDaoHibernate = new DataDaoHibernateImpl();
    @Override
    public void createDataTable() {
        dataDaoHibernate.createDataTable();
    }

    @Override
    public void dropDataTable() {
        dataDaoHibernate.dropDataTable();
    }

    @Override
    public void saveData(String data) {
        dataDaoHibernate.saveData(data);
    }

    @Override
    public void removeDataById(long id) {
        dataDaoHibernate.removeDataById(id);
    }

    @Override
    public List<Data> getAllData() {
        return dataDaoHibernate.getAllData();
    }

    @Override
    public void cleanDataTable() {
        dataDaoHibernate.cleanDataTable();
    }
}
