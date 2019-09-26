package cn.demo.crm.condition;

import java.util.List;

public class PageList<T> {
	private Integer total;
	private List<T> rows;

	@Override
	public String toString() {
		return "PageList{" +
				"total=" + total +
				", rows=" + rows +
				'}';
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public PageList(Integer total, List<T> rows) {
		this.total = total;
		this.rows = rows;
	}
}
