package planning.respositories;

import org.hibernate.Session;
import org.hibernate.Transaction;
import planning.DTO.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;
import DB.util.*;

public class TruckTripsRespository {

 public List<TruckTripsDTO> findObjectByTypeOfWork(String typeofwork) {
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery(" SELECT * FROM trucktrip u inner join typeofwork t on t.id = u.typeofwork_id WHERE t.work_name = '" + typeofwork + "'order by u.arrival_time").list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }


 public List<TruckTripsDTO> findObjectByTypeOfWorkAndTime(String typeofwork, Timestamp date) {
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery("SELECT * FROM trucktrip u inner join typeofwork t on t.id = u.typeofwork_id WHERE t.work_name = " + typeofwork + " and date(u.arrival_time) = date( " + date + ") order by u.arrival_time").list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }

 public List<TruckTripsDTO> findAllOrderBy() {
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery("SELECT * FROM trucktrip u order by u.arrival_time").list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }

 public List<TruckTripsDTO> findByTimeOfComeLoading(Timestamp time_of_come_loading2) {
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery("SELECT * FROM trucktrip u  WHERE date(u.arrival_time) = date(" + time_of_come_loading2 + ") order by u.arrival_time").list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }


 public List<TruckTripsDTO> findByTimeOfComeLoadingaAndNumSamosval(Timestamp time_of_come_loading2, Integer num_samosval) {
  List<TruckTripsDTO> truckTripsDTOS = null;
  String query = "SELECT * FROM trucktrip \n" +
          "inner join truck t on t.id = trucktrip.truck_id\n" +
          "WHERE date(arrival_time) = date(" + time_of_come_loading2 + ") and t.number = " + num_samosval + " \n" +
          "order by arrival_time";
  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }

 public List<Date> findAllDateTime() {
  List<Date> dates = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   dates = session.createQuery("select date(arrival_time) as d from trucktrip group by d order by d").list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return dates;
 }


 public List<Integer> findCountSamosvalbyDateAndType(Timestamp time_of_come_loading2, String type_samosval) {
  String query = "select t.number from trucktrip\n" +
          "inner join truck t on t.id = trucktrip.truck_id\n" +
          "inner join trucktype t2 on t.trucktype_id = t2.id\n" +
          "WHERE date(arrival_time) = date(" + time_of_come_loading2 + ") and t2.name = " + type_samosval + "\n" +
          "group  by t.number";
  List<Integer> integers = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   integers = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return integers;
 }


 public List<Integer> findNums(Timestamp date) {
  String query = "select t.number from trucktrip \n" +
          "inner join truck t on t.id = trucktrip.truck_id\n" +
          "where date(arrival_time) = date(" + date + ") \n" +
          "group by t.number";
  List<Integer> integers = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   integers = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return integers;
 }

 public Integer getMaxReise(Date date, Integer num) {
  String query = "select max(trip_number) from trucktrip \n" +
          "inner join truck t on t.id = trucktrip.truck_id\n" +
          "where date(arrival_time) = ?1 and t.number= ?2";
  Integer s = 0;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   s = session.createQuery(query).getMaxResults();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return s;
 }


 public List<String> findCountEx(Timestamp date, String ex) {
  String query = "SELECT e.name FROM trucktrip \n" +
          "inner join excavator e on e.id = trucktrip.excavator_id\n" +
          "WHERE date(arrival_time) = date(" + date + ") and e.name = +" + ex + " \n" +
          "group by e.name";
  List<String> s = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   s = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return s;
 }

 public List<Integer> findbyReise(Timestamp time) {
  String query = "select count(trip_number) from trucktrip where date(arrival_time) = date(" + time + ") group by trip_number";
  List<Integer> integers = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   integers = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return integers;
 }



 public List<String> AllNameExByDay(Timestamp time){
  String query = "select e.name from trucktrip\n" +
          "inner join excavator e on e.id = trucktrip.excavator_id\n" +
          "where date(arrival_time) = "+time+"n" +
          "group by e.name";
  List<String> integers = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   integers = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return integers;
 }


 public List<TruckTripsDTO> findAllObjectByEx(Timestamp time, String name){
  String query = "select * from trucktrip\n" +
          " inner join excavator e on e.id = trucktrip.excavator_id\n" +
          "where date(arrival_time) = date("+time+") and e.name = "+name+" \n" +
          "order by arrival_time";
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }
//
// @Query(value = "select t2.name from trucktrip\n" +
//         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
//         "inner join truck t on t.id = trucktrip.truck_id\n" +
//         "inner join trucktype t2 on t.trucktype_id = t2.id\n" +
//         "where date(arrival_time) = ?1 and e.name = ?2 \n" +
//         "group by t2.name" , nativeQuery = true)
// List<String> findAllTypeSamsvalByNameEx(Timestamp time, String name);


