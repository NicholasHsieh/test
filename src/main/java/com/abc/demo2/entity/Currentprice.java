package com.abc.demo2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
public class Currentprice implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    
    private String encu;
	private String chcu;
    private float rate_float;

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	private String update_time;



	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}

	public String getEncu() {
		return encu;
	}

	public void setEncu(String encu) {
		this.encu = encu;
	}

	public String getChcu() {
		return chcu;
	}

	public void setChcu(String chcu) {
		this.chcu = chcu;
	}

	public float getRate_float() {
		return rate_float;
	}

	public void setRate_float(float rate_float) {
		this.rate_float = rate_float;
	}


}
