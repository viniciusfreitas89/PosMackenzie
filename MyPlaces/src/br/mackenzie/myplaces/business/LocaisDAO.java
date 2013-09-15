package br.mackenzie.myplaces.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

import br.mackenzie.myplaces.utils.Constants;
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
	
//	public StatusVO cadastrar(String nome, double latitude, double longitude) throws Exception{
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("nome", nome));
//		params.add(new BasicNameValuePair("email", email));
//		params.add(new BasicNameValuePair("senha", senha));
//		params.add(new BasicNameValuePair("c_senha", c_senha));
//		
//		InputStream response = WebUtils.requestByPost(Constants.SERVICES_URL_CADASTRO_USUARIO, params);
//		String json = Utils.inputStreamToString(response);
//		
//		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
//		StatusVO status = jUtil.translate(json);
//		
//		return status;
//	}
	

	public StatusVO fazerCheckin(int idUsuario, int idLocal, float valorGasto) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", String.valueOf(idUsuario)));
		params.add(new BasicNameValuePair("id_local", String.valueOf(idLocal)));
		params.add(new BasicNameValuePair("valor_gasto", String.valueOf(valorGasto)));
		
		InputStream response = WebUtils.requestByPost(Constants.SERVICE_URL_CHECKIN, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
	
}
