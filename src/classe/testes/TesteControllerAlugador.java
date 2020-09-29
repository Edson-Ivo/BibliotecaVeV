package classe.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classe.controlador.ControllerAlugador;
import classe.controlador.ControllerAlugar;
import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class TesteControllerAlugador {

	@Test
	public void testCadastroAlugadorComNome() {
		ControllerAlugador CA= new ControllerAlugador();
		assertTrue(CA.cadastroAlugador(1,"George"));
	}
	
	@Test
	public void testCadastroAlugadorSemNome() {
		ControllerAlugador CA= new ControllerAlugador();
		assertFalse(CA.cadastroAlugador(1,""));
	}
	
	@Test
	public void testRemoverAlugadorExistente() {
		Livro l= new Livro(1112,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(1213, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(1213,1112);
		ControllerAlugador ca= new ControllerAlugador();
		assertTrue(ca.removerAlugador(1213));
	}
	
	@Test
	public void testRemoverAlugadorInxistente() {
		Livro l= new Livro(1112,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(1214, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(1213,1112);
		ControllerAlugador ca= new ControllerAlugador();
		assertFalse(ca.removerAlugador(1213));
	}
	
	
	

}
