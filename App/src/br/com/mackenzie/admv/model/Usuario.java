package br.com.mackenzie.admv.model;

import java.util.Date;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author Vinicius
 *
 */
@XStreamAlias("Usuario")
public class Usuario {
	private String id;
	private String nome;
	private String sobrenome;
	private String tokenFoursquare;
	private String sexo;
	private Date dataCadastro;
	private List<Usuario> amigos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public String getTokenFoursquare() {
		return tokenFoursquare;
	}
	public void setTokenFoursquare(String tokenFoursquare) {
		this.tokenFoursquare = tokenFoursquare;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public List<Usuario> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Usuario> amigos) {
		this.amigos = amigos;
	}
}
