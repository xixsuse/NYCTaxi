package demo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import org.glassfish.tyrus.core.coder.PrimitiveDecoders.IntegerDecoder;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class MainDashBoardController implements Initializable {
	CategoryAxis xAxis = new CategoryAxis();
	NumberAxis yAxis = new NumberAxis();
	private final XYChart.Series<String, Long> series1 = new XYChart.Series<>();
	private final XYChart.Series<String, Long> series2 = new XYChart.Series<>();
	private final XYChart.Series<String, Long> series3 = new XYChart.Series<>();
	private final XYChart.Series<String, Double> fhvMinSeries = new XYChart.Series<>();
	private final XYChart.Series<String, Double> greenMinSeries = new XYChart.Series<>();
	private final XYChart.Series<String, Double> yellowMinSeries = new XYChart.Series<>();
	private final XYChart.Series<String, Integer> fhvDropseries = new XYChart.Series<>();
	private final XYChart.Series<String, Integer> greenDropSeries = new XYChart.Series<>();
	private final XYChart.Series<String, Integer> yellowDropSeries = new XYChart.Series<>();

	private final XYChart.Series<String, Double> fhvpickUp = new XYChart.Series<>();
	private final XYChart.Series<String, Double> greenPickUp = new XYChart.Series<>();
	private final XYChart.Series<String, Double> yellowPickUp = new XYChart.Series<>();

	// private ObservableList<Series<String, Integer>> AverageInf =
	// FXCollections.observableArrayList();
	private ArrayList<String> averageDaysNames = new ArrayList<>();
	private ArrayList<Double> averageDaysValue = new ArrayList<>();
	private boolean pause = true;
	TripInf t = new TripInf();

	@FXML
	BarChart<String, Long> tripsNoChart;
	@FXML
	BarChart<String, Double> minutesBC;
	@FXML
	LineChart<String, Double> averagTripsPerDayChart;
	@FXML
	Button b1;
	@FXML
	BarChart<String, Integer> dropOffBC;
	@FXML
	BarChart<String, Double> pickUpBC;

	public void initialize(URL location, ResourceBundle resources) {

		// yAxis.setLabel("Trips");
		// addToCharts();
		// addTripsPerDay();

		ActionEvent e = null;
		try {
			updateData(e);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	@FXML
	protected void addToCharts() {

		tripsNoChart.getData().clear();

		series1.getData().clear();
		series2.getData().clear();
		series3.getData().clear();

		series1.setName("FHV");
		series1.getData().add(new XYChart.Data<String, Long>("FHV", t.getFhvCounter()));
		// System.out.println(TripInf.fhvCounter);

		series2.setName("Yellow");
		series2.getData().add(new XYChart.Data<String, Long>("Yellow", TripInf.yellowCounter));

		series3.setName("Green");
		series3.getData().add(new XYChart.Data<String, Long>("Green", TripInf.greenCounter));

		tripsNoChart.getData().addAll(series1, series2, series3);
		// System.out.println("1) "+TripInf.fhvCounter);

	}

	@FXML
	protected void addminutes() {
		long tripsCounter[] = new long[10];

		tripsCounter[0] = t.getFhvCounter();
		tripsCounter[1] = TripInf.yellowCounter;
		tripsCounter[2] = TripInf.greenCounter;

		for (int i = 0; i < 3; i++)
			if (tripsCounter[i] == 0)
				tripsCounter[i] = 1;

		minutesBC.getData().clear();

		fhvMinSeries.getData().clear();
		greenMinSeries.getData().clear();
		yellowMinSeries.getData().clear();

		fhvMinSeries.setName("fhv");
		fhvMinSeries.getData()
				.add(new XYChart.Data<String, Double>("fhv", (double) TripInf.fhvMinutes / tripsCounter[0]));

		yellowMinSeries.setName("Yellow");
		yellowMinSeries.getData()
				.add(new XYChart.Data<String, Double>("Yellow", (double) TripInf.yellowMinutes / tripsCounter[1]));

		greenMinSeries.setName("Green");
		greenMinSeries.getData()
				.add(new XYChart.Data<String, Double>("Green", (double) TripInf.greenMinutes / tripsCounter[2]));

		minutesBC.getData().addAll(fhvMinSeries, greenMinSeries, yellowMinSeries);
		// System.out.println("2) "+TripInf.fhvCounter);
	}

	@FXML
	protected void addPickUp() {
	

		int days = TripInf.dates.size();
		if (days == 0)
			days = 1;
		pickUpBC.getData().clear();

		fhvpickUp.getData().clear();
		greenPickUp.getData().clear();
		yellowPickUp.getData().clear();

		fhvpickUp.setName("fhv");
		fhvpickUp.getData()
				.add(new XYChart.Data<String, Double>("fhv", (double) TripInf.fhvPickUp / days));

		yellowPickUp.setName("Yellow");
		yellowPickUp.getData()
				.add(new XYChart.Data<String, Double>("Yellow", (double) TripInf.yellowPickUp / days));

		greenPickUp.setName("Green");
		greenPickUp.getData()
				.add(new XYChart.Data<String, Double>("Green", (double) TripInf.greenPickup / days));

		pickUpBC.getData().addAll(fhvpickUp, greenPickUp, yellowPickUp);
		// System.out.println("2) "+TripInf.fhvCounter);
	}

	@FXML
	protected void addDropOff() {

		dropOffBC.getData().clear();

		fhvDropseries.getData().clear();
		greenDropSeries.getData().clear();
		yellowDropSeries.getData().clear();

		fhvDropseries.setName("fhv");
		fhvDropseries.getData().add(new XYChart.Data<String, Integer>("fhv", TripInf.noDropFhv));

		yellowDropSeries.setName("Yellow");
		yellowDropSeries.getData().add(new XYChart.Data<String, Integer>("Yellow", TripInf.noDropyellow));

		greenDropSeries.setName("Green");
		greenDropSeries.getData().add(new XYChart.Data<String, Integer>("Green", TripInf.noDropGreen));

		dropOffBC.getData().addAll(fhvDropseries, greenDropSeries, yellowDropSeries);

	}

	@FXML
	protected void addTripsPerDay() {
		XYChart.Series<String, Double> seriesA = new XYChart.Series<>();

		long numberOfTrips = t.getFhvCounter() + TripInf.yellowCounter + TripInf.greenCounter;
		int days = TripInf.dates.size();
		if (days == 0)
			days = 1;

		averageDaysValue.add((double) (numberOfTrips / days));
		int length = averageDaysValue.size();

		averageDaysNames.add("Day " + Integer.toString(days));
		if (length > 10) {
			averageDaysNames.remove(0);
			averageDaysValue.remove(0);
		}
		for (int i = 0; i < length - 1; i++)
			seriesA.getData().add(new XYChart.Data<String, Double>(averageDaysNames.get(i), averageDaysValue.get(i)));

		averagTripsPerDayChart.getData().clear();
		averagTripsPerDayChart.getData().add(seriesA);
		// System.out.println("3) "+TripInf.fhvCounter);
	}

	@FXML
	protected void updateData(ActionEvent e) throws InterruptedException {
		addTripsPerDay();

		if (pause) {
			pause = false;
			b1.setText("Pause");
		} else {
			pause = true;
			b1.setText("View Changes");
		}
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				javafx.application.Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
						addToCharts();
						addminutes();
						addTripsPerDay();
						addPickUp();
						addDropOff();

						if (pause) {
							timer.cancel();
							timer.purge();
						}
					}
				});
			}
		}, 0, 1500);

	}

}
