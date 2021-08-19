package Server.DB.dao;

import Server.DB.model.Data;
import Server.DB.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DataDaoHibernateImpl implements DataDao{

    public DataDaoHibernateImpl() {

    }

    @Override
    public void createDataTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS Data(id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, data nvarchar(200) NOT NULL)").executeUpdate();
            transaction.commit();
            session.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void dropDataTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS Data").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveData(String data) {
        Data data1 = new Data(data);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            session.save(data1);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeDataById(long id) {
        try (Session session = Util.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Data data = session.get(Data.class, id);
            session.delete(data);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Data> getAllData() {
        List<Data> datas = new ArrayList<>();
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            datas = session.createQuery("FROM Data").list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return datas;
    }

    @Override
    public void cleanDataTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE table Data").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
