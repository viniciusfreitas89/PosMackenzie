package br.mackenzie.myplaces.utils;

public class URLs {
	// SERVIÇOS PHP
	private static final String SERVICES_URL		 				= "http://www.mpcbsolutions.com/mackenzie/";
	
	public 	static final String SERVICES_URL_LOGIN	 				= SERVICES_URL+"usuarios/efetuarLogin";
	public 	static final String SERVICES_URL_CADASTRO_USUARIO		= SERVICES_URL+"usuarios/cadastrar";
	public 	static final String SERVICE_URL_CHECKIN					= SERVICES_URL+"usuarios/efetuarCheckin";
	public 	static final String SERVICE_URL_CARREGAR_TIMELINE		= SERVICES_URL+"usuarios/carregarTimeLine";
	
	public 	static final String SERVICES_URL_CADASTRO_LOCAL			= SERVICES_URL+"locais/cadastrarLocal";
	public 	static final String SERVICES_URL_LISTAR_TODOS			= SERVICES_URL+"locais/listar";
	
	public 	static final String SERVICES_URL_LISTAR_CATEGORIAS		= SERVICES_URL+"categorias/listar";
}
