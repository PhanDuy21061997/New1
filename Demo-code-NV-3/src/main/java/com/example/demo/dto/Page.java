package com.example.demo.dto;

public class Page {

	private int Number;
	private int page;
	private int totalPage;
	private Object data;

	public Page(int number, int page, int totalPage, Object data) {
		super();
		this.Number = number;
		this.page = page;
		this.totalPage = totalPage;
		this.data = data;
	}

	public Page() {

	}

	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	
}
