package br.mackenzie.myplaces.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.mackenzie.myplaces.Exception.LoginException;
import br.mackenzie.myplaces.vo.GastoVO;
import com.mpcbsolutions.mackenzie.vo.JSONResult;

public class RelatorioBusiness {
	
	public List<GastoVO> listarGastos(int idUsuario, Date dtInicial, Date dtFinal) throws Exception{
		RelatorioDAO dao = new RelatorioDAO();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		JSONResult result = dao.listarGastos(idUsuario, format.format(dtInicial), format.format(dtFinal));
		
		if (result.isStatus()){
			return result.getGastos();
		}else{
			throw new LoginException(result.getMsg());
		}
	}
	
}
