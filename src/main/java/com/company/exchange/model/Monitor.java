package com.company.exchange.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "post_Data_Monitor")
public class Monitor {

	@Id
	@Column(name = "size")
	private int size;

	@Column(name = "operation")
	private String operation;

	@Column(name = "status")
	private String httpStatus;

	public Monitor() {
	}

	public Monitor(int size, String operation, String httpStatus) {
		this.size = size;
		this.operation = operation;
		this.httpStatus = httpStatus;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
