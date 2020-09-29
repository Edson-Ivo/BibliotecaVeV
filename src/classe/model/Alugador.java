package classe.model;

import java.util.ArrayList;

public class Alugador {
	
	private long id;
	private String nome;
	private ArrayList<Livro> livrosAlugados;	
	
	public Alugador() {}

	public Alugador(long id,String nome,ArrayList<Livro> livros) {
		this.id=id;
		this.nome=nome;
		this.livrosAlugados=livros;
	}	

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ArrayList<Livro> getLivrosAlugados() {
		return livrosAlugados;
	}
	public void setLivrosAlugados(ArrayList<Livro> livrosAlugados) {
		this.livrosAlugados = livrosAlugados;
	}
	public void adicionarLivro(Livro l) {
		this.livrosAlugados.add(l);
	}
	public void removerAlugado(Livro l) {
		this.livrosAlugados.remove(l);
	}
	
	
	

}
