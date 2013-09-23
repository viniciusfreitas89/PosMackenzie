package br.mackenzie.myplaces.business;

import java.io.InputStream;

import com.mpcbsolutions.mackenzie.vo.JSONResult;

import br.mackenzie.myplaces.utils.URLs;
import br.mackenzie.myplaces.utils.JSONUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.utils.WebUtils;

public class CategoriasDAO {
	protected CategoriasDAO(){}
	
	public JSONResult listar() throws Exception{
		InputStream response = WebUtils.requestByPost(URLs.SERVICES_URL_LISTAR_CATEGORIAS, null);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<JSONResult> jUtil = new JSONUtils<JSONResult>(JSONResult.class);
		JSONResult status = jUtil.translate(json);
		
		return status;
	}
}
