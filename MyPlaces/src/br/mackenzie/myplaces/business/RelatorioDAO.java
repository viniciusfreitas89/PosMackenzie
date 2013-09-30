package br.mackenzie.myplaces.business;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.mpcbsolutions.mackenzie.vo.JSONResult;

import br.mackenzie.myplaces.utils.URLs;
import br.mackenzie.myplaces.utils.JSONUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.utils.WebUtils;
/**
 * 
 * @author Vinicius
 *
 */
public class RelatorioDAO {
	protected RelatorioDAO(){}

	/**
	 * 
	 * @param idUsuario
	 * @param dtInicial - yyyy-MM-dd
	 * @param dtFinal - yyyy-MM-dd
	 * @return JSONResult
	 * @throws Exception
	 */
	public JSONResult listarGastos(int idUsuario, String dtInicial, String dtFinal) throws Exception{
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", String.valueOf(idUsuario)));
		params.add(new BasicNameValuePair("data_inicio", dtInicial+" 00:00:00"));
		params.add(new BasicNameValuePair("data_fim", dtFinal+" 23:59:99"));
		
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_RELATORIO_GASTOS, params);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<JSONResult> jUtil = new JSONUtils<JSONResult>(JSONResult.class);
		JSONResult status = jUtil.translate(json);
		
		return status;
	}
	
}
