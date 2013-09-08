package br.mackenzie.myplaces.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mpcbsolutions.mackenzie.vo.LoginVO;

import br.mackenzie.myplaces.utils.Constants;
import br.mackenzie.myplaces.utils.JSONUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.utils.WebUtils;

public class UsuarioDAO {
	protected UsuarioDAO(){}
	
	public LoginVO fazerLogin(String email, String senha) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("senha", senha));
		
		InputStream response = WebUtils.requestByPost(Constants.SERVICES_URL_LOGIN, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<LoginVO> jUtil = new JSONUtils<LoginVO>(LoginVO.class);
		LoginVO login = jUtil.translate(json);
		
		return login;
	}
	
}
