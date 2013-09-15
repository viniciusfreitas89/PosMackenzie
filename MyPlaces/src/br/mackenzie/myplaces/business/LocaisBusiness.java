package br.mackenzie.myplaces.business;

import java.util.List;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

import br.mackenzie.myplaces.vo.CategoriaVO;

public class LocaisBusiness {
	
	public CategoriaVO cadastrar() throws Exception{
		LocaisDAO dao = new LocaisDAO();
		StatusVO status = dao.listar();
		List<CategoriaVO> list = status.getCategorias();
		
		return list;
	}
	
}
