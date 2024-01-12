package cpt;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;

public class ScatterCharts extends Charts{
    DataCollection collection = new DataCollection();

    /**
     * ScatterCharts Constructor
     * @param theXAxis x-axis inherited from Charts
     * @param theYAxis y-axis inherited from Charts
     */
    public ScatterCharts(NumberAxis theXAxis, NumberAxis theYAxis) {
        super(theXAxis, theYAxis);
    }

    /**
     * Method creates ScatterChart with integer axis for the filtered dataset of empirical data
     * @return the ScatterChart for the filtered dataset of empirical data
     */
    public ScatterChart.Series<Integer, Integer> scatterChartCurrent(){
        final Series<Integer, Integer> currentGrowthScatterPlot = new Series<>();
        currentGrowthScatterPlot.setName("Annual Population Growth");

        for(int i = 0; i <= collection.getCurrentYear(); i++) {
            currentGrowthScatterPlot.getData().add(new XYChart.Data<>(collection.getCurrentData().get(i).getYear(), collection.getCurrentData().get(i).getPopulationGrowth()));
        }

        return currentGrowthScatterPlot;
    }

    /**
     * Method creates ScatterChart with integer axis for the filtered dataset of future values
     * @return the ScatterChart for the filtered dataset of future values
     */
    public ScatterChart.Series<Integer, Integer> scatterChartFuture(){
        final Series<Integer, Integer> futureGrowthScatterPlot = new Series<>();
        futureGrowthScatterPlot.setName("Future Population Growth");

        for(int i = collection.getCurrentYear(); i <= collection.getFileLength(); i++) {
            futureGrowthScatterPlot.getData().add(new XYChart.Data<>(collection.getData().get(i).getYear(), collection.getData().get(i).getPopulationGrowth()));
        }

        return futureGrowthScatterPlot;
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