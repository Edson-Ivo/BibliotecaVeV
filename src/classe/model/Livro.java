package classe.model;

public class Livro {
	
	private long id;
	private String nome;
	private String autor;
	private String tipo;
	private boolean alugado;
	
	public Livro() {}
	
	public Livro(long id,String nome,String autor,String tipo) {
		this.id=id;
		this.nome=nome;
		this.autor=autor;
		this.tipo=tipo;
		this.alugado=false;
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
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void Alugado() {
		this.alugado = true;
	}
	public void Devolvido() {
		this.alugado=false;
	}

	public boolean isAlugado() {
		return alugado;
	}
	
	
	
	
	

}
