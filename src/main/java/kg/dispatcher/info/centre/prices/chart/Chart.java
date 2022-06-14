package kg.dispatcher.info.centre.prices.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class Chart {

    JFreeChart chart = getChart();
    int width = 500;
    int height = 350;


    public JFreeChart getChart() {

        var dataset = new DefaultPieDataset();
        dataset.setValue("Croatia", 22);
        dataset.setValue("Bohemia", 34);
        dataset.setValue("Bulgaria", 18);
        dataset.setValue("Spain", 5);
        dataset.setValue("Others", 21);

        JFreeChart chart = ChartFactory.createPieChart("Popular destinations",
                dataset, true, false, false);

        chart.setBorderVisible(false);

        return chart;
    }
}
