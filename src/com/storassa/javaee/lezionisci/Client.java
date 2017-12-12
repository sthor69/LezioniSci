package com.storassa.javaee.lezionisci;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
public class Client implements Serializable {
	
	@Id
	private String fiscalCode;
	
	private String name;
	private String surname;
	private int phone;
	private String address;
	private String note;

	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFiscalCode() {
		return fiscalCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}
	
	public Client(String _codFisc) {
		this();
		fiscalCode = _codFisc;
	}
   
}
