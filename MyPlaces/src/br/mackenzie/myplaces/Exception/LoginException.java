package br.mackenzie.myplaces.Exception;

public class LoginException extends Exception {
	private static final long serialVersionUID = -1030896081995078440L;
	public LoginException(){
	}
	public LoginException(String msg) {
		super(msg);
	}
	public LoginException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
