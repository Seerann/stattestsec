package com.springapp.mvc.entity;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Slame on 08.09.2015.
 */
@XmlType
@XmlRootElement(name = "tcversions")
public class Tcversions extends TBEntity implements Serializable {

	private Integer id;
	private Integer status;
	private String summary;
	private Integer importance;
	private Date creation_ts;
	private Integer updater_id;
	private boolean active;
	private Integer cnt;

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public Tcversions(Integer id, Date rudc, Integer ruid, Integer id1, Integer status, String summary, Integer importance, Date creation_ts, Integer updater_id, boolean active) {
		super(id, rudc, ruid);
		id = id1;
		this.status = status;
		this.summary = summary;
		this.importance = importance;
		this.creation_ts = creation_ts;
		this.updater_id = updater_id;
		this.active = active;
	}

	public Tcversions() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getImportance() {
		return importance;
	}

	public void setImportance(Integer importance) {
		this.importance = importance;
	}

	public Date getCreation_ts() {
		return creation_ts;
	}

	public void setCreation_ts(Date creation_ts) {
		this.creation_ts = creation_ts;
	}

	public Integer getUpdater_id() {
		return updater_id;
	}

	public void setUpdater_id(Integer updater_id) {
		this.updater_id = updater_id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
