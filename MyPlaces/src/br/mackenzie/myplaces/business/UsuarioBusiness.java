package br.mackenzie.myplaces.business;

import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.Exception.UsuarioException;
import br.mackenzie.myplaces.vo.UsuarioVO;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

public class UsuarioBusiness {
	
	public UsuarioVO fazerLogin(String email, String senha) throws LoginException, Exception{
		UsuarioDAO dao = new UsuarioDAO();
		StatusVO result = dao.fazerLogin(email, senha);
		
		if (result.isStatus()){
			return result.getUsuario();
		}else{
			throw new LoginException(result.getMsg());
		}
	}
	
	public boolean inserir(String nome, String email, String senha, String c_senha) throws UsuarioException, Exception{
		UsuarioDAO dao = new UsuarioDAO();
		StatusVO result = dao.cadastrar(nome, email, senha, c_senha);
		
		if (result.isStatus()){
			return result.isStatus();
		}else{
			throw new UsuarioException(result.getMsg());
		}
	}
}
