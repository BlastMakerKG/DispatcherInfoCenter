package planning.respositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import planning.DTO.TruckTypeDTO;
import DB.util.*;

import java.util.List;

public class TruckTypeRepository {

    public List<TruckTypeDTO> getByName(String name){
        List<TruckTypeDTO> dates = null;

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            dates = session.createQuery("select * from trucktype where name = "+name).list();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return dates;
    }
}
