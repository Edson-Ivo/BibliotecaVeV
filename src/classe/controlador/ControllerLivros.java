package classe.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.sql.DataSource;

import classe.Interface.crudLivrosDAO;
import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class ControllerLivros{

	public boolean cadastroLivro(long id,String nome,String autor,String tipo) {
		if(!nome.equals("")&&!autor.equals("")&&!tipo.equals("")) {
				if(BD.getInstance().buscarLivroID(id)==null) {
				Livro l=new Livro(id,nome,autor,tipo);
				BD.getInstance().adicionarLivroBD(l);
				return true; 
			}else {
				return false;
			}					
		}
		return false;
	}
	
	public boolean removerLivro(long id) {
		// remover da lista de alugados para depois remover do banco
		Livro l = BD.getInstance().buscarLivroID(id);

		if (l == null)
			return false;

		int index = BD.getInstance().buscarLivroIDAlugadosIndex(l);

		if (index == -1)
			return false;// false

		while (index != -1) {
			BD.getInstance().DevolverLivro((Alugador) BD.getInstance().getAlugados().get(index - 1),
					(Livro) BD.getInstance().getAlugados().get(index));
			index = BD.getInstance().buscarLivroIDAlugadosIndex(l);
		}
		if (BD.getInstance().removerLivro(l))
			return true;
		return false;

	}
	



}
