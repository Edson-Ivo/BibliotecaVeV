package classe.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classe.controlador.ControllerAlugar;
import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class TesteControllerAlugar {

	@Test
	public void testAlugarLivroExistenteAlugadorExistente() {
		Livro l= new Livro(123,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(321, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		assertTrue(CA.AlugarLivros(321,123));				
	}
	
	@Test
	public void testAlugarLivroExistenteAlugadorNaoExistente() {
		Livro l= new Livro(2,"morte","leandro Soleni","terror");
		BD.getInstance().adicionarLivroBD(l);
		ControllerAlugar CA= new ControllerAlugar();
		assertFalse(CA.AlugarLivros(1,2));				
	}
	
	@Test
	public void testAlugarLivroNaoExistenteAlugadorExistente() {
		Alugador a= new Alugador(4, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		assertFalse(CA.AlugarLivros(1,3));				
	}
	
	@Test
	public void testAlugarLivroNaoExistenteAlugadorNaoExistente() {
		ControllerAlugar CA= new ControllerAlugar();
		assertFalse(CA.AlugarLivros(5,5));			
	}
	

}
