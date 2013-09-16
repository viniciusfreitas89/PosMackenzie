package br.mackenzie.myplaces.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

import br.mackenzie.myplaces.utils.URLs;
import br.mackenzie.myplaces.utils.JSONUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.utils.WebUtils;
/**
 * 
 * @author Vinicius
 *
 */
public class UsuarioDAO {
	protected UsuarioDAO(){}
	
	public StatusVO fazerLogin(String email, String senha) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("senha", senha));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_LOGIN, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	
	public StatusVO cadastrar(String nome, String email, String senha, String c_senha) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nome", nome));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("senha", senha));
		params.add(new BasicNameValuePair("c_senha", c_senha));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_CADASTRO_USUARIO, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
}
