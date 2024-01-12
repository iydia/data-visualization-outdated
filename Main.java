package cpt;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.CheckBox;
import javafx.scene.chart.ScatterChart;

public class Main extends Application {

    // Tabpane
    private TabPane tabPane;
    private Tab tab1;
    private Tab tab2;
    
    // Tab1 Variables
    private LineChart chart1;
    private LineChart newChart1;

    // Tab2 Variables
    private ScatterChart chart2;
    private ScatterChart newChart2;

    /**
     * Method to create vbox scene and separate tabs for each chart
     * @return tabPane the database interface
     */
    public Parent createContent() {
        // Create instance of DataCollection
        DataCollection collection = new DataCollection();

        // Variables accessed by both tabs
        int intXLowerBound = collection.getData().get(0).getYear();
        int intXUpperBound = collection.getCurrentData().get(collection.getCurrentYear()).getYear();
        int intYLowerBound = collection.getSmallestGrowth(collection.getCurrentData());
        int intYUpperBound = collection.getLargestGrowth(collection.getCurrentData());
        int intXTickUnit = 1;
        int intYTickUnit = 2000000;
        int intNewXLowerBound = collection.getData().get(0).getYear();
        int intNewXUpperBound = collection.getData().get(collection.getFileLength()).getYear();
        int intNewYLowerBound = collection.getSmallestGrowth(collection.getData());
        int intNewYUpperBound = collection.getLargestGrowth(collection.getData());
    
        // Create instances of charts
        ScatterCharts scatterChart = new ScatterCharts(new NumberAxis("Years", intXLowerBound, intXUpperBound + intXTickUnit, intXTickUnit), new NumberAxis("Annual Population Growth", intYLowerBound, intYUpperBound + intYTickUnit, intYTickUnit));
        ScatterCharts newScatterChart = new ScatterCharts(new NumberAxis("Years", intNewXLowerBound, intNewXUpperBound, intXTickUnit), new NumberAxis("Annual Population Growth", intNewYLowerBound, intNewYUpperBound + intYTickUnit, intYTickUnit));
        LineCharts lineChart = new LineCharts(new NumberAxis("Years", intXLowerBound, intXUpperBound + intXTickUnit, intXTickUnit), new NumberAxis("Annual Population Growth", intYLowerBound, intYUpperBound + intYTickUnit, intYTickUnit));
        LineCharts newLineChart = new LineCharts(new NumberAxis("Years", intNewXLowerBound, intNewXUpperBound, intXTickUnit), new NumberAxis("Annual Population Growth", intNewYLowerBound, intNewYUpperBound + intYTickUnit, intYTickUnit));

        // Setup tabpane
        tabPane = new TabPane();
        tabPane.setSide(Side.LEFT);
        tabPane.setPrefSize(900, 700);
        tabPane.setMinSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPane.setMaxSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE); 
        tabPane.setSide(Side.TOP);

        // Make tabs
        tab1 = new Tab();
        tab2 = new Tab();

        // Create vbox
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setTranslateY(10);     

        // Tab1 Setup
        tab1.setText("Line Chart");
        tab1.setContent(vbox);
        final VBox vboxTab1 = new VBox();
        vboxTab1.setSpacing(10);
        vboxTab1.setTranslateY(10);
        vboxTab1.setStyle("-fx-padding: 16;");

        // Tab1 Title
        Text titleTab1 = new Text("Annual Population Growth, World");
        titleTab1.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Tab1 Checkbox
        CheckBox cb1 = new CheckBox("Show predicted population changes");

        // Tab1 Charts   
        chart1 = new LineChart(lineChart.getXAxis(), lineChart.getYAxis(), lineChart.lineChartCurrent());

        // Add Tab1 to tabpane
        vboxTab1.getChildren().addAll(titleTab1, cb1, chart1);
        tab1.setContent(vboxTab1);
        tabPane.getTabs().add(tab1);

        // Tab1 if checkbox is clicked
        cb1.setOnAction(new EventHandler<ActionEvent>() {
            int intCount = 0;
            @Override
            public void handle(ActionEvent event) {
                intCount++;
                if(intCount%2==1){
                    vboxTab1.getChildren().remove(chart1);

                    newChart1 = new LineChart(newLineChart.getXAxis(), newLineChart.getYAxis(), lineChart.lineChartFuture());

                    vboxTab1.getChildren().add(newChart1);
                    tab1.setContent(vboxTab1);

                // If checkbox is unclicked
                } else if(intCount%2==0){
                    vboxTab1.getChildren().remove(newChart1);
                    vboxTab1.getChildren().add(chart1);
                    tab1.setContent(vboxTab1);
                }
            }
        });
        
        // Tab2 Setup
        tab2.setText("Scatter Chart");
        tab2.setContent(vbox);
        final VBox vboxTab2 = new VBox();
        vboxTab2.setSpacing(10);
        vboxTab2.setTranslateY(10);
        vboxTab2.setStyle("-fx-padding: 16;");

        // Tab2 Title
        Text titleTab2 = new Text("Annual Population Growth, World");
        titleTab2.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        
        // Tab2 Checkbox
        CheckBox cb2 = new CheckBox("Show predicted population changes");

        // Tab2 Charts    
        chart2 = new ScatterChart(scatterChart.getXAxis(), scatterChart.getYAxis());
        chart2.getData().add(scatterChart.scatterChartCurrent());

        // Add Tab2 to tabpane
        vboxTab2.getChildren().addAll(titleTab2, cb2, chart2);
        tab2.setContent(vboxTab2);
        tabPane.getTabs().add(tab2);

        // Tab2 if checkbox is clicked
        cb2.setOnAction(new EventHandler<ActionEvent>() {
            int intCount = 0;
            @Override
            public void handle(ActionEvent event) {
                intCount++;
                if(intCount%2==1){
                    vboxTab2.getChildren().remove(chart2);

                    newChart2 = new ScatterChart(newScatterChart.getXAxis(), newScatterChart.getYAxis());
                    newChart2.getData().addAll(scatterChart.scatterChartCurrent(), scatterChart.scatterChartFuture());

                    vboxTab2.getChildren().add(newChart2);
                    tab2.setContent(vboxTab2);

                // If checkbox is unclicked
                } else if(intCount%2==0){
                    vboxTab2.getChildren().remove(newChart2);
                    vboxTab2.getChildren().add(chart2);
                    tab2.setContent(vboxTab2);
                }
            }
        });
        return tabPane;
    }

    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("World Annual Population Growth Database");
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    /**
     * Java main for when running without JavaFX launcher
     * @param args command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}