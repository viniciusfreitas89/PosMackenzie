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
public class LocaisDAO {
	protected LocaisDAO(){}
	
	public StatusVO cadastrar(String nome, double latitude, double longitude, int idCategoria) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("nome", nome));
		params.add(new BasicNameValuePair("latitude", String.valueOf(latitude)));
		params.add(new BasicNameValuePair("longitude", String.valueOf(longitude)));
		params.add(new BasicNameValuePair("id_categoria", String.valueOf(idCategoria)));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_CADASTRO_LOCAL, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	

	public StatusVO fazerCheckin(int idUsuario, int idLocal, float valorGasto) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", String.valueOf(idUsuario)));
		params.add(new BasicNameValuePair("id_local", String.valueOf(idLocal)));
		params.add(new BasicNameValuePair("valor_gasto", String.valueOf(valorGasto)));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICE_URL_CHECKIN, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	
	public StatusVO listarTodos() throws Exception{
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_LISTAR_TODOS, null);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	
	public StatusVO listarPorUsuario(int idUsuario) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", String.valueOf(idUsuario)));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICE_URL_CARREGAR_TIMELINE, params);
		String json = Utils.inputStreamToString(response);
		System.out.println("json: "+idUsuario+json);
		
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	
}
