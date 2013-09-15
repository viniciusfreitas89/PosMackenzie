package br.mackenzie.myplaces.business;

import java.io.InputStream;

import com.mpcbsolutions.mackenzie.vo.StatusVO;

import br.mackenzie.myplaces.utils.Constants;
import br.mackenzie.myplaces.utils.JSONUtils;
import br.mackenzie.myplaces.utils.Utils;
import br.mackenzie.myplaces.utils.WebUtils;

public class CategoriasDAO {
	protected CategoriasDAO(){}
	
	public StatusVO listar() throws Exception{
		InputStream response = WebUtils.requestByPost(Constants.SERVICES_URL_LISTAR_CATEGORIAS, null);
		String json = Utils.inputStreamToString(response);
		
		JSONUtils<StatusVO> jUtil = new JSONUtils<StatusVO>(StatusVO.class);
		StatusVO status = jUtil.translate(json);
		
		return status;
	}
}
