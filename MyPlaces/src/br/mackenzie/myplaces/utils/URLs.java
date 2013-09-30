package br.mackenzie.myplaces.utils;

public class URLs {
	// SERVIÇOS PHP
	private static final String SERVICES_URL		 				= "http://www.mpcbsolutions.com/mackenzie/";
	
	public 	static final String SERVICES_URL_LOGIN	 				= SERVICES_URL+"usuarios/efetuarLogin";
	public 	static final String SERVICES_URL_CADASTRO_USUARIO		= SERVICES_URL+"usuarios/cadastrar";
	public 	static final String SERVICE_URL_CHECKIN					= SERVICES_URL+"usuarios/efetuarCheckin";
	public 	static final String SERVICE_URL_CARREGAR_TIMELINE		= SERVICES_URL+"usuarios/carregarTimeLine";
	public 	static final String SERVICE_URL_BUSCAR_USUARIOS			= SERVICES_URL+"usuarios/buscarUsuario";
	public 	static final String SERVICE_URL_LISTAR_AMIGOS			= SERVICES_URL+"usuarios/listarAmigos";
	public 	static final String SERVICE_URL_ADICIONAR_AMIGO			= SERVICES_URL+"usuarios/solicitarAmizade";
	public 	static final String SERVICE_URL_LISTAR_SOLICITACOES		= SERVICES_URL+"usuarios/listarSolicitacoesAmizade";
	public 	static final String SERVICE_URL_ALTERAR_SOLICITACAO		= SERVICES_URL+"usuarios/alterarSolicitacao";
	
	public 	static final String SERVICES_URL_CADASTRO_LOCAL			= SERVICES_URL+"locais/cadastrarLocal";
	public 	static final String SERVICES_URL_LISTAR_LOCAIS			= SERVICES_URL+"locais/listar";
	public 	static final String SERVICES_URL_RELATORIO_GASTOS		= SERVICES_URL+"locais/relatorioGastos";
	
	public 	static final String SERVICES_URL_LISTAR_CATEGORIAS		= SERVICES_URL+"categorias/listar";
}
