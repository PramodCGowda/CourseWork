import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MySqlDataStoreUtilities mysqlUtil = new MySqlDataStoreUtilities();
		HashMap<String, ArrayList<Sales>> allSales = mysqlUtil.getAllSale();
		HashMap<String, ArrayList<DailySale>> dailySales = mysqlUtil.getDailySale();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print(getSaleReport(allSales));
		pw.print(getSalesChart(allSales));
		pw.println(getDailySale(dailySales));
		pw.println(getContentFooter());
	}

	private String getSalesChart(HashMap<String, ArrayList<Sales>> allSales) {
		String content = "";
		content += "" + "<center><h2>Bar Chart</h2></center>";
		content += "<div id='barchart_div' style='border:1px solid #CCC'></div>";
		content +="<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>";
		content +="<script type='text/javascript'>";
		content +="google.charts.load('current', {'packages':['corechart']});";
		content +="google.charts.setOnLoadCallback(drawChart);";
		content +="function drawChart() {";

		content +="var data = new google.visualization.DataTable();";
		content +="data.addColumn('string', 'Product Name', 'productName');";
		content +="data.addColumn('number', 'TotalSales');";
		content += "data.addColumn({type: 'string', role: 'style'});";
		content +=" data.addRows([";
		for (Sales product : allSales.get("sales")) {
			content +=" ['" + product.getName() + "', " + product.getTotalSale() + ", 'color: #006600'],";
		}
		content +="]);";
		content +=" var options = {'title':'',";
		content +="   'width':1500,";
		content += "  height: 700,";
		content += "  bars: 'horizontal',";
		content += "  hAxis: {title: 'TotalSales', minValue: 0},";
		content += "  vAxis: {title: 'Product Name', textPosition: 'out', titleTextStyle: {color: 'black'}, textStyle: {fontSize: 12}},";
		content += "};";
		content +=" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));";
		content +="  chart.draw(data, options);     }";
		content +=" </script>";
		content +="<div id='content'>";
		content +="<div class='post'>";
		content +="<h2 class='title meta'>";
		content +="<a style='font-size: 24px; color: #00cc00'>Bar Chart of Sales</a>";
		content +="</h2><div class='entry'>";
		content +="<div id='chart_div'></div>";

		content +="</div></div>";
		return content;
	}

	private String getDailySale(HashMap<String, ArrayList<DailySale>> sales) {
		String content = "";
		content += "" + "<center><h2>Daily Sales</h2></center>";
		for (Map.Entry<String, ArrayList<DailySale>> data : sales.entrySet()) {
			String frame = data.getKey();
			ArrayList<DailySale> inv = data.getValue();
			if (frame.equalsIgnoreCase("error")) {
				content += "<center>No Data Available.</center>";
			} else {
				content += ""
						+ "<table id='example2' class='hover' cellspacing='0' width='100%' style='border: 1px solid black;'>"
						+ "<thead>"
						+ "<tr><th style='border: 1px solid black;'>Date</th>"
						+ "<th style='border: 1px solid black;'>No. Item Sold</th>"
						+ "<th style='border: 1px solid black;'>Total Sale</th>"
						+ "";

				content +="<tbody>";
				for(DailySale i: inv) {
					content += ""
							+ "<tr><td style='border: 1px solid black;'>"+i.getDate()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getQuantity()+"</td>"
							+ "<td style='border: 1px solid black;'>"+Utilities.roundDecimal(i.getPrice())+"</td>";
				}
				content +="</tbody>"
						+ "</table>";

			}
		}

		return content;
	}

	private String getSaleReport(HashMap<String, ArrayList<Sales>> sales) {
		String heading = "" + "<div id='body'>" + "<section id='content'>" + "";
		String content = "";
		content += "" + "<center><h2>Sales</h2></center>";
		for (Map.Entry<String, ArrayList<Sales>> data : sales.entrySet()) {
			String frame = data.getKey();
			ArrayList<Sales> inv = data.getValue();
			if (frame.equalsIgnoreCase("error")) {
				content += "<center>No Data Available.</center>";
			} else {
				content += ""
						+ "<table id='example2' class='hover' cellspacing='0' width='100%' style='border: 1px solid black;'>"
						+ "<thead>"
						+ "<tr><th style='border: 1px solid black;'>ProductName</th>"
						+ "<th style='border: 1px solid black;'>ProductPrice</th>"
						+ "<th style='border: 1px solid black;'>ProductQuantity</th>"
						+ "<th style='border: 1px solid black;'>Total Sale</th>"
						+ "";

				content +="<tbody>";
				for(Sales i: inv) {
					content += ""
							+ "<tr><td style='border: 1px solid black;'>"+i.getName()+"</td>"
							+ "<td style='border: 1px solid black;'>"+Utilities.roundDecimal(i.getPrice())+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getQuantity()+"</td>"
							+ "<td style='border: 1px solid black;'>"+Utilities.roundDecimal(i.getTotalSale())+"</td>";
				}
				content +="</tbody>"
						+ "</table>";

			}
		}

		return heading + content;
	}

	private String getContentFooter() {

		return "</section>" + "</div>";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
