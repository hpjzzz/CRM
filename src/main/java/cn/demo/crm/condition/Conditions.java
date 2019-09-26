package cn.demo.crm.condition;

public class Conditions {
	//当前页
	private Integer page=1;
	//起始数据
	private Integer offset=0;
	//行数
	private Integer rows = 10;

	@Override
	public String toString() {
		return "Conditions{" +
				"page=" + page +
				", offset=" + offset +
				", rows=" + rows +
				'}';
	}

	public Integer getOffset() {
		return (page-1)*rows;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
}
