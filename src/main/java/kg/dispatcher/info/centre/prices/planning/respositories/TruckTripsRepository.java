package kg.dispatcher.info.centre.prices.planning.respositories;

import kg.dispatcher.info.centre.prices.planning.DTO.TruckTripsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Repository
public interface TruckTripsRepository extends JpaRepository<TruckTripsDTO, Long>, JpaSpecificationExecutor<TruckTripsDTO> {

 @Modifying
 @Query(value = "SELECT *\n" +
         "FROM trucktrip u\n" +
         "    inner join typeofwork t on t.id = u.typeofwork_id\n" +
         "WHERE t.work_name = ?1\n" +
         "order by u.arrival_time", nativeQuery = true)
 List<TruckTripsDTO> findObjectByTypeOfWork(String typeofwork);

 @Modifying
 @Query(value = "SELECT *\n" +
         "FROM trucktrip u\n" +
         "    inner join typeofwork t on t.id = u.typeofwork_id\n" +
         "WHERE t.work_name = ?1 and date(u.arrival_time) = date(?2)\n" +
         "order by u.arrival_time", nativeQuery = true)
 List<TruckTripsDTO> findObjectByTypeOfWorkAndTime(String typeofwork, Timestamp date);

 @Modifying
 @Query(value = "SELECT * FROM trucktrip u order by u.arrival_time", nativeQuery = true)
 List<TruckTripsDTO> findAllOrderBy();

 @Modifying
 @Query(value = "SELECT * FROM trucktrip u  WHERE date(u.arrival_time) = date(?1) order by u.arrival_time", nativeQuery = true)
 List<TruckTripsDTO> findByTimeOfComeLoading(Timestamp time_of_come_loading2);

 @Modifying
 @Query(value = "SELECT * FROM trucktrip \n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "WHERE date(arrival_time) = date(?1) and t.number = ?2 \n" +
         "order by arrival_time", nativeQuery = true)
 List<TruckTripsDTO> findByTimeOfComeLoadingaAndNumSamosval(Timestamp time_of_come_loading2, Integer num_samosval);

 @Modifying
 @Query(value = "select date(arrival_time) as d from trucktrip group by d order by d", nativeQuery = true)
 List<Date> findAllDateTime();

 @Modifying
 @Query(value = "select t.number from trucktrip\n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "inner join trucktype t2 on t.trucktype_id = t2.id\n" +
         "WHERE date(arrival_time) = ?1 and t2.name = ?2\n" +
         "group  by t.number", nativeQuery = true)
 List<Integer> findCountSamosvalbyDateAndType(Timestamp time_of_come_loading2, String type_samosval);

 @Modifying
 @Query(value = "select t.number from trucktrip \n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "where date(arrival_time) = date(?1) \n" +
         "group by t.number", nativeQuery = true)
 List<Integer> findNums(Timestamp date);

