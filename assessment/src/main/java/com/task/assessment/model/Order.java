package com.task.assessment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.GenerationType;
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String nric;
	private String region;
	private String country;
	private String itemType;
	private String salesChannel;
	private String orderPriority;
	private Date orderDate;
	private long orderId;
	private Date shipDate;
	private int unitsSold;
	private float unitPrice;
	private float unitCost;
	private double totalRevenue;
	private double totalCost;
	private double totalProfit;
	
	public Order() {
		super();
	}
	public Order(String nric, String region, String country, String itemType, String salesChannel, String orderPriority,
			Date orderDate, long orderId, Date shipDate, int unitsSold, float unitPrice, float unitCost,
			double totalRevenue, double totalCost, double totalProfit) {
		super();
		this.nric = nric;
		this.region = region;
		this.country = country;
		this.itemType = itemType;
		this.salesChannel = salesChannel;
		this.orderPriority = orderPriority;
		this.orderDate = orderDate;
		this.orderId = orderId;
		this.shipDate = shipDate;
		this.unitsSold = unitsSold;
		this.unitPrice = unitPrice;
		this.unitCost = unitCost;
		this.totalRevenue = totalRevenue;
		this.totalCost = totalCost;
		this.totalProfit = totalProfit;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSalesChannel() {
		return salesChannel;
	}
	public void setSalesChannel(String salesChannel) {
		this.salesChannel = salesChannel;
	}
	public String getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(String orderPriority) {
		this.orderPriority = orderPriority;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Date getShipDate() {
		return shipDate;
	}
	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	public int getUnitsSold() {
		return unitsSold;
	}
	public void setUnitsSold(int unitsSold) {
		this.unitsSold = unitsSold;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public float getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getTotalProfit() {
		return totalProfit;
	}
	public void setTotalProfit(double totalProfit) {
		this.totalProfit = totalProfit;
	}
	@Override
	public String toString() {
		return "Order [nric=" + nric + ", region=" + region + ", country=" + country + ", itemType=" + itemType
				+ ", salesChannel=" + salesChannel + ", orderPriority=" + orderPriority + ", orderDate=" + orderDate
				+ ", orderId=" + orderId + ", shipDate=" + shipDate + ", unitsSold=" + unitsSold + ", unitPrice="
				+ unitPrice + ", unitCost=" + unitCost + ", totalRevenue=" + totalRevenue + ", totalCost=" + totalCost
				+ ", totalProfit=" + totalProfit + "]";
	}
}
