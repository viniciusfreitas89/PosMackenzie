package br.com.mackenzie.admv.model;

import java.sql.Date;

/**
 * 
 * @author Vinicius
 *
 */
public class Lugar {
	private String id;
	private String nome;
	private Categoria categoria;
	private String numero;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private long countCheckins;
	private Date dataCadastro;
	private Coordenadas coord;
	private double distancia;
	
	
	public double getDistancia() {
		return distancia;
	}
	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public long getCountCheckins() {
		return countCheckins;
	}
	public void setCountCheckins(long countCheckins) {
		this.countCheckins = countCheckins;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Coordenadas getCoord() {
		return coord;
	}
	public void setCoord(Coordenadas coord) {
		this.coord = coord;
	}
}