 public List<Integer> findQuantitySamByEx(Timestamp time, String name, String type){
  String query = "select count(t.number) from trucktrip\n" +
          "inner join truck t on t.id = trucktrip.truck_id\n" +
          "inner join excavator e on e.id = trucktrip.excavator_id\n" +
          "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
          "where date(arrival_time) = "+time+" and e.name = "+name+" and t2.name = "+type+"\n" +
          "group by t.number";
  List<Integer> s = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   s = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return s;
 }

// @Query(value ="select e.name from trucktrip\n" +
//         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
//         "group by e.name;", nativeQuery = true)
// List<String> getAllTypeExs();
//
// @Query(value = "select t2.name from trucktrip\n" +
//         "inner join truck t on t.id = trucktrip.truck_id\n" +
//         "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
//         "group by t2.name",nativeQuery = true )
// List<String> getAllTypeSamosval();
//
//
//
//
// // optimize work
// @Query(value = "select round(cast(sum(distance) as numeric), 5) as distance,\n" +
//                "       sum(abs(gas_for_begin_loading-gas_for_begin_unloading)) as waste_gas,\n" +
//                "       sum(weight_fact) as weight_fact, round(cast(sum(weight_norm) as numeric), 5) as weight_norm\n" +
//                "from object\n" +
//                "group by date(time_of_come_loading)",nativeQuery = true )
// List<String[]> getMonth();
//
// @Query(value = "select round(cast(sum(distance) as numeric), 5) as distance,\n" +
//                "       sum(abs(gas_for_begin_loading-gas_for_begin_unloading)) as waste_gas,\n" +
//                "       sum(weight_fact) as weight_fact," +
//                "       round(cast(sum(weight_norm) as numeric), 5) as weight_norm\n" +
//                "from object\n" +
//                "where type_of_work = ?1\n" +
//                "group by date(time_of_come_loading)",nativeQuery = true )
// List<String[]> getMonthByTypeOfWork(String type);
//
//
// @Query(value = "select name_ex,\n" +
//                "       num_ex,\n" +
//                "       type_samosval,\n" +
//                "       sum(weight_fact) weight_fact,\n" +
//                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
//                "       round(cast(sum(distance) as numeric),5) distance,\n" +
//                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
//                "from object\n" +
//                "where date(time_of_come_loading) = date(?1)\n" +
//                "group by name_ex, num_ex, type_samosval",nativeQuery = true )
// List<String[]> getByExesByDay(Timestamp time);
//
// @Query(value = "select name_ex,\n" +
//                "       num_ex,\n" +
//                "       type_samosval,\n" +
//                "       sum(weight_fact) weight_fact,\n" +
//                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
//                "       round(cast(sum(distance) as numeric),5) distance,\n" +
//                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
//                "from object\n" +
//                "where date(time_of_come_loading) = date(?1)\n" +
//                "      and type_of_work = ?2\n" +
//                "group by name_ex, num_ex, type_samosval",nativeQuery = true )
// List<String[]> getByExesByDayByTypeOfWork(Timestamp time, String type);
//
// @Query(value = "select num_samosval,\n" +
//                "       type_samosval,\n" +
//                "       num_ex,\n" +
//                "       (select count(reise)\n" +
//                "       from object c\n" +
//                "       where c.num_samosval = g.num_samosval\n" +
//                "         and date(c.time_of_come_loading) = ?1) as count_reise,\n" +
//                "       round(cast((select sum(b.distance / (extract(epoch from (b.time_of_begin_unloading - b.time_of_come_loading)) /\n" +
//                "                                            3600)::double precision) / count(b.reise)\n" +
//                "                   from object b\n" +
//                "                   where date(b.time_of_come_loading) = '2019-11-01'\n" +
//                "                     and b.num_samosval = g.num_samosval) as numeric), 5) as speed,\n" +
//                "       sum(weight_fact) weight_fact,\n" +
//                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
//                "       round(cast(sum(distance) as numeric),5) distance,\n" +
//                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
//                "from object g\n" +
//                "where date(time_of_come_loading) = ?1\n" +
//                "group by num_samosval, type_samosval, num_ex;",nativeQuery = true )
// List<String[]> getBySumosvalByDay(Timestamp time);
//
//
//
// @Query(value = "select g.num_samosval,\n" +
//         "       g.type_samosval,\n" +
//         "       g.num_ex,\n" +
//         "       (select count(reise)\n" +
//         "        from object c\n" +
//         "        where c.num_samosval = g.num_samosval\n" +
//         "          and date(c.time_of_come_loading) = ?1) as count_reise,\n" +
//         "       round(cast((select sum(b.distance / (extract(epoch from (b.time_of_begin_unloading - b.time_of_come_loading)) /\n" +
//         "                                            3600)::double precision) / count(b.reise)\n" +
//         "                   from object b\n" +
//         "                   where date(b.time_of_come_loading) = date(?1)\n" +
//         "                     and b.num_samosval = g.num_samosval) as numeric), 5) as speed,\n" +
//         "             sum(weight_fact) weight_fact,\n" +
//         "             round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
//         "             round(cast(sum(distance) as numeric),5) distance,\n" +
//         "             sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
//         "from object g\n" +
//         "where date(time_of_come_loading) = ?1\n" +
//         " and type_of_work = ?2\n" +
//         "group by num_samosval, type_samosval, num_ex;",nativeQuery = true )
// List<String[]> getBySumosvalByDayByTypeOfWork(Timestamp time, String type);
//
// @Query(value = "select reise,\n" +
//                "       round(cast((distance / (extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
//                "                               3600)::double precision) as numeric), 5) as speed,\n" +
//                "       round(cast(((extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
//                "                    3600)::double precision) as numeric), 5) as time_inHour,\n" +
//                "       (time_of_begin_unloading-object.time_of_come_loading)::time as time_inTime,\n" +
//                "       abs(gas_for_begin_unloading-object.gas_for_begin_loading) as waste,\n" +
//                "       distance\n" +
//                "from object\n" +
//                "where date(time_of_come_loading) = ?1\n" +
//                "      and type_of_work = ?3\n" +
//                "      and num_samosval = ?2",nativeQuery = true )
// List<String[]> getDataByDayByNumsumByTypeOfWork(Timestamp time, Integer num, String type);
//
//
// @Query(value = "select reise,\n" +
//         "       round(cast((distance / (extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
//         "                               3600)::double precision) as numeric), 5) as speed,\n" +
//         "       round(cast(((extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
//         "                    3600)::double precision) as numeric), 5) as time_inHour,\n" +
//         "       (time_of_begin_unloading-object.time_of_come_loading)::time as time_inTime,\n" +
//         "       abs(gas_for_begin_unloading-object.gas_for_begin_loading) as waste,\n" +
//         "       distance\n" +
//         "from object\n" +
//         "where date(time_of_come_loading) = ?1\n" +
//         "      and num_samosval = ?2",nativeQuery = true )
// List<String[]> getDataByDayByNumsum(Timestamp time, Integer num);
//
// /**
//  * Функия при запросе БД
//  * Для взятия данных о дне с определенным вида самосвалом, где берется самосвал с самым большим количеством рейсов
//  * */

