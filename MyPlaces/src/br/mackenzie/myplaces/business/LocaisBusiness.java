package br.mackenzie.myplaces.business;

import java.util.List;

import br.mackenzie.myplaces.vo.LocalVO;

import com.mpcbsolutions.mackenzie.vo.JSONResult;

public class LocaisBusiness {
	public Integer inserir(String nome, double latitude, double longitude, int idCategoria) throws Exception{
		LocaisDAO dao = new LocaisDAO();
		JSONResult status = dao.cadastrar(nome, latitude, longitude, idCategoria);
		
		if (status.isStatus()){
			return status.getId_local();
		}
		
		return null;
	}
	
	public boolean checkin(int idUsuario, int idLocal, float valorGasto) throws Exception{
		LocaisDAO dao = new LocaisDAO();
		JSONResult status = dao.fazerCheckin(idUsuario, idLocal, valorGasto);
		
		return status.isStatus();
	}
	
	public List<LocalVO> listarTodos(int maxResults, double latitude, double longitude) throws Exception{
		LocaisDAO dao = new LocaisDAO();
		JSONResult status = dao.listarLocais(maxResults, latitude, longitude);
		
		return status.getLocais();	
	}
	
	public List<LocalVO> listarPorUsuario(int idUsuario) throws Exception{
		LocaisDAO dao = new LocaisDAO();
		JSONResult status = dao.listarPorUsuario(idUsuario);
		
		return status.getTimeline();
	}
}
