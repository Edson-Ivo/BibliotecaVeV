package classe.testeMock;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import classe.dao.AlugadorDAO;
import classe.dao.LivrosDao;
import classe.model.Alugador;
import classe.model.Livro;

public class testeAlugador {

	@Mock
	private DataSource ds = Mockito.mock(DataSource.class);
	
	@Mock
	private Connection c = Mockito.mock(Connection.class);
	
	@Mock
	private PreparedStatement stmt = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private PreparedStatement leituraStmt = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private PreparedStatement update = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private PreparedStatement excluir = Mockito.mock(PreparedStatement.class);
	
	@Mock
	private ResultSet rs = Mockito.mock(ResultSet.class);
	
	@Mock
	private ResultSet nullRs = Mockito.mock(ResultSet.class);
	
	private Alugador a;
	private long LivroSolicitada = 0;
	private boolean apagado = false;
	
	@Before
	public void setUp() throws Exception {
		Assert.assertNotNull(ds);
		Mockito.when(ds.getConnection()).thenReturn(c);
		
		Mockito.when(c.prepareStatement(Mockito.any(String.class))).thenReturn(stmt);
		Mockito.when(c.prepareStatement(Mockito.startsWith("SELECT"))).thenReturn(leituraStmt);
		Mockito.when(c.prepareStatement(Mockito.startsWith("UPDATE"))).thenReturn(update);
		Mockito.when(c.prepareStatement(Mockito.startsWith("DELETE"))).thenReturn(excluir);
		
		Mockito.when(stmt.execute()).thenReturn(true);
		Mockito.doNothing().when(stmt).close();
		

		

		
		a = new Alugador();
		a.setId(1);
		a.setNome("Jorge");
		

		Mockito.when(rs.first()).thenReturn(true);
		Mockito.when(rs.getLong(1)).thenReturn(a.getId());
		Mockito.when(rs.getString(2)).thenReturn(a.getNome());

		Mockito.when(nullRs.first()).thenReturn(false);

		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				LivroSolicitada = (long)invocation.getArguments()[1];
				return null;
			}
			
		}).when(leituraStmt).setLong(Mockito.eq(1), Mockito.anyLong());
		
		Mockito.when(leituraStmt.executeQuery()).thenAnswer(new Answer<ResultSet>() {
			@Override
			public ResultSet answer(InvocationOnMock invocation) throws Throwable {
				return LivroSolicitada == 1 ? rs : nullRs;
			}
		});
		
		
		Mockito.doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				LivroSolicitada = (long)invocation.getArguments()[1];
				return null;
			}		
		}).when(excluir).setLong(Mockito.eq(1),Mockito.anyLong());
		
		Mockito.when(excluir.execute()).thenAnswer(new Answer<Boolean>() {
			@Override
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
				if(LivroSolicitada==1)
					apagado=true;
				
				return false;
			}
		});
		
		Mockito.when(excluir.getUpdateCount()).thenAnswer(new Answer<Integer>() {
			@Override
			public Integer answer(InvocationOnMock invocation) throws Throwable {
				if(apagado==true)
					return 1;			
				return 0;
			}
		});
		
		
	}
	
	@Test
	public void adicionaAlugadorBanco() throws Exception {
		AlugadorDAO dao = new AlugadorDAO();
		dao.setDataSource(ds);
		dao.adiciona(a);
		Mockito.verify(stmt,Mockito.times(1)).execute();
	}	
	
	@Test
	public void ListarLivroPorId() throws Exception {
		AlugadorDAO dao = new AlugadorDAO();
		dao.setDataSource(ds);
		Alugador LivroLido = dao.ListarAlugador(1);
		Assert.assertEquals(LivroLido.getNome(), a.getNome());
	}
	
	@Test
	public void LivroNaoEncontrado() throws Exception {
		AlugadorDAO dao = new AlugadorDAO();
		dao.setDataSource(ds);
		Alugador livro = dao.ListarAlugador(2);
		Assert.assertNull(livro);
	}
	
	@Test
	public void excluirLivroBanco() throws Exception {
		AlugadorDAO dao = new AlugadorDAO();
		dao.setDataSource(ds);
		dao.deletarAlugador(1);
		Mockito.verify(excluir,Mockito.times(1)).execute();
		Mockito.verify(excluir,Mockito.times(1)).getUpdateCount();
		assertEquals(1, excluir.getUpdateCount());
	}

}
