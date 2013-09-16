package br.mackenzie.myplaces.vo;

/**
 * 
 * @author Vinicius
 *
 */
public class LocalVO {
	private Integer id_local;
	private String local;
	private String data_registro;
	private Long num_checkins;
	private Double latitude;
	private Double longitude;
	private Double valor_gasto;
	private CategoriaVO categoria;
	
	public Integer getId_local() {
		return id_local;
	}
	public void setId_local(Integer id_local) {
		this.id_local = id_local;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getData_registro() {
		return data_registro;
	}
	public void setData_registro(String data_registro) {
		this.data_registro = data_registro;
	}
	public Long getNum_checkins() {
		return num_checkins;
	}
	public void setNum_checkins(Long num_checkins) {
		this.num_checkins = num_checkins;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getValor_gasto() {
		return valor_gasto;
	}
	public void setValor_gasto(Double valor_gasto) {
		this.valor_gasto = valor_gasto;
	}
	public CategoriaVO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
	}
}
