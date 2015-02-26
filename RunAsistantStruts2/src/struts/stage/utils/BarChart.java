package struts.stage.utils;

//import java.awt.Dimension;
import java.io.File;

import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
//import org.jfree.ui.ApplicationFrame;

//import org.jfree.ui.RefineryUtilities;

public class BarChart {

	//
	// private String label1;
	// private float value1;
	// private String label2;
	// private float value2;

	// public BarChar(String title, String label1, float value1, String label2,
	// float value2 ){
	// super(title);
	// this.label1 = label1;
	// this.label2 = label2;
	// this.value1 = value1;
	// this.value2 = value2;
	// }

//	public BarChart(String title) {
//		super(title);
//		// DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		// dataset.addValue(123.0, "MEN", "Gender");
//		// dataset.addValue(53.0, "WOMEN", "Gender");
//		// JFreeChart chart = ChartFactory.createBarChart(
//		// "MEN vs WOMEN", // chart title
//		// "", // domain axis label
//		// "KM", // range axis label
//		// dataset, // data
//		// PlotOrientation.VERTICAL, // orientation
//		// true, // include legend
//		// true, // tooltips?
//		// false // URLs?
//		// );
//		// ChartPanel chartPanel = new ChartPanel(chart, false);
//		// chartPanel.setPreferredSize(new Dimension(500, 270));
//		// setContentPane(chartPanel);
//	}

	public static void createBarChar(String title, String label1, float value1,
			String label2, float value2) {
		// BarChar barChar = new BarChar(title, label1, value1, label2, value2);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(value1, label1, "Gender");
		dataset.addValue(value2, label2, "Gender");
		JFreeChart chart = ChartFactory.createBarChart(
				label1 + " vs " + label2, // chart title
				"", // domain axis label
				"KM", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);

		try {
			ChartUtilities.saveChartAsJPEG(new File("C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/storage/menVsWomenBarChart.jpg"),
					chart, 500, 500);
		} catch (Exception e) {
			System.err.println("couldn't write chart");
		}
	}

//	 public static void main(String[] args) {
//	
//	 createBarChar("Men vs Women", "MEN", 234.0f, "WOMEN", 150.0f);
	
//	 BarChar demo = new BarChar
//	 ("Bar Demo 1");
//	 demo.pack();
//	 RefineryUtilities.centerFrameOnScreen(demo);
//	 demo.setVisible(true);
//	 }

	// public String getLabel1() {
	// return label1;
	// }
	//
	// public void setLabel1(String label1) {
	// this.label1 = label1;
	// }
	//
	// public float getValue1() {
	// return value1;
	// }
	//
	// public void setValue1(float value1) {
	// this.value1 = value1;
	// }
	//
	// public String getLabel2() {
	// return label2;
	// }
	//
	// public void setLabel2(String label2) {
	// this.label2 = label2;
	// }
	//
	// public float getValue2() {
	// return value2;
	// }
	//
	// public void setValue2(float value2) {
	// this.value2 = value2;
	// }
}
