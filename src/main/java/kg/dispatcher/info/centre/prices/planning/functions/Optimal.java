package kg.dispatcher.info.centre.prices.planning.functions;


import org.springframework.stereotype.Service;

/**
 * Рассчет оптимальных плановых значений смено-суточных параметров ЭТК
 */
@Service
public class Optimal {

    Equation equation;
    /**
     * оптимальная скорость дивжения самосвалов i-го типа при j-ом типе эксковатора
     *
     * @param mass_truck_max - максимальная грузоподьемность самосвала
     * @param mass_truck_norm - номинальная грузоподьемность самосвала
     * @param speed - средняяя скорость дивжени самосвала
     * @return
     */
//    public double optimal_speed(double mass_truck_max, double mass_truck_norm, double speed){
//
//        return Math.sqrt(waste.waste_for_ready_venicle(mass_truck_norm)/waste.cost_gas(mass_truck_max, speed));
//    }

    /**
     * *
     * оптимальное количество самосвалов i-го типа для обслуживания экскаватора j-го типа
     *
     * @param distance - растояние которое должен пройти самосвал
     * @param mass_truck_max - грузоподьемность саосвала аксимальная
     * @param mass_ex_max - грузоподьемность ковша макстмальная
     * @param speed -средняя скорость самосвала
     * @return
     */
    public  double optimal_quantity(double distance, double mass_ex_max, double mass_truck_max, double speed){
        return equation.quality_trucks(distance, mass_ex_max, mass_truck_max, speed);
    }
}
