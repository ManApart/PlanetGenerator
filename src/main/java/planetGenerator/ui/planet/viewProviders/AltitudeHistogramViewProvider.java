package planetGenerator.ui.planet.viewProviders;

import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.Pane;
import planetGenerator.model.Planet;
import rak.utility.Histogram;

public class AltitudeHistogramViewProvider implements ViewProvider{
	private Histogram histogram;

	@Override
	public void drawView(Planet planet, Pane viewBackground) {
		createHistorgramMap(planet);
		drawHistogram(viewBackground);
	}

	private void createHistorgramMap(Planet planet) {
		histogram = new Histogram();
//		System.out.println("x,y,a");
		for (int x=0; x<planet.getSize(); x++){
			for (int y=0; y<planet.getSize(); y++){
				int altitude = planet.getRegion(x, y).getAltitude();
				histogram.add(altitude);
//				System.out.println(x + "," + y + "," + altitude);
			}
		}
	}

	private void drawHistogram(Pane viewBackground) {
		Series<String, Number> series = buildSeries();
		BarChart<String, Number> barChart = buildChart(series);
		viewBackground.getChildren().add(barChart);
	}

	private Series<String, Number> buildSeries() {
		Series<String, Number> series = new Series<>();
		
		for (int i=histogram.getMin(); i<= histogram.getMax(); i++){
			int value = histogram.getTotal(i);
			series.getData().add(new Data<String, Number>("" + i, value));
//			System.out.println(i + ": " + value);
		}
		return series;
	}
	
	private BarChart<String, Number> buildChart(Series<String, Number> series) {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Altitude");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Frequency of Region");
		
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
		barChart.setVerticalGridLinesVisible(false);
		barChart.setBarGap(0);
		barChart.getData().add(series);
		
		for(Node n:barChart.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: black;");
        }
		
		return barChart;
	}
	
	@Override
	public boolean allowOverlays(){
		return false;
	}
	
}
