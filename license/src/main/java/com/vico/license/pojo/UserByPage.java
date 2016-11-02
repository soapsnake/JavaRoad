package com.vico.license.pojo;

import java.util.List;

public class UserByPage {
	
	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFilterted;
	private List<User> data;
	private String error;
	
	public List<User> getData() {
		return data;
	}
	public void setData(List<User> data) {
		this.data = data;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFilterted() {
		return recordsFilterted;
	}
	public void setRecordsFilterted(Integer recordsFilterted) {
		this.recordsFilterted = recordsFilterted;
	}
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
