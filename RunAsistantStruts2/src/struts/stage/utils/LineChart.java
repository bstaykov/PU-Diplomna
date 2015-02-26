package struts.stage.utils;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleEdge;

public class LineChart {
	public static void createLineChart(ArrayList<Float> kmByAge){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int fromAge = 0;
		int tillAge = 9;
		for (int i = 0; i < kmByAge.size(); i++) {
			dataset.addValue(kmByAge.get(i), "Classes", fromAge + " - " + tillAge);
			fromAge += 10;
			tillAge += 10;
		}
		
		JFreeChart chart = ChartFactory.createLineChart(
				"KM's by Age", // chart title
				"Age", // domain axis label
				"KM", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				false, // include legend
				true, // tooltips
				false // urls
				);
		
		//chart.addSubtitle(new TextTitle("Run more!!!"));
		TextTitle source = new TextTitle(
		"Source: Java In A Nutshell (4th Edition) "
		+ "by David Flanagan (O’Reilly)"
		);
		source.setFont(new Font("SansSerif", Font.PLAIN, 10));
		source.setPosition(RectangleEdge.BOTTOM);
		source.setHorizontalAlignment(HorizontalAlignment.RIGHT);
		chart.addSubtitle(source);
		chart.setBackgroundPaint(Color.white);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		
		// customise the range axis...
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		// customise the renderer...
		LineAndShapeRenderer renderer
		= (LineAndShapeRenderer) plot.getRenderer();
		renderer.setShapesVisible(true);
		renderer.setDrawOutlines(true);
		renderer.setUseFillPaint(true);
		renderer.setFillPaint(Color.white);
		
		try {
			ChartUtilities.saveChartAsJPEG(new File("C:/Users/Boycho/Desktop/FMI/workspace/RunAsistantStruts2/WebContent/images/storage/kmByAgeLineChart.jpg"),
					chart, 500, 500);
		} catch (Exception e) {
			System.err.println("couldn't write chart");
		}
	}
}
