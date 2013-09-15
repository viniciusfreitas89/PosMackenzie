package util;

import java.util.List;

/**
 * 
 * @author Vinicius
 *
 */
public class UsuarioVO {
	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private String data_registro;
	private String senha;
	private String tokenFoursquare;
	private String sexo;
	private List<UsuarioVO> amigos;
	
	public UsuarioVO(){
	}
	
	public UsuarioVO(String email, String senha){
		this.email = email;
		this.senha = senha;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getData_registro() {
		return data_registro;
	}
	public void setData_registro(String data_registro) {
		this.data_registro = data_registro;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
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
	public List<UsuarioVO> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<UsuarioVO> amigos) {
		this.amigos = amigos;
	}
}
