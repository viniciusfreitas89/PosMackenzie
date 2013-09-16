import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import util.JsonUtils;
import util.StatusVO;
import util.Utils;
import util.WebUtils;

public class Main {
	
	public static void main (String args[]) throws Exception{
		listarLugares();
	}
	
	public static void carregarTimeLine() throws Exception{
		String url = "http://www.mpcbsolutions.com/mackenzie/usuarios/carregarTimeLine";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", "1"));
		
		InputStream response = WebUtils.requestByPost(url, params);
		String json = Utils.inputStreamToString(response);
		System.out.println(json);
		JsonUtils<StatusVO> util = new JsonUtils<StatusVO>(StatusVO.class);
		StatusVO login = util.translate(json);
		
		System.out.println(login.getMsg());
		System.out.println(login.isStatus());
	}
	
	public static void efetuarCheckin() throws Exception{
		String url = "http://www.mpcbsolutions.com/mackenzie/usuarios/efetuarCheckin";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("id_usuario", "1"));
		params.add(new BasicNameValuePair("id_local", "1"));
		params.add(new BasicNameValuePair("valor_gasto", "10.5"));
		
		InputStream response = WebUtils.requestByPost(url, params);
		String json = Utils.inputStreamToString(response);
		System.out.println(json);
		JsonUtils<StatusVO> util = new JsonUtils<StatusVO>(StatusVO.class);
		StatusVO login = util.translate(json);
		
		System.out.println(login.getMsg());
		System.out.println(login.isStatus());
	}
	
	public static void listarCategorias() throws Exception{
		String url = "http://www.mpcbsolutions.com/mackenzie/categorias/listar";
		
		InputStream response = WebUtils.requestByPost(url, null);
		String json = Utils.inputStreamToString(response);
		System.out.println(json);
		JsonUtils<StatusVO> util = new JsonUtils<StatusVO>(StatusVO.class);
		StatusVO login = util.translate(json);
		
		System.out.println(login.getMsg());
		System.out.println(login.isStatus());
	}
	
	public static void listarLugares() throws Exception{
		String url = "http://www.mpcbsolutions.com/mackenzie/locais/listar";
		
		InputStream response = WebUtils.requestByPost(url, null);
		String json = Utils.inputStreamToString(response);
		System.out.println(json);
		JsonUtils<StatusVO> util = new JsonUtils<StatusVO>(StatusVO.class);
		StatusVO login = util.translate(json);
		
		System.out.println(login.getMsg());
		System.out.println(login.isStatus());
	}
}
