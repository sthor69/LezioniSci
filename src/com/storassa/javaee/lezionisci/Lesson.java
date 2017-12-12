package com.storassa.javaee.lezionisci;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Lesson
 *
 */
@Entity
public class Lesson implements Serializable {


	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private double hours;
	private double pricePerHour;
	private int clientNum;
	private List<Client> clients;
	private String place;
	private String note;
	
	private static final long serialVersionUID = 1L;

	public Lesson() {
		super();
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getHours() {
		return hours;
	}

	public void setHours(double hours) {
		this.hours = hours;
	}

	public double getPricePerHour() {
		return pricePerHour;
	}

	public void setPricePerHour(double pricePerHour) {
		this.pricePerHour = pricePerHour;
	}

	public int getClientNum() {
		return clientNum;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
		this.clientNum = clients.size();
	}
	
	public void addClient(Client _client) {
		clients.add(_client);
		this.clientNum++;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

   
}
