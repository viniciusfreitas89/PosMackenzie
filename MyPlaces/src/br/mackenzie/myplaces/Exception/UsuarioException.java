package br.mackenzie.myplaces.Exception;
/**
 * 
 * @author Vinicius
 *
 */
public class UsuarioException extends Exception {
	private static final long serialVersionUID = -1030896081995078440L;
	public UsuarioException(){
	}
	public UsuarioException(String msg) {
		super(msg);
	}
	public UsuarioException(String msg, Throwable ex) {
		super(msg, ex);
	}
}
