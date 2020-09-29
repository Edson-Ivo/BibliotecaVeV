package classe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import classe.Interface.crudLivrosDAO;
import classe.model.Livro;

public class LivrosDao implements crudLivrosDAO<Livro>{
	
	private Connection connection;
	private DataSource dataSource;		
	
	
	public Livro adiciona(Livro l) {
		String sql = "INSERT INTO livros " +
	             "(id,nome,autor,tipo)" +
	             " values (?,?,?,?)";

	     try {
	    	 this.connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql);

	         stmt.setLong(1,(long)l.getId());
	         stmt.setString(2,l.getNome());
	         stmt.setString(3,l.getAutor());
	         stmt.setString(4,l.getTipo());

	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	     
	     return new Livro(l.getId(),l.getNome(),l.getAutor(),l.getTipo());
		
	}
	
	public void Alugado(long id) {
		String sql = "UPDATE livros SET alugado = ? WHERE id = ?";

	     try {
	    	 this.connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setInt(1,1);
	         stmt.setInt(2,(int)id);
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	
	public void Desalugado(long id) {
		String sql = "UPDATE livros SET alugado = ? WHERE id = ?";

	     try {
	    	 this.connection = dataSource.getConnection();
	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setInt(1,0);
	         stmt.setInt(2,(int)id);
	         stmt.execute();
	         stmt.close();
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
		
	}
	

	
	public Livro ListarLivroId(long i) {
		String sql = "SELECT * FROM livros WHERE id = ?";
		
	     try {
	    	 this.connection = dataSource.getConnection();

	         PreparedStatement stmt = connection.prepareStatement(sql);
	         stmt.setLong(1,i);
	         ResultSet rs = stmt.executeQuery();
	         Livro l = null;
	         if(rs.first()){
	        	 l = new Livro(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4));
	         }
	         stmt.close();
	         return l;
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	}
	
	
	public void deletarLivro(long id) {
		String sql = "DELETE FROM livros WHERE id = ?";				
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
