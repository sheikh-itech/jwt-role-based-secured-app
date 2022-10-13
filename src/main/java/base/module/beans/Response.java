package base.module.beans;

import org.springframework.http.HttpStatus;

public class Response {

	private Object data;
	private HttpStatus status;
	private String message;
	private String error;
	
	public Response() {}
	
	public Response(Object data, String message, HttpStatus status, String error) {
		super();
		this.data = data;
		this.message = message;
		this.status = status;
		this.error = error;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
