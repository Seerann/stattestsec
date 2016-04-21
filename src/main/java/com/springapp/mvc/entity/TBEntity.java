package com.springapp.mvc.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Slame on 07.09.2015.
 */
public class TBEntity implements Serializable {

	private Integer id;
	private Date rudc;
	private Integer ruid;

	/**
	 * Get the value of ruid
	 *
	 * @return the value of ruid
	 */
	public Integer getRuid() {
		return ruid;
	}

	/**
	 * Set the value of ruid
	 *
	 * @param ruid new value of ruid
	 */
	public void setRuid(Integer ruid) {
		this.ruid = ruid;
	}

	/**
	 * Get the value of rudc
	 *
	 * @return the value of rudc
	 */
	public Date getRudc() {
		return rudc;
	}

	/**
	 * Set the value of rudc
	 *
	 * @param rudc new value of rudc
	 */
	public void setRudc(Date rudc) {
		if (null == rudc) {
			this.rudc = Calendar.getInstance().getTime();
		} else {
			this.rudc = rudc;
		}
	}

	/**
	 * Get the value of id
	 *
	 * @return the value of id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Set the value of id
	 *
	 * @param id new value of id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public TBEntity(Integer id, Date rudc, Integer ruid) {
		this.id = id;
		this.rudc = rudc;
		this.ruid = ruid;
	}

	public TBEntity() {
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final TBEntity other = (TBEntity) obj;
		if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
			return false;
		}
		if (this.rudc != other.rudc && (this.rudc == null || !this.rudc.equals(other.rudc))) {
			return false;
		}
		if (this.ruid != other.ruid && (this.ruid == null || !this.ruid.equals(other.ruid))) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}
}
