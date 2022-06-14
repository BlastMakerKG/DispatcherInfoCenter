package kg.dispatcher.info.centre.prices.planning.functions;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Рптимальные решения планирования смено-суточных параметров ЭТК
 */
@Service
public class Solution {

    @Autowired
    Equation equation;

    private double min_value = 0;

    /**
     * Z_пj и M_пj - планок значения
     * Z_ij - относительные отклонения суточных затрат
     * M_ij - погрузочные-транспортные работы и их объемы
     * @param mass_truck_max -максимальное грузопоедесность самосвала
     * @param speed - скорость самосвала
     * @param distance - дистанция
     * @param mass_ex_max - максимальная груподьемность ковша
     * @param mass_truck_norm - номинальная грузоподьемность самосвала
     * @param give_money_for_work - величина суточной транспортно-выемочной удельной себестоимости
     * @param plan_ex_work -  план сменно-суточной отработки горного материала
     * @return - Будем оценивать эффективность плана ij-го ЭТК суммой квадратов относительных отклонений суточных затрат - Z_ij на погрузочно-транспортные работы и их объёмов - M_ij от их плановых значений Z_пj и M_пj
     */
//    public double effect_plan(double mass_truck_max, double speed, double distance, double mass_ex_max, double mass_truck_norm, double plan_ex_work, double give_money_for_work){
//
//        return Math.pow(
//                1 - waste.model_day_waste(mass_truck_max, speed, distance, mass_ex_max, mass_truck_norm)/(give_money_for_work + plan_ex_work), 2)
//                + Math.pow((1- (1 / plan_ex_work)), 2);
//    }


    /**
     * Эффективными будт считать такие поановые параметры автоколнные из н типа самосвалов (v_aij, N_aij) При к-ом экскаваторе,
     * которые доставля.т минимальные функции, при заланной  номинальной кмкости ковша - m_кj,
     * плане выемочный работ М_эi, пло=ановом премени Т_пб длительности цикла черпания t_ц, и плеча возки L_j
     *
     *  @param mass_truck_max -максимальное грузопоедесность самосвала
     *  @param speed - скорость самосвала
     *  @param distance - дистанция
     *  @param mass_ex_max - максимальная груподьемность ковша
     *  @param mass_truck_norm - номинальная грузоподьемность самосвала
     *  @param give_money_for_work - величина суточной транспортно-выемочной удельной себестоимости
     *  @param plan_ex_work -  план сменно-суточной отработки горного материала
     * @return - минмальное; Будем оценивать эффективность плана ij-го ЭТК суммой квадратов относительных отклонений суточных затрат - Z_ij на погрузочно-транспортные работы и их объёмов - M_ij от их плановых значений Z_пj и M_пj:
     */
//    public double min_value_parrameters( double mass_truck_max, double speed, double distance, double mass_ex_max, double mass_truck_norm, double plan_ex_work, double give_money_for_work ) {
//        double value =effect_plan(mass_truck_max, speed , distance, mass_ex_max, mass_truck_norm, plan_ex_work, give_money_for_work );
//        if(min_value == 0 || min_value > value) {
//            min_value = value;
//        }
//        return min_value;
//    }
}
