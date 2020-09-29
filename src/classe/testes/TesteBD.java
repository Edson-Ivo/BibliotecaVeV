package classe.testes;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import classe.model.Livro;
import classe.controlador.ControllerAlugar;
import classe.model.Alugador;
import classe.sigleton.BD;

public class TesteBD {

	@Test
	public void testRetornoSingletonBD() {
		assertNotNull(BD.getInstance());
	}
	
	@Test
	public void testRetornoAlugadoresBD() {
		assertNotNull(BD.getInstance().getAlugadoresBD());
	}
	
	@Test
	public void testRetornoLivrosBD() {
		assertNotNull(BD.getInstance().getLivrosBD());
	}
	
	@Test
	public void testAdicionarLivroBDSemObj() {
		assertFalse(BD.getInstance().adicionarLivroBD(null));
	}
	
	@Test
	public void testAdicionarLivroBDComObj() {
		assertTrue(BD.getInstance().adicionarLivroBD(new Livro(123,"Verdum","Paul Richard","Historia")));
	}
	
	@Test
	public void testAdicionarAlugadorBDSemObj() {
		assertFalse(BD.getInstance().adicionarAlugadoresBD(null));
	}
	
	@Test
	public void testAdicionarAlugadorBDComObj() {
		assertTrue(BD.getInstance().adicionarAlugadoresBD(new Alugador(123,"Gustavo",new ArrayList<Livro>())));
	}
	
	@Test
	public void testbuscarLivroIDBDSemAchar() {
		assertNull(BD.getInstance().buscarLivroID(999));
	}
	
	@Test
	public void testbuscarLivroIDBDAchando() {
		BD.getInstance().adicionarLivroBD(new Livro(444,"Verdum","Paul Richard","Historia"));
		assertNotNull(BD.getInstance().buscarLivroID(444));
	}
	
	@Test
	public void testbuscarLivroIDIndexBDSemAchar() {
		assertEquals(-1,BD.getInstance().buscarLivroIDIndex(999));
	}
	
	@Test
	public void testbuscarLivroIDIndexBDAchando() {
		BD.getInstance().adicionarLivroBD(new Livro(333,"Verdum","Paul Richard","Historia"));
		assertNotEquals(-1, BD.getInstance().buscarLivroIDIndex(333));
	}
	
	@Test
	public void testbuscarAlugadorIDIndexBDSemAchar() {
		assertEquals(-1,BD.getInstance().buscarAlugadorIDIndex(999));
	}
	
	@Test
	public void testbuscarAlugadorIDIndexBDAchando() {
		BD.getInstance().adicionarAlugadoresBD(new Alugador(987,"Gustavo",new ArrayList<Livro>()));
		assertNotEquals(-1, BD.getInstance().buscarAlugadorIDIndex(987));
	}
	
	@Test
	public void testbuscarAlugadorIDBDSemAchar() {
		assertNull(BD.getInstance().buscarAlugadorID(999));
	}
	
	@Test
	public void testbuscarAlugadorIDBDAchando() {
		BD.getInstance().adicionarAlugadoresBD(new Alugador(222,"Gustavo",new ArrayList<Livro>()));
		assertNotNull(BD.getInstance().buscarAlugadorID(222));
	}
	
	@Test
	public void testbuscarAlugadorIDBDAlugadoAchando() {
		Livro l= new Livro(1113,"morte","leandro Soleni","terror");
		Alugador al= new Alugador(1213, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(al);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(1213,1113);
		assertNotEquals(-1, BD.getInstance().buscarAlugadorIDAlugadosIndex(al));		
	}
	
	@Test
	public void testbuscarAlugadorIDBDAlugadoNaoAchando() {
		Livro l= new Livro(11243,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(12299, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA= new ControllerAlugar();
		CA.AlugarLivros(12299,11243);
		assertEquals(-1, BD.getInstance().buscarAlugadorIDAlugadosIndex(new Alugador()));
		
		
	}
	
	@Test
	public void testAlugarLivroComLivroEAlugador() {
		
		Livro l= new Livro(1111,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(1212, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		assertTrue(BD.getInstance().alugarLivro(a, l));			
		
	}
	
	@Test
	public void testAlugarLivroComLivroSemAlugador() {
		
		Livro l= new Livro(1111,"morte","leandro Soleni","terror");
		Alugador a= null;
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		assertFalse(BD.getInstance().alugarLivro(a, l));			
		
	}
	
	@Test
	public void testAlugarLivroSemLivroComAlugador() {
		
		Livro l= null;
		Alugador a= new Alugador(1212, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		assertFalse(BD.getInstance().alugarLivro(a, l));			
		
	}
	
	@Test
	public void testAlugarLivroSemLivroEAlugador() {
		Livro l= null;
		Alugador a= null;
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		assertFalse(BD.getInstance().alugarLivro(a, l));			
		
	}
	
	@Test
	public void testDevolverLivro() {
		Livro l= new Livro(11,"morte","leandro Soleni","terror");
		Alugador a= new Alugador(12, "George", new ArrayList<Livro>());
		BD.getInstance().adicionarLivroBD(l);
		BD.getInstance().adicionarAlugadoresBD(a);
		ControllerAlugar CA = new ControllerAlugar();
		CA.AlugarLivros(12,11);
		assertTrue(BD.getInstance().DevolverLivro(a, l));
		
	}

}
