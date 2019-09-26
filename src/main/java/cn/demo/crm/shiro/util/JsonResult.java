package cn.demo.crm.shiro.util;

public class JsonResult {
	private boolean success = true;
	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public JsonResult(){}
	public JsonResult(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
}
