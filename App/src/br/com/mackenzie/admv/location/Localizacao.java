package br.com.mackenzie.admv.location;

import br.com.mackenzie.admv.model.Coordenadas;

/**
 * 
 * @author Vinicius
 *
 */
public interface Localizacao {
	public boolean isProvedorAtivo();
	public Coordenadas getMinhaLocalizacao();
}
