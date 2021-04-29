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

	@PostMapping("/upload")
	public List<Order> migrateOrders(HttpServletRequest request) throws IOException, ServletException{
		final Part filePart = request.getPart("myFile");

		List<Order> orders = readOrdersFromCSV(filePart);
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
			if(order != null) {
				orders.add(order);
			}
		} 
		scanner.close();
		return orders;

	} 
	private static Order createOrder(String[] metadata) {
		System.out.println(metadata.length);
		String nric = generateNRIC();
		if(metadata.length>=14 && null!= nric && !"".equals(nric) && null!= metadata[0] && !"".equals(metadata[0])
				&& null!= metadata[1] && !"".equals(metadata[1]) && null!= metadata[2] && !"".equals(metadata[2])
				&& null!= metadata[3] && !"".equals(metadata[3]) && null!= metadata[4] && !"".equals(metadata[4])
				&& null!= metadata[5] && !"".equals(metadata[5]) && null!= metadata[6] && !"".equals(metadata[6])
				&& null!= metadata[7] && !"".equals(metadata[7]) && null!= metadata[8] && !"".equals(metadata[8])
				&& null!= metadata[9] && !"".equals(metadata[9]) && null!= metadata[10] && !"".equals(metadata[10])
				&& null!= metadata[11] && !"".equals(metadata[11]) && null!= metadata[12] && !"".equals(metadata[12])
				&& null!= metadata[13] && !"".equals(metadata[13])) {
			String region = metadata[0];
			String country = metadata[1];
			String itemType = metadata[2];
			String salesChannel = metadata[3];
			String orderPriority = metadata[4];
			try {
				Date orderDate = new SimpleDateFormat("mm/dd/yyyy").parse(metadata[5]);
				Date shipDate = new SimpleDateFormat("mm/dd/yyyy").parse(metadata[7]);
				long orderId = Long.parseLong(metadata[6]);
				int unitsSold = Integer.parseInt(metadata[8]);
				float unitPrice = Float.parseFloat(metadata[9]);
				float unitCost = Float.parseFloat(metadata[10]);
				double totalRevenue = Double.parseDouble(metadata[11]);
				double totalCost = Double.parseDouble(metadata[12]);
				double totalProfit = Double.parseDouble(metadata[13]);
				return new Order(nric, region, country, itemType, salesChannel, orderPriority,
						orderDate, orderId, shipDate, unitsSold, unitPrice, unitCost,
						totalRevenue, totalCost, totalProfit); 
			}catch(Exception e) {}
		}
		return null;
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
