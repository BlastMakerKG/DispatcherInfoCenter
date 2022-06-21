package kg.dispatcher.info.centre.prices.planning.reportDocument;

import kg.dispatcher.info.centre.prices.UI.ChartPanel;
import kg.dispatcher.info.centre.prices.planning.functions.Constants;
import kg.dispatcher.info.centre.prices.planning.model.Truck;

import java.util.List;

/**
 * Задание №6
 * 1.	Рассчитываем транспортные затраты по каждому рейсу - r, каждого i-го самосвала, по каждому виду работ и каждому экскаватору - j
 * 2.	Рассчитываем полные суточные затраты по добыче и вскрыше для каждого экскаватора:
 * 3.   Рассчитаем суточную грузоперевозку по каждому виду работ, для каждого экскаватора - M_сдj, M_свj
 * 4.   Рассчитаем среднюю, суточную себестоимость грузоперевозки по видам работ: ζ_сдj=Z_сдj/M_сдj  , ζ_свj=Z_свj/M_свj
 */

public class Excersice6 extends ChartPanel {


    /**
     *1.	Рассчитываем транспортные затраты по каждому рейсу - r, каждого i-го самосвала, по каждому виду работ и каждому экскаватору - j
     * @param specific_with_mass - удельные путевые расходы горючего гружённого самосвала соответствующего типа
     * @param specific_without_mass -удельные путевые расходы горючего порожнего с/с соответствующего типа на добыче и вскрыше
     * @param speed_with_mass - рейсовая скорость движения с/с на добыче и вскрыше
     * @param truck_mass - рейсовая масса материала в кузове с/с на добыче и вскрыше
     * @param timeInHour_with_mass - полное рейсовое время (с грузом и порожняком) r-го рейса с/с при j-ом экскаваторе
     * @param timeInhour_without полное рейсовое время  порожняком
     * @param truck_weight - вес самого самосвала
     * @param speed_without_mass - рейсовая скорость движения порожнего с/с на добыче и вскрыше
     */
    public double waste_day(double specific_with_mass, double specific_without_mass, double speed_with_mass, double truck_mass, double timeInHour_with_mass, double timeInhour_without, double truck_weight, double speed_without_mass){
        double waste_truck = (specific_with_mass*(truck_mass+truck_weight)* Math.pow(speed_with_mass, 2)*timeInHour_with_mass) + (specific_without_mass*truck_weight*Math.pow(speed_without_mass, 2)*timeInhour_without) ;
        double waste_ex = Constants.approc_waste_ready_class0 + Constants.approc_waste_ready_class1*(timeInHour_with_mass+timeInhour_without)+Math.pow(Math.E, (-Constants.approc_waste_ready_class2 * truck_mass));

        double waste_all = Constants.cost_gas*(waste_truck+ waste_ex);

        return waste_all;
    }


    /**
     *Рассчитываем полные суточные затраты по добыче и вскрыше для каждого экскаватора:
     *  @param sum_waste_day - транспортные затраты (вверхняя функция) просуммированная как по количеству рейсов да по количествутву с\с
     *                      (суммируется сперва по количесвам рейсов который сделал i-ый с\с, а потому суммируется с другими i-ыми с\с которые принадлежать j-му эксковатору)
     * @param ex_mass_max - предельная загрузка ковша экскаватора
     * @param time_works - суточная длительность добычных и вскрышных работ экскаваторов №2 и №7
     * @return
     */
    public double waste_day2 (double sum_waste_day, double ex_mass_max, double time_works, List<Truck> trucks){


        double sum_waste_truck_per_reice = 0;

        for(Truck tr: trucks){ // todo it's the trucks that include the ex and need to add parametr "List<Reice> reices" into object @Truck
            // todo warning!!!! after include need to create the second loop per Reice
            sum_waste_truck_per_reice += waste_day(tr.getSpecific_waste_with_mass(), tr.getSpecific_waste_without_mass(), tr.getSpeed(), tr.getWeight_fact(), 142.5 /* todo need to put the time in hours that the truck gone*/, 2352.5 /* todo need to put the time in hours that the truck come*/, 356 /* todo need to put the weight of truck*/, 25.5 /* todo need to put the speed without mass */);
        }


        double waste =  sum_waste_truck_per_reice * time_works*Math.pow(10,3)*4.5+Math.log10(ex_mass_max+1);
        return waste;
    }


    /**
     *Рассчитаем суточную грузоперевозку по каждому виду работ, для каждого экскаватора
     *
     * все параметры просумированы по 2 пунктам :
     *                  (суммируется сперва по количесвам рейсов который сделал i-ый с\с,
     *                  а потому суммируется с другими i-ыми с\с которые принадлежать j-му эксковатору)
     *  @param truck_mass -рейсовая масса материала в кузове с/с на добыче и вскрыше
     * @param distance - растояние до разгрузки
     * @return
     */
    public double waste_day3 (double truck_mass, double distance, List<Truck> trucks){


        double weight_fact = 0;
        double distnace = 0;
        for(Truck tr: trucks){
            // todo it's the trucks that include the ex and need to add parametr "List<Reice> reices" into object @Truck
            // todo warning!!!! after include need to create the second loop per Reice
            weight_fact += tr.getWeight_fact();
            distnace += tr.getDistance();
        }

        double waste = weight_fact*distnace;

        return waste;
    }


    /**
     * Рассчитаем среднюю, суточную себестоимость грузоперевозки по видам работ
     *
     * @param waste_day2 - описанные функции вверху
     * @param waste_day3
     */
    public void waste_day4(double waste_day2, double waste_day3, double ex_mass_max, double time_works, List<Truck> trucks){


        double waste2 = waste_day2( 142,ex_mass_max, time_works, trucks);

        double waste3 = waste_day3(213, 424, trucks);

        double waste = waste2/waste3;
    }



}