 public List<TruckTripsDTO> getObjectsByDateAndByTypeTruck(Timestamp time, String typeTruck){
  String query = "SELECT *\n" +
          "FROM trucktrip g\n" +
          "inner join excavator e on e.id = g.excavator_id\n" +
          "inner join truck t on t.id = g.truck_id\n" +
          "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
          "WHERE date(g.arrival_time) = date("+time+")\n" +
          "  and t.number = (select t4.number\n" +
          "                        from trucktrip o\n" +
          "                            inner join truck t4 on t4.id = o.truck_id\n" +
          "                            inner join trucktype t3 on t3.id = t4.trucktype_id\n" +
          "                        where date(o.arrival_time) = date(?1) and t3.name = "+typeTruck+"\n" +
          "                        group by t4.number\n" +
          "                        order by (select count(n.trip_number)\n" +
          "                                  from trucktrip n\n" +
          "                                    inner join truck t6 on t6.id = n.truck_id\n" +
          "                                    inner join trucktype t8 on t6.trucktype_id =t8.id\n" +
          "                                  where date(n.arrival_time) = date("+time+") and t8.name = "+typeTruck+" and t6.number = t4.number) desc\n" +
          "                        limit 1)\n" +
          "order by g.trip_number";
  List<TruckTripsDTO> truckTripsDTOS = null;

  Transaction transaction = null;
  try (Session session = Util.getSessionFactory().openSession()) {
   transaction = session.getTransaction();
   truckTripsDTOS = session.createQuery(query).list();
   transaction.commit();
   session.close();
  } catch (Exception e) {
   if (transaction != null) {
    transaction.rollback();
   }
  }
  return truckTripsDTOS;
 }


// @Query(value = "select type_of_work from object group by type_of_work", nativeQuery = true)
// List<String> getAllTypeOfWork();

}
