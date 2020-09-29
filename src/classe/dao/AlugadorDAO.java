package classe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import classe.model.Alugador;
import classe.model.Livro;

public class AlugadorDAO {
	
	private Connection connection;
	private DataSource dataSource;		
	
	
	public void adiciona(Alugador a) {
		String sql = "INSERT INTO alugador " +
	             "(id,nome)" +
	             " values (?,?)";

	     try {
	    	 this.connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql);

	         stmt.setLong(1,(long)a.getId());
	         stmt.setString(2,a.getNome());

	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	

	
	public Alugador ListarAlugador(long i) {
		String sql = "SELECT * FROM alugador WHERE id = ?";
		
	     try {
	    	 this.connection = dataSource.getConnection();

	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setLong(1,i);
	         ResultSet rs = stmt.executeQuery();
	         Alugador a = null;
	         if(rs.first()){
	        	 a = new Alugador();
	        	 a.setId(rs.getLong(1));
	        	 a.setNome(rs.getString(2));
	         }
	         stmt.close();
	         return a;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	
	public void deletarAlugador(long id) {
		String sql = "DELETE FROM alugador WHERE id = ?";				
		 try {
	    	 this.connection = dataSource.getConnection();
	         // prepared statement para inserção
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         // seta os valores
	         stmt.setLong(1, id);
	         stmt.execute();

	         stmt.getUpdateCount();
	         stmt.close();
	         
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}

	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
