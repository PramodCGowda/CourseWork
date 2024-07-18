import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/InventoryReport")
public class InventoryReport extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		MySqlDataStoreUtilities mysqlUtil = new MySqlDataStoreUtilities();
		HashMap<String, ArrayList<Inventory>> inventory = mysqlUtil.getInventory();
		HashMap<String, ArrayList<Inventory>> onSaleInventory = mysqlUtil.getOnSaleInventory();
		HashMap<String, ArrayList<Inventory>> onDiscountInventory = mysqlUtil.getOnDiscountInventory();

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		pw.print(getInventoryTable(inventory));
		pw.print(getBarChart(inventory));
		pw.println(getProductOnSale(onSaleInventory));
		pw.println(getdiscountInventory(onDiscountInventory));
		pw.println(getContentFooter());
	}

	

	private String getBarChart(HashMap<String, ArrayList<Inventory>> inventory) {
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
		content +="data.addColumn('number', 'Inventory');";
		content += "data.addColumn({type: 'string', role: 'style'});";
		content +=" data.addRows([";
		for (Inventory product : inventory.get("all inventory")) {
			content +=" ['" + product.getName() + "', " + product.getQuantity() + ", 'color: #006600'],";
		}
		content +="]);";
		content +=" var options = {'title':'',";
		content +="   'width':1500,";
		content += "  height: 700,";
		content += "  bars: 'horizontal',";
		content += "  hAxis: {title: 'Inventory', minValue: 0},";
		content += "  vAxis: {title: 'Product Name', textPosition: 'out', titleTextStyle: {color: 'black'}, textStyle: {fontSize: 12}},";
		content += "};";
		content +=" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));";
		content +="  chart.draw(data, options);     }";
		content +=" </script>";
		content +="<div id='content'>";
		content +="<div class='post'>";
		content +="<h2 class='title meta'>";
		content +="<a style='font-size: 24px; color: #00cc00'>Bar Chart of Inventory</a>";
		content +="</h2><div class='entry'>";
		content +="<div id='chart_div'></div>";

		content +="</div></div>";
		return content;
	}

	private String getdiscountInventory(HashMap<String, ArrayList<Inventory>> inventory) {
		String content = "";
		content += "" + "<center><h2>On discount Inventory</h2></center>";
		for (Map.Entry<String, ArrayList<Inventory>> data : inventory.entrySet()) {
			String frame = data.getKey();
			ArrayList<Inventory> inv = data.getValue();
			if (frame.equalsIgnoreCase("error")) {
				content += "<center>No Data Available.</center>";
			} else {
				content += ""
						+ "<table id='example3' class='hover' cellspacing='0' width='100%'>"
						+ "<thead>"
						+ "<tr><th style='border: 1px solid black;'>ProductName</th>"
						+ "<th style='border: 1px solid black;'>ProductPrice</th>"
						+ "<th style='border: 1px solid black;'>ProductQuantity</th>"
						+ "<th style='border: 1px solid black;'>ProductDiscount</th></tr></thead>"
						+ "";

				content +="<tbody>";
				for(Inventory i: inv) {
					content += ""
							+ "<tr><td style='border: 1px solid black;'>"+i.getName()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getPrice()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getQuantity()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getDiscount()+"</td></tr>";
				}
				content +="</tbody>"
						+ "</table>";
			}
		}

		return  content;

	}

	private String getProductOnSale(HashMap<String, ArrayList<Inventory>> inventory) {
		String content = "";
		content += "" + "<center><h2>On Sale Inventory</h2></center>";
		for (Map.Entry<String, ArrayList<Inventory>> data : inventory.entrySet()) {
			String frame = data.getKey();
			ArrayList<Inventory> inv = data.getValue();
			if (frame.equalsIgnoreCase("error")) {
				content += "<center>No Data Available.</center>";
			} else {
				content += ""
						+ "<table id='example2' class='hover' cellspacing='0' width='100%'>"
						+ "<thead>"
						+ "<tr><th style='border: 1px solid black;'>ProductName</th>"
						+ "<th style='border: 1px solid black;'>ProductPrice</th>"
						+ "<th style='border: 1px solid black;'>ProductQuantity</th>"
						+ "";

				content +="<tbody>";
				for(Inventory i: inv) {
					content += ""
							+ "<tr><td style='border: 1px solid black;'>"+i.getName()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getPrice()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getQuantity()+"</td>";
				}
				content +="</tbody>"
						+ "</table>";

			}
		}

		return  content;
	}

	private String getContentFooter() {

		return "</section>" + "</div>";
	}

	private String getInventoryTable(HashMap<String, ArrayList<Inventory>> inventory) {
		String heading = "" + "<div id='body'>" + "<section id='content'>" + "";
		String content = "";
		content += "" + "<center><h2>Inventory</h2></center>";
		for (Map.Entry<String, ArrayList<Inventory>> data : inventory.entrySet()) {
			String frame = data.getKey();
			ArrayList<Inventory> inv = data.getValue();
			if (frame.equalsIgnoreCase("ERROR")) {
				content += "<center>No Data Available.</center>";
			} else {
				content += ""
						+ "<table id='example2' class='hover' cellspacing='0' width='100%' style='border: 1px solid black;'>"
						+ "<thead>"
						+ "<tr><th style='border: 1px solid black;'>ProductName</th>"
						+ "<th style='border: 1px solid black;'>ProductPrice</th>"
						+ "<th style='border: 1px solid black;'>ProductQuantity</th>"
						+ "";

				content +="<tbody>";
				for(Inventory i: inv) {
					content += ""
							+ "<tr><td style='border: 1px solid black;'>"+i.getName()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getPrice()+"</td>"
							+ "<td style='border: 1px solid black;'>"+i.getQuantity()+"</td>";
				}
				content +="</tbody>"
						+ "</table>";

			}
		}

		return heading + content;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
