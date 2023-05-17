package br.com.comida.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;
import br.com.comidas.comidas;
import br.com.comidas.factory.ConnectionFactory;

public class comidaDAO {
/*
 * CRUD
 * c: CREAT
 * r: READ
 * u: UPDATE
 * d: DELET
 * */
	
	public void SAVE(comidas Conection) {

		
		String sql = "INSERT INTO COMIDA (NOME,VALOR,CRIADOR,DATA) VALUE (?,?,?,?)";
		
		Connection comm = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria uma conexão com o banco de dados
			comm = ConnectionFactory.CreateConnectionToMYSQL();
			
			//Criamos uma PreparedStatement para executar uma Query
			pstm = (PreparedStatement) comm.prepareStatement(sql);
			
			//Valores esperados pela Query
			pstm.setString(1, Conection.getNome());
			pstm.setDouble(2, Conection.getValor());
			pstm.setString(3, Conection.getCriador());
			pstm.setDate(4, new Date(Conection.getData().getTime()));
			
			//Executar a Query
			pstm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//Fechar conecxões
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (comm != null) {
					comm.close();
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public static List<comidas> getComidas() {
		String sql = "SELECT * FROM COMIDA";
		
		List<comidas> comidas = new ArrayList<comidas>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//classe que vai recuperar dados do banco   *****SELECT***
		ResultSet rset = null;
		
		try {
			//Criando uma conexão com o banco de dados
			conn = ConnectionFactory.CreateConnectionToMYSQL();
			//criando um prepareStatement para executar a Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Variavel para armazenar uma Array com resultados da Query
			rset  = pstm.executeQuery();
			
			while (rset.next()) {
				comidas comida = new comidas(sql, null, sql);
				
				//recuperando resultados CRIADOR
				comida.setCriador(rset.getString("CRIADOR"));
				//recuperando resultados NOME
				comida.setNome(rset.getString("NOME"));
				//recuperando resultados DATA
				comida.setData(rset.getDate("DATA"));
				//recuperando resultados VALOR
				comida.setValor(rset.getDouble("VALOR"));
				
				comidas.add(comida);
			}
			
			
			} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return comidas;
	}
}
