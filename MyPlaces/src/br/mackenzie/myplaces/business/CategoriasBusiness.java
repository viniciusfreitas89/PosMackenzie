package br.mackenzie.myplaces.business;

import java.util.List;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

import br.mackenzie.myplaces.vo.CategoriaVO;

public class CategoriasBusiness {
	
	public List<CategoriaVO> listar() throws Exception{
		CategoriasDAO dao = new CategoriasDAO();
		StatusVO status = dao.listar();
		List<CategoriaVO> list = status.getCategorias();
		
		return list;
	}
	
}
