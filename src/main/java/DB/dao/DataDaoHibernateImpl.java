package DB.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import DB.util.Util;
import DB.model.Data;
import java.util.ArrayList;
import java.util.List;

public class DataDaoHibernateImpl implements DataDao{

    public DataDaoHibernateImpl() {

    }

    @Override
    public void createDataTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("CREATE TABLE IF NOT EXISTS Data(id SERIAL PRIMARY KEY NOT NULL, data varchar NOT NULL)").executeUpdate();
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
            session.createQuery("DROP TABLE IF EXISTS Data").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void saveData(String data1) {
        Data data = new Data(data1);
        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            session.save(data);
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
            session.createQuery("TRUNCATE table Data").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
