package br.mackenzie.myplaces.business;

import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.vo.UsuarioVO;

import com.mpcbsolutions.mackenzie.vo.LoginVO;

public class UsuarioBusiness {
	
	public UsuarioVO fazerLogin(String email, String senha) throws LoginException, Exception{
		UsuarioDAO dao = new UsuarioDAO();
		LoginVO result = dao.fazerLogin(email, senha);
		
		if (result.isStatus()){
			return result.getUsuario();
		}else{
			throw new LoginException(result.getMsg());
		}
	}
}
