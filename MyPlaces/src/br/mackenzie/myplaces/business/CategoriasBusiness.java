package br.mackenzie.myplaces.business;

import java.util.List;

import com.mpcbsolutions.mackenzie.vo.JSONResult;

import br.mackenzie.myplaces.vo.CategoriaVO;

public class CategoriasBusiness {
	
	public List<CategoriaVO> listar() throws Exception{
		CategoriasDAO dao = new CategoriasDAO();
		JSONResult status = dao.listar();
		List<CategoriaVO> list = status.getCategorias();
		
		return list;
	}
	
}