 @Query(value = "select max(trip_number) from trucktrip \n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "where date(arrival_time) = ?1 and t.number= ?2;", nativeQuery = true)
 Integer getMaxReise(Date date, Integer num);

 @Query(value = "SELECT e.name FROM trucktrip \n" +
         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "WHERE date(arrival_time) = date(?1) and e.name = ?2 \n" +
         "group by e.name", nativeQuery = true)
    List<String> findCountEx(Timestamp date, String ex);

 @Query(value = "select count(trip_number) from trucktrip where date(arrival_time) = date(?1) group by trip_number", nativeQuery = true)
 List<Integer> findbyReise(Timestamp time);

 @Query(value = "select e.name from trucktrip\n" +
         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "where date(arrival_time) = ?1\n" +
         "group by e.name" , nativeQuery = true)
 List<String> AllNameExByDay(Timestamp time);

 @Query(value = "select * from trucktrip\n" +
         " inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "where date(arrival_time) = ?1 and e.name = ?2 \n" +
         "order by arrival_time" , nativeQuery = true)
 List<TruckTripsDTO> findAllObjectByEx(Timestamp time, String name);

 @Query(value = "select t2.name from trucktrip\n" +
         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "inner join trucktype t2 on t.trucktype_id = t2.id\n" +
         "where date(arrival_time) = ?1 and e.name = ?2 \n" +
         "group by t2.name" , nativeQuery = true)
 List<String> findAllTypeSamsvalByNameEx(Timestamp time, String name);

 @Query(value = "select count(t.number) from trucktrip\n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
         "where date(arrival_time) = ?1 and e.name = ?2 and t2.name = ?3\n" +
         "group by t.number;" , nativeQuery = true)
 List<Integer> findQuantitySamByEx(Timestamp time, String name, String type);

 @Query(value ="select e.name from trucktrip\n" +
         "inner join excavator e on e.id = trucktrip.excavator_id\n" +
         "group by e.name;", nativeQuery = true)
 List<String> getAllTypeExs();

 @Query(value = "select t2.name from trucktrip\n" +
         "inner join truck t on t.id = trucktrip.truck_id\n" +
         "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
         "group by t2.name",nativeQuery = true )
 List<String> getAllTypeSamosval();




 // optimize work
 @Query(value = "select round(cast(sum(distance) as numeric), 5) as distance,\n" +
                "       sum(abs(gas_for_begin_loading-gas_for_begin_unloading)) as waste_gas,\n" +
                "       sum(weight_fact) as weight_fact, round(cast(sum(weight_norm) as numeric), 5) as weight_norm\n" +
                "from object\n" +
                "group by date(time_of_come_loading)",nativeQuery = true )
 List<String[]> getMonth();

 @Query(value = "select round(cast(sum(distance) as numeric), 5) as distance,\n" +
                "       sum(abs(gas_for_begin_loading-gas_for_begin_unloading)) as waste_gas,\n" +
                "       sum(weight_fact) as weight_fact," +
                "       round(cast(sum(weight_norm) as numeric), 5) as weight_norm\n" +
                "from object\n" +
                "where type_of_work = ?1\n" +
                "group by date(time_of_come_loading)",nativeQuery = true )
 List<String[]> getMonthByTypeOfWork(String type);


 @Query(value = "select name_ex,\n" +
                "       num_ex,\n" +
                "       type_samosval,\n" +
                "       sum(weight_fact) weight_fact,\n" +
                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
                "       round(cast(sum(distance) as numeric),5) distance,\n" +
                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
                "from object\n" +
                "where date(time_of_come_loading) = date(?1)\n" +
                "group by name_ex, num_ex, type_samosval",nativeQuery = true )
 List<String[]> getByExesByDay(Timestamp time);

 @Query(value = "select name_ex,\n" +
                "       num_ex,\n" +
                "       type_samosval,\n" +
                "       sum(weight_fact) weight_fact,\n" +
                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
                "       round(cast(sum(distance) as numeric),5) distance,\n" +
                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
                "from object\n" +
                "where date(time_of_come_loading) = date(?1)\n" +
                "      and type_of_work = ?2\n" +
                "group by name_ex, num_ex, type_samosval",nativeQuery = true )
 List<String[]> getByExesByDayByTypeOfWork(Timestamp time, String type);

 @Query(value = "select num_samosval,\n" +
                "       type_samosval,\n" +
                "       num_ex,\n" +
                "       (select count(reise)\n" +
                "       from object c\n" +
                "       where c.num_samosval = g.num_samosval\n" +
                "         and date(c.time_of_come_loading) = ?1) as count_reise,\n" +
                "       round(cast((select sum(b.distance / (extract(epoch from (b.time_of_begin_unloading - b.time_of_come_loading)) /\n" +
                "                                            3600)::double precision) / count(b.reise)\n" +
                "                   from object b\n" +
                "                   where date(b.time_of_come_loading) = '2019-11-01'\n" +
                "                     and b.num_samosval = g.num_samosval) as numeric), 5) as speed,\n" +
                "       sum(weight_fact) weight_fact,\n" +
                "       round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
                "       round(cast(sum(distance) as numeric),5) distance,\n" +
                "       sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
                "from object g\n" +
                "where date(time_of_come_loading) = ?1\n" +
                "group by num_samosval, type_samosval, num_ex;",nativeQuery = true )
 List<String[]> getBySumosvalByDay(Timestamp time);



 @Query(value = "select g.num_samosval,\n" +
         "       g.type_samosval,\n" +
         "       g.num_ex,\n" +
         "       (select count(reise)\n" +
         "        from object c\n" +
         "        where c.num_samosval = g.num_samosval\n" +
         "          and date(c.time_of_come_loading) = ?1) as count_reise,\n" +
         "       round(cast((select sum(b.distance / (extract(epoch from (b.time_of_begin_unloading - b.time_of_come_loading)) /\n" +
         "                                            3600)::double precision) / count(b.reise)\n" +
         "                   from object b\n" +
         "                   where date(b.time_of_come_loading) = date(?1)\n" +
         "                     and b.num_samosval = g.num_samosval) as numeric), 5) as speed,\n" +
         "             sum(weight_fact) weight_fact,\n" +
         "             round(cast(sum(weight_norm) as numeric), 5) weight_norm,\n" +
         "             round(cast(sum(distance) as numeric),5) distance,\n" +
         "             sum(abs(gas_for_begin_unloading-gas_for_begin_loading)) waste_gas\n" +
         "from object g\n" +
         "where date(time_of_come_loading) = ?1\n" +
         " and type_of_work = ?2\n" +
         "group by num_samosval, type_samosval, num_ex;",nativeQuery = true )
 List<String[]> getBySumosvalByDayByTypeOfWork(Timestamp time, String type);

 @Query(value = "select reise,\n" +
                "       round(cast((distance / (extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
                "                               3600)::double precision) as numeric), 5) as speed,\n" +
                "       round(cast(((extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
                "                    3600)::double precision) as numeric), 5) as time_inHour,\n" +
                "       (time_of_begin_unloading-object.time_of_come_loading)::time as time_inTime,\n" +
                "       abs(gas_for_begin_unloading-object.gas_for_begin_loading) as waste,\n" +
                "       distance\n" +
                "from object\n" +
                "where date(time_of_come_loading) = ?1\n" +
                "      and type_of_work = ?3\n" +
                "      and num_samosval = ?2",nativeQuery = true )
 List<String[]> getDataByDayByNumsumByTypeOfWork(Timestamp time, Integer num, String type);


 @Query(value = "select reise,\n" +
         "       round(cast((distance / (extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
         "                               3600)::double precision) as numeric), 5) as speed,\n" +
         "       round(cast(((extract(epoch from (time_of_begin_unloading - time_of_come_loading)) /\n" +
         "                    3600)::double precision) as numeric), 5) as time_inHour,\n" +
         "       (time_of_begin_unloading-object.time_of_come_loading)::time as time_inTime,\n" +
         "       abs(gas_for_begin_unloading-object.gas_for_begin_loading) as waste,\n" +
         "       distance\n" +
         "from object\n" +
         "where date(time_of_come_loading) = ?1\n" +
         "      and num_samosval = ?2",nativeQuery = true )
 List<String[]> getDataByDayByNumsum(Timestamp time, Integer num);

 /**
  * Функия при запросе БД
  * Для взятия данных о дне с определенным вида самосвалом, где берется самосвал с самым большим количеством рейсов
  * */
 @Query(value = "SELECT *\n" +
         "FROM trucktrip g\n" +
         "inner join excavator e on e.id = g.excavator_id\n" +
         "inner join truck t on t.id = g.truck_id\n" +
         "inner join trucktype t2 on t2.id = t.trucktype_id\n" +
         "WHERE date(g.arrival_time) = date(?1)\n" +
         "  and t.number = (select t4.number\n" +
         "                        from trucktrip o\n" +
         "                            inner join truck t4 on t4.id = o.truck_id\n" +
         "                            inner join trucktype t3 on t3.id = t4.trucktype_id\n" +
         "                        where date(o.arrival_time) = date(?1) and t3.name = ?2\n" +
         "                        group by t4.number\n" +
         "                        order by (select count(n.trip_number)\n" +
         "                                  from trucktrip n\n" +
         "                                    inner join truck t6 on t6.id = n.truck_id\n" +
         "                                    inner join trucktype t8 on t6.trucktype_id =t8.id\n" +
         "                                  where date(n.arrival_time) = date(?1) and t8.name = ?2 and t6.number = t4.number) desc\n" +
         "                        limit 1)\n" +
         "order by g.trip_number", nativeQuery = true)
 List<TruckTripsDTO> getObjectsByDateAndByTypeTruck(Timestamp time, String typeTruck);

}
