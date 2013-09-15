package br.mackenzie.myplaces.vo;

/**
 * 
 * @author Vinicius
 *
 */
public class CategoriaVO {
	private long id;
	private String nome;
	private String icone;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getIcone() {
		return icone;
	}
	public void setIcone(String icone) {
		this.icone = icone;
	}
}
