package br.mackenzie.myplaces.location;

import br.mackenzie.myplaces.vo.CoordenadasVO;

/**
 * 
 * @author Vinicius
 *
 */
public interface Localizacao {
	public boolean isProvedorAtivo();
	public CoordenadasVO getMinhaLocalizacao();
}
