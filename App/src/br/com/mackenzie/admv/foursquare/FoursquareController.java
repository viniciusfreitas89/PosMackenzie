package br.com.mackenzie.admv.foursquare;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.foursquare.android.nativeoauth.FoursquareCancelException;
import com.foursquare.android.nativeoauth.FoursquareDenyException;
import com.foursquare.android.nativeoauth.FoursquareInvalidRequestException;
import com.foursquare.android.nativeoauth.FoursquareOAuth;
import com.foursquare.android.nativeoauth.FoursquareOAuthException;
import com.foursquare.android.nativeoauth.FoursquareUnsupportedVersionException;
import com.foursquare.android.nativeoauth.model.AccessTokenResponse;
import com.foursquare.android.nativeoauth.model.AuthCodeResponse;
import com.foursquareutils.request.explorer.controller.ExplorerController;
import com.foursquareutils.request.place.Groups;
import com.foursquareutils.request.place.Items;
import com.foursquareutils.request.place.Location;
import com.foursquareutils.request.place.Place;
import com.foursquareutils.request.place.Venue;
import com.foursquareutils.request.user.User;

import android.app.Activity;
import android.content.Intent;
import br.com.mackenzie.admv.R;
import br.com.mackenzie.admv.model.Coordenadas;
import br.com.mackenzie.admv.model.Lugar;
import br.com.mackenzie.admv.model.Usuario;
import br.com.mackenzie.admv.utils.AndroidUtils;
import br.com.mackenzie.admv.utils.Utils;
import br.com.mackenzie.admv.utils.WebUtils;
import br.com.mackenzie.admv.xml.persistence.XMLReader;
import br.com.mackenzie.admv.xml.persistence.XMLWriter;

public class FoursquareController implements FoursquareConstants{
	private static String pathXml = null;
	private static FoursquareController instance;
	private static Activity activity = null;
	
	static{
		instance = new FoursquareController();
	}
	private FoursquareController(){}

	
	public static FoursquareController getInstance(Activity activity){
		if (pathXml == null){
			pathXml = AndroidUtils.getDataDir(activity)+"/"+Usuario.class.getSimpleName()+".xml";
			FoursquareController.activity = activity;
		}
		
		return instance;
	}
	
	public boolean gravarUsuario(Usuario usuario){
		XMLWriter<Usuario> writer = new XMLWriter<Usuario>(Usuario.class);
		
		return writer.writeFile(usuario, pathXml);
	}
	
	private Usuario carregarUsuario(String token) throws Exception{
		String url = String.format(URL_USER_DETAIL, token);
		
		InputStream is = WebUtils.requestByGet(url, null);
		String json = Utils.inputStreamToString(is);
		
		ExplorerController controller = new ExplorerController();
		User user = controller.user(json);
		Usuario usuario = new Usuario();
		usuario.setId(user.getId());
		usuario.setNome(user.getFirstName());
		usuario.setSobrenome(user.getLastName());
		usuario.setSexo(user.getGender());
		usuario.setTokenFoursquare(token);
		
		return usuario;
	}
	
	public Usuario getUsuario(){
		XMLReader<Usuario> reader = new XMLReader<Usuario>(Usuario.class);
		Usuario user = reader.readFile(pathXml);
		
		return user;
	}
	
	public boolean isAutenticado(){
		Usuario usuario = getUsuario();
		if (usuario!=null){
			String token = usuario.getTokenFoursquare();
			if (token!=null && !token.isEmpty()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	public void limparDadosUsuario(){
		File file = new File(pathXml);
		if (file.exists()){
			file.delete();
		}
	}
	
	public List<Lugar> listarLugaresProximos(Coordenadas coordenadas) throws Exception{
		String url = String.format(URL_EXPLORE, coordenadas, getUsuario().getTokenFoursquare());
		
		InputStream is = WebUtils.requestByGet(url, null);
		String json = Utils.inputStreamToString(is);
		
		ExplorerController controller = new ExplorerController();
		Place explorer = controller.locais(json);
		
		List<Lugar> lugares = new ArrayList<Lugar>();
		if (explorer!=null){
			if (explorer.getGroups()!=null){
				for (Groups group : explorer.getGroups()){
					if (group.getItems()!=null){
						for (Items item : group.getItems()){
							Venue venue = item.getVenue();
							if (venue!=null){
								Location location = venue.getLocation();
								Lugar lugar = new Lugar();
								lugar.setId(venue.getId());
								lugar.setNome(venue.getName());
								if (location!=null){
									Coordenadas coord = new Coordenadas();
									coord.setLatitude(location.getLat());
									coord.setLongitude(location.getLng());
									
									lugar.setLogradouro(location.getAddress());
									lugar.setCidade(location.getCity());
									lugar.setPais(location.getCountry());
									lugar.setCoord(coord);
									lugar.setEstado(location.getState());
									lugar.setDistancia(location.getDistance());
								}
								
								lugares.add(lugar);
							}
						}
					}
				}
			}
		}
		
		return lugares;
	}
	
	public void onCompleteConnect(int resultCode, Intent data) {
        AuthCodeResponse codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data);
        Exception exception = codeResponse.getException();
        
        if (exception == null) {
            // Success.
            String code = codeResponse.getCode();
            performTokenExchange(code);

        } else {
            if (exception instanceof FoursquareCancelException) {
            	AndroidUtils.showMessage(activity, activity.getString(R.string.foursquare_conexao_cancelada));
            } else if (exception instanceof FoursquareDenyException) {
            	AndroidUtils.showMessage(activity, activity.getString(R.string.foursquare_conexao_negada));
            } else if (exception instanceof FoursquareOAuthException) {
                String errorMessage = exception.getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                AndroidUtils.showMessage(activity, errorMessage + " [" + errorCode + "]");
                
            } else if (exception instanceof FoursquareUnsupportedVersionException) {
            	AndroidUtils.showMessage(activity, activity.getString(R.string.foursquare_desatualizado));
            } else if (exception instanceof FoursquareInvalidRequestException) {
            	AndroidUtils.showMessage(activity, exception.getMessage());
            } else {
            	AndroidUtils.showMessage(activity, exception.getMessage());
            }
        }
    }
	
	private void performTokenExchange(String code) {
        Intent intent = FoursquareOAuth.getTokenExchangeIntent(activity, FoursquareController.CLIENT_ID, FoursquareController.CLIENT_SECRET, code);
        activity.startActivityForResult(intent, FoursquareController.REQUEST_CODE_FSQ_TOKEN_EXCHANGE);
    }
	
	public void onCompleteTokenExchange(int resultCode, Intent data) {
    	AccessTokenResponse tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data);
        Exception exception = tokenResponse.getException();
        
        if (exception == null) {
            String accessToken = tokenResponse.getAccessToken();
            AndroidUtils.showMessageDialog(activity, "Access token: " + accessToken, false);
            
            
            System.out.println("token: "+accessToken);
            Usuario usuario = null;
			try {
				usuario = carregarUsuario(accessToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
            
            gravarUsuario(usuario);
        } else {
            if (exception instanceof FoursquareOAuthException) {
                String errorMessage = ((FoursquareOAuthException) exception).getMessage();
                String errorCode = ((FoursquareOAuthException) exception).getErrorCode();
                AndroidUtils.showMessageDialog(activity, errorMessage + " [" + errorCode + "]", false);
                
            } else {
            	AndroidUtils.showMessage(activity, exception.getMessage());
            }
        }
    }
}
