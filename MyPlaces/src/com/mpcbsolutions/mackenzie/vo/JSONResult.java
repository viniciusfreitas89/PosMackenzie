package com.mpcbsolutions.mackenzie.vo;

import java.util.List;

import br.mackenzie.myplaces.vo.CategoriaVO;
import br.mackenzie.myplaces.vo.LocalVO;
import br.mackenzie.myplaces.vo.UsuarioVO;

/**
 * Objeto que representa o resultado JSON retornado pelo serviço de Login em PHP.
 * A URL de acesso para este serviço esta em: Constants.SERVICES_URL_LOGIN
 * @author Vinicius
 * 
 */
public class JSONResult {
	private boolean status;
	private String msg;
	private Integer id_local;
	private UsuarioVO usuario;
	private List<UsuarioVO> usuarios;
	private List<CategoriaVO> categorias;
	private List<LocalVO> timeline;
	private List<LocalVO> locais;
	
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
	public Integer getId_local() {
		return id_local;
	}
	public void setId_local(Integer id_local) {
		this.id_local = id_local;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	public List<UsuarioVO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioVO> usuarios) {
		this.usuarios = usuarios;
	}
	public List<CategoriaVO> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<CategoriaVO> categorias) {
		this.categorias = categorias;
	}
	public List<LocalVO> getTimeline() {
		return timeline;
	}
	public void setTimeline(List<LocalVO> timeline) {
		this.timeline = timeline;
	}
	public List<LocalVO> getLocais() {
		return locais;
	}
	public void setLocais(List<LocalVO> locais) {
		this.locais = locais;
	}
}
