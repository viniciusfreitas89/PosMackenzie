package br.mackenzie.myplaces.vo;

import java.sql.Date;

/**
 * 
 * @author Vinicius
 *
 */
public class LugarVO {
	private String id;
	private String nome;
	private CategoriaVO categoria;
	private String numero;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String pais;
	private long countCheckins;
	private Date dataCadastro;
	private CoordenadasVO coord;
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
	public CategoriaVO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaVO categoria) {
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
	public CoordenadasVO getCoord() {
		return coord;
	}
	public void setCoord(CoordenadasVO coord) {
		this.coord = coord;
	}
}
