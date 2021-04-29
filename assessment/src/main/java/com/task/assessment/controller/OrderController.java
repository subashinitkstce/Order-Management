package com.task.assessment.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.assessment.model.Order;
import com.task.assessment.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class OrderController {
	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/orders")
	public Page<Order> getAllOrders(@PageableDefault(value = 100) Pageable page){
	
		return orderRepository.findAll(page);
	}
	/*public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}*/

	@PostMapping("/upload")
	public List<Order> migrateOrders(HttpServletRequest request) throws IOException, ServletException{
		final Part filePart = request.getPart("myFile");

		List<Order> orders = readOrdersFromCSV(filePart);
		//List<Order> orders = readOrdersFromCSV("D:\\Interview\\PaloIT\\newCSV.csv");
		for (Order order : orders) { 
			System.out.println(order);
		}
		System.out.println("Total number of records: " + orders.size());
		orderRepository.saveAll(orders);
		return orders;
	}
	private static String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(
						content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	private static List<Order> readOrdersFromCSV(Part filePart) throws IOException {
		final String fileName = getFileName(filePart);
		System.out.println(fileName);
		InputStream filecontent = null;
		filecontent = filePart.getInputStream();

		Scanner scanner = new Scanner(filecontent);
		List<Order> orders = new ArrayList<>();

		String line = scanner.nextLine();
		while (scanner.hasNextLine()) {
			line = scanner.nextLine();
			String[] attributes = line.split(","); 
			Order order = createOrder(attributes); 
			orders.add(order);
		} 
		scanner.close();
		return orders;


		/*List<Order> orders = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) { 
			br.readLine(); 
			String line = br.readLine();
			while (line != null) {
				String[] attributes = line.split(","); 
				Order order = createOrder(attributes); 
				orders.add(order);
				line = br.readLine(); 
			} 
		} catch (IOException ioe) {
			ioe.printStackTrace(); 
		} return orders; */
	} 
	private static Order createOrder(String[] metadata) {
		String nric = generateNRIC();
		String region = metadata[0];
		String country = metadata[1];
		String itemType = metadata[2];
		String salesChannel = metadata[3];
		String orderPriority = metadata[4];
		Date orderDate = new Date();
		long orderId = Long.parseLong(metadata[6]);
		Date shipDate = new Date();
		int unitsSold = Integer.parseInt(metadata[8]);
		float unitPrice = Float.parseFloat(metadata[9]);
		float unitCost = Float.parseFloat(metadata[10]);
		double totalRevenue = Double.parseDouble(metadata[11]);
		double totalCost = Double.parseDouble(metadata[12]);
		double totalProfit = Double.parseDouble(metadata[13]);
		try {
			orderDate = new SimpleDateFormat("mm/dd/yyyy").parse(metadata[5]);
			shipDate = new SimpleDateFormat("mm/dd/yyyy").parse(metadata[7]);
		}catch(Exception e) {}
		return new Order(nric, region, country, itemType, salesChannel, orderPriority,
				orderDate, orderId, shipDate, unitsSold, unitPrice, unitCost,
				totalRevenue, totalCost, totalProfit); 
	}
	public static String generateNRIC() {
		String firstChar ="STFG";
		String alphaSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder nric = new StringBuilder();
		Random r = new Random();
		nric.append(firstChar.charAt(r.nextInt(firstChar.length())));
		int x = (int)getRandomIntegerBetweenRange(1000000, 9999999);
		nric.append(x);
		nric.append(alphaSet.charAt(r.nextInt(alphaSet.length())));
		return nric.toString();
	}
	public static double getRandomIntegerBetweenRange(double min, double max){
		double x = (int)(Math.random()*((max-min)+1))+min;
		return x;
	}
}
