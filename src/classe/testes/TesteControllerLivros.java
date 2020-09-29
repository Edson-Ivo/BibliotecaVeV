package classe.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classe.controlador.ControllerAlugador;
import classe.controlador.ControllerAlugar;
import classe.controlador.ControllerLivros;
import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class TesteControllerLivros {

	@Test
	public void testCadastroLivros() {
		ControllerLivros CL= new ControllerLivros();
		assertTrue(CL.cadastroLivro(1,"Cronicas felizes","Felix Ghordom","Fantasia"));
	}
	
	@Test
	public void testCadastroLivrosSemTipo() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"Cronicas felizes","Felix Ghordom",""));
	}
	
	@Test
	public void testCadastroLivrosSemAutor() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"Cronicas felizes","","Fantasia"));
	}
	@Test
	public void testCadastroLivrosSemNome() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"","Felix Ghordom","Fantasia"));
	}
	
	@Test
	public void testCadastroLivrosSemTipoENome() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"","Felix Ghordom",""));
	}
	@Test
	public void testCadastroLivrosSemTipoEAutor() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"Cronicas felizes","",""));
	}
	@Test
	public void testCadastroLivrosSemNomeEAutor() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"","","Fantasia"));
	}
	@Test
	public void testCadastroLivrosSemNada() {
		ControllerLivros CL= new ControllerLivros();
		assertFalse(CL.cadastroLivro(1,"","",""));
	}
	
	@Test
	public void testRemoverLivroExistente() {
		Livro l= new Livro(1112,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(1213, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(1213,1112);
		ControllerLivros cl= new ControllerLivros();
		assertTrue(cl.removerLivro(1112));
	}
	
	@Test
	public void testRemoverLivroInexistente() {
		Livro l= new Livro(1113,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(1213, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(1213,1113);
		ControllerLivros cl= new ControllerLivros();
		assertFalse(cl.removerLivro(1114));
	}

}
