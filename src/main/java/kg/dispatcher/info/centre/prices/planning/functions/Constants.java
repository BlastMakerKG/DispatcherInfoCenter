package kg.dispatcher.info.centre.prices.planning.functions;

public interface Constants {

    int plan_time_day_cycle = 20; // плановое время суточного цикла

    int cost_gas = 50;// стоимость 1-го литра горючего, руб/л

    double tranfer_factory_load_mass = 0.73; //коэффициент перевода заводской номинальной массы загрузки кузова самосвала в массу самого самосвала

    int approc_waste_ready_class0 = 4000; //коэффициенты аппроксимации функции затрат на обеспечение технической готовности машины,
    int approc_waste_ready_class1 = 7245;// определялись расчётным путём как разность фактически часовых затрат и часовых теоретических затрат
    double approc_waste_ready_class2 = 0.0145;//на горючее для каждого класса самосвалов

    double fact_load_landle = 0.45; // k_н^фак рассчитан по фактическим данным черпания и перевозки

    double opt_load_landle = 0.5; // k_н^opt задан как нормативная величина;

    double fact_load_carcase = 0.87; // k_з^фак рассчитан по фактическим данным черпания и перевозки,

    double opt_load_carcase = 0.9; // k_з^opt задан как нормативная величина;

    double t_time_cycle = 0.00963; // длительность цикла черпания-выгрузка-черпание, хронометражные данные.

    double specific_spentGas = 5.218 * Math.pow(10, -4) ; //удельные путевые затраты горючего, л/(МДж∙час)
}
