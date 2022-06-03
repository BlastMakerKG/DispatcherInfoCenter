package planning.respositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import planning.DTO.ExcavatorDTO;
import planning.model.Excavator;
import DB.util.*;


import java.sql.Date;
import java.util.List;

public class ExcavatorRepository {

//    @Query(value = "select * from excavator where name = ?1", nativeQuery = true)
    public List<ExcavatorDTO> getByName(String name){
        List<ExcavatorDTO> dates = null;

        Transaction transaction = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            dates = session.createQuery("select * from excavator where name = "+name).list();
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
