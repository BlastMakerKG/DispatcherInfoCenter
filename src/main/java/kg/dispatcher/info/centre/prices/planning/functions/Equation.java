package kg.dispatcher.info.centre.prices.planning.functions;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

/**
    Вычисление затрат
 */
@Service
public class Equation {

    /**
     * *
     * это суточное количество самосвалов i-го типа для обслуживания экскаватора j-го типа
     *
     * @param distance - растояние которое должен пройти самосвал
     * @param mass_truck_norm - грузоподьемность саосвала нормальная
     * @param mass_ex_max - грузоподьемность ковша максимальная
     * @param speed -средняя скорость самосвала
     * @return
     */
    public double quality_trucks(double distance, double mass_ex_max, double mass_truck_norm, double speed){
        return Precision.round(2*distance*1*mass_ex_max, 3)/Precision.round(1*  mass_truck_norm * speed *Constants.t_time_cycle, 3); // todo contants need to change!!!
    }

    /**
     *стоимость горючего, израсходованного на перевозку плановой массы горного материала
     * 〖0,5c〗_e m_aiн v_aij^2 [q_ei k_зi+0,73(q_ei+q_iп )]
     *
     * @param mass_truck_norm - номнальная грузоподьемность самосвала
     * @param speed_go - средняяя скорость дивжени самосвала
     * @param mass_truck_max
     * @param speed_come
     * @param timeInhours_come
     * @param timeInHours_go
     * @param waste_gas_with_mass
     * @param waste_gas_without_mass
     * @return
     */
    public double cost_gas(double mass_truck_norm, double speed_go , double waste_gas_with_mass, double waste_gas_without_mass, double timeInHours_go,
                           double timeInhours_come, double mass_truck_max, double speed_come){

        double specific_gas_with_mass = specific_gas(waste_gas_with_mass, mass_truck_max, timeInHours_go, speed_go);
        double specific_gas_without_mass = specific_gas(waste_gas_without_mass, mass_truck_norm, timeInhours_come, speed_come);
        double sum_of_two_specific_gas = specific_gas_with_mass + specific_gas_without_mass;

        double multiplication_with_073 = sum_of_two_specific_gas * 0.73;

        double sum_the_second_time = specific_gas_with_mass + multiplication_with_073;

        double pow_the_speed = Math.pow(speed_go,2);

        double multiplication_speed_and_inBrakes = sum_the_second_time * pow_the_speed;

        double the_rest_multiplications = 0.5*Constants.cost_gas*mass_truck_norm;

        return the_rest_multiplications* multiplication_speed_and_inBrakes;
    }

    public double cost_gas(double mass_truck_norm, double speed, double specific_waste_with_mass, double specific_waste_without_mass){

        return 0.5*Constants.cost_gas*mass_truck_norm * Math.pow(speed,2) *(specific_waste_with_mass*1 + 0.73*(specific_waste_with_mass + specific_waste_without_mass));
    }

    /**
     *связанные с обеспечением готовности транспортных средств ГТК
     * Z_тг=a_0+a_1 exp(-a_2 m_aij );
     * @param mass_truck_norm - номинальная грузоподьемность самосвала
     * @return
     */
    public double waste_for_ready_venicle(double mass_truck_norm){

//        double the_pow_for_exp = -(Constants.approc_waste_ready_class2 * mass_truck_norm);
//
//        double the_powing = Math.pow(Math.E, the_pow_for_exp);
//
//        double multiplication_after_powing = Constants.approc_waste_ready_class1*the_powing;
//
//        return Constants.approc_waste_ready_class0 + multiplication_after_powing;


        return (Constants.approc_waste_ready_class0 + Constants.approc_waste_ready_class1*Precision.round(Math.pow(Math.E, -(Constants.approc_waste_ready_class2 * mass_truck_norm)),2)) ;
    }

    /**
     *затраты, связанные с работой экскаватора
     * 〖10〗^3 4,5 ln(m_кj+1) T_п
     * @param mass_ex_max - грузоподьемность ковша максимальная
     * @return
     */
    public double waste_for_ex(double mass_ex_max){
        return (4500 * Math.log(mass_ex_max + 1))*Constants.plan_time_day_cycle;
    }

    /**
     * удельные расходы по самосвалу с грузом и без
     *
     * Входные данные:
     *      для груза это вес_факт. + вес_самосвала
     *      без груза это вес_самосвала
     *
     * затраты горючего на работу самосвала с рудой
     * q_e = waste_gas/ (m * speed * time)
     * @param mass_truck_max
     * @param speed
     * @param waste_gas
     * @param timeInHours
     * */
    public double specific_gas(double waste_gas, double mass_truck_max, double timeInHours, double speed){
        return waste_gas/ (mass_truck_max*Math.pow(speed, 2)*timeInHours);
    }



    /**
     * заданной суточной добычи
     *[〖0,5c〗_e m_aiн v_aij^2 [q_ei k_зi+0,73(q_ei+q_iп )]+[a_0+a_1 exp(-a_2 m_aiн )]] T_п N_аij+[〖10〗^3 4,5 ln⁡(m_кj+1) ] T_п
     * @param mass_truck_max - максимальная грухоподьемность самосвала по кузова которая написано в документации
     * @param speed_go - средняя скорость самосвала
     * @param distance - растояние которое должен пройти самосвал
     * @param mass_ex_max - грузоподьмеость ковша экскаватора
     * @param mass_truck_norm - вес груза
     * @param timeInHours_go - время в часах которое он потратил на транспортировку руды
     * @param speed_come - скорость с которрой возвращался самосвал
     * @param timeInHours_come - время в часаъ, потраченное на возвращение
     * @param waste_gas_come - топливо потраченное на возвращение
     * @param waste_gas_go - топливо потраченное на транспортировку руды
     * @return
     */
    public double model_day_waste(double mass_truck_max, double speed_go, double distance, double mass_ex_max, double mass_truck_norm, double waste_gas_go,
                                  double timeInHours_go, double waste_gas_come, double speed_come, double timeInHours_come){
        return (Precision.round(cost_gas(mass_truck_norm, speed_go, waste_gas_go, waste_gas_come, timeInHours_go, timeInHours_come, mass_truck_max, speed_come),3) +
                Precision.round(waste_for_ready_venicle(mass_truck_norm),3)) *
                        Constants.plan_time_day_cycle*Precision.round(quality_trucks(distance, mass_ex_max, mass_truck_norm, speed_go), 3)+
                        Precision.round(waste_for_ex(mass_ex_max), 3);
    }


    public double model_day_waste(double speed, double mass_truck_norm, double mass_ex_max, double distance,double specific_waste_with_mass, double specific_waste_without_mass){
        return (cost_gas(mass_truck_norm, speed, specific_waste_with_mass,specific_waste_without_mass) +
                waste_for_ready_venicle(mass_truck_norm)) *
                Constants.plan_time_day_cycle *
                quality_trucks(distance, mass_ex_max, mass_truck_norm, speed) +
                waste_for_ex(mass_ex_max);
    }
}
