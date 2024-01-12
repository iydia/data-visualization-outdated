package cpt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

public class LineCharts extends Charts{
    DataCollection collection = new DataCollection();
    
    /**
     * Construcotr for LineCharts
     * @param theXAxis x-axis inherited from Charts
     * @param theYAxis y-axis inherited from Charts
     */
    public LineCharts(NumberAxis theXAxis, NumberAxis theYAxis) {
        super(theXAxis, theYAxis);
    }

    /**
     * Method creates LineChart with integer axis for the filtered dataset of empirical data
     * @return the LineChart for the filtered dataset of empirical data
     */
    public ObservableList<Series<Integer, Integer>> lineChartCurrent() {
        ObservableList<XYChart.Data<Integer, Integer>> currentGrowthLineChart = FXCollections.observableArrayList();

        for(int i = 0; i <= collection.getCurrentYear(); i++) {
            currentGrowthLineChart.add(new XYChart.Data<>(collection.getCurrentData().get(i).getYear(), collection.getCurrentData().get(i).getPopulationGrowth()));
        }

        LineChart.Series<Integer, Integer> lineChart = new LineChart.Series<>("Annual Population Growth", currentGrowthLineChart);

        ObservableList<XYChart.Series<Integer,Integer>> lineChartData =
        FXCollections.observableArrayList();

        lineChartData.add(lineChart);

        return lineChartData;
    }

    /**
     * Method creates LineChart with integer axis for the filtered dataset of future values
     * @return the LineChart for the filtered dataset of future values
     */
    public ObservableList<Series<Integer, Integer>> lineChartFuture() {
        ObservableList<XYChart.Data<Integer, Integer>> currentGrowthLineChart = FXCollections.observableArrayList();
        ObservableList<XYChart.Data<Integer, Integer>> futureGrowthLineChart = FXCollections.observableArrayList();

        for(int i = 0; i <= collection.getCurrentYear(); i++) {
            currentGrowthLineChart.add(new XYChart.Data<>(collection.getCurrentData().get(i).getYear(), collection.getCurrentData().get(i).getPopulationGrowth()));
        }

        for(int i = collection.getCurrentYear(); i <= collection.getFileLength(); i++) {
            futureGrowthLineChart.add(new XYChart.Data<>(collection.getData().get(i).getYear(), collection.getData().get(i).getPopulationGrowth()));
        }

        LineChart.Series<Integer, Integer> lineChart = new LineChart.Series<>("Annual Population Growth", currentGrowthLineChart);
        LineChart.Series<Integer, Integer> newLineChart = new LineChart.Series<>("Future Population Growth", futureGrowthLineChart);

        ObservableList<XYChart.Series<Integer,Integer>> newLineChartData = FXCollections.observableArrayList();

        newLineChartData.add(lineChart);
        newLineChartData.add(newLineChart);

        return newLineChartData;
    }

    /**
     * Inherited abstract methods from parent class
     * @param arg0 the stage
     * @throws Exception
     */
    @Override
    public void start(Stage arg0) throws Exception { 
    }
}
