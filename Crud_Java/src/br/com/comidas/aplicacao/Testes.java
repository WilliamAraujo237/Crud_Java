package br.com.comidas.aplicacao;

import java.sql.Date;
import br.com.comida.dao.comidaDAO;
import br.com.comidas.comidas;

public class Testes {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		comidaDAO comida = new comidaDAO();
		
		//Testando cosntrutor e metodos GET
		comidas x = new comidas("costela", 60.0, "Luciana" ); 
		comidas c = new comidas("macarrão", 12.0,"LUCIANA");
		System.out.println(c.getCriador());
		System.out.println(c.getNome());
		System.out.println(c.getValor());
		c.setData(new Date(10,10,0));
		x.setData(new Date(0));
		 
		comida.SAVE(c);
		comida.SAVE(x);
	}

}