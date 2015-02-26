package struts.stage.utils;

import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart {
	public static void createPieChart(float menKm, float womenKm){
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Men", menKm);
		dataset.setValue("Women", womenKm);
		
		// create a chart...
		JFreeChart chart = ChartFactory.createPieChart(
		"Men vs Women",
		dataset,
		true, // legend?
		true, // tooltips?
		false // URLs?
		);
		
		try {
	        ChartUtilities.saveChartAsJPEG(new File("C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/storage/menVsWomenPieChart.jpg"),
	        		chart, 500, 500);
	    } catch (Exception e) {
	        System.err.println("couldn't write chart");
	    }
	}
}
