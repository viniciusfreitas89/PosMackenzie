package com.mpcbsolutions.mackenzie.vo;

import br.mackenzie.myplaces.vo.UsuarioVO;

/**
 * Objeto que representa o resultado JSON retornado pelo serviço de Login em PHP.
 * A URL de acesso para este serviço esta em: Constants.SERVICES_URL_LOGIN
 * @author Vinicius
 * 
 */
public class LoginVO {
	private boolean status;
	private String msg;
	private UsuarioVO usuario;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
}
