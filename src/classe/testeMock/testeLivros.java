package classe.testeMock;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.Returns;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import classe.Interface.crudLivrosDAO;
import classe.controlador.ControllerLivros;
import classe.dao.LivrosDao;
import classe.model.Livro;

public class testeLivros {
	
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
	
	private Livro l;
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
		
		l = new Livro();
		l.setId(1);
		l.setNome("Bolo");
		l.setAutor("Palmeirinha");
		l.setTipo("Receitas");

		Mockito.when(rs.first()).thenReturn(true);
		Mockito.when(rs.getLong(1)).thenReturn(l.getId());
		Mockito.when(rs.getString(2)).thenReturn(l.getNome());
		Mockito.when(rs.getString(3)).thenReturn(l.getAutor());
		Mockito.when(rs.getString(4)).thenReturn(l.getTipo());

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
	public void adicionaLivroBanco() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		dao.adiciona(l);
		Mockito.verify(stmt,Mockito.times(1)).execute();
	}	
	
	@Test
	public void ListarLivroPorId() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		Livro LivroLido = dao.ListarLivroId(1);
		Assert.assertEquals(LivroLido.getNome(), l.getNome());
	}
	
	@Test
	public void LivroNaoEncontrado() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		Livro livro = dao.ListarLivroId(2);
		Assert.assertNull(livro);
	}
	
	@Test
	public void AlugarLivroBanco() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		dao.Alugado(1);
		Mockito.verify(update,Mockito.times(1)).execute();
	}
	
	@Test
	public void DesalugarLivroBanco() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		dao.Desalugado(1);
		Mockito.verify(update,Mockito.times(1)).execute();
	}	
	
	@Test
	public void excluirLivroBanco() throws Exception {
		LivrosDao dao = new LivrosDao();
		dao.setDataSource(ds);
		dao.deletarLivro(1);
		Mockito.verify(excluir,Mockito.times(1)).execute();
		Mockito.verify(excluir,Mockito.times(1)).getUpdateCount();
		assertEquals(1, excluir.getUpdateCount());
	}
	

}
