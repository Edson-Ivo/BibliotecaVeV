package classe.Interface;

import java.util.ArrayList;

import classe.model.Livro;

public interface crudLivrosDAO<T> {
	
	public Livro adiciona(Livro l);
	
	public void Alugado(long id);
	
	public void Desalugado(long id);
	
	public Livro ListarLivroId(long i);
	
	public void deletarLivro(long id);
}
