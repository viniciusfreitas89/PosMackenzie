package br.com.mackenzie.admv.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import br.com.mackenzie.admv.Exception.LoginException;
import br.com.mackenzie.admv.model.Usuario;
import br.com.mackenzie.admv.utils.Constants;
import br.com.mackenzie.admv.utils.Utils;
import br.com.mackenzie.admv.utils.WebUtils;

public class UsuarioDAO {
	
	public boolean fazerLogin(Usuario usuario) throws LoginException, Exception{
		String url = String.format(Constants.SERVICES_URL_LOGIN, usuario.getEmail(), usuario.getSenha());
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("email", usuario.getEmail()));
		params.add(new BasicNameValuePair("senha", usuario.getSenha()));
		
		InputStream response = WebUtils.requestByPost(url, params);
		String json = Utils.inputStreamToString(response);
		
		throw new LoginException("Usuário não autenticado");
		
//		return true;
	}
}
