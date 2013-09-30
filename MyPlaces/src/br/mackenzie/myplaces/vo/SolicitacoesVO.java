package br.mackenzie.myplaces.vo;

public class SolicitacoesVO {
	private int id;
	private String data_solicitacao;
	private UsuarioVO usuario;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData_solicitacao() {
		return data_solicitacao;
	}
	public void setData_solicitacao(String data_solicitacao) {
		this.data_solicitacao = data_solicitacao;
	}
	public UsuarioVO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
}
