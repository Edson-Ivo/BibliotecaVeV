package classe.sigleton;

import java.util.ArrayList;

import classe.model.Alugador;
import classe.model.Livro;

public class BD {

	private static BD bd = new BD();

	private ArrayList<Livro> livrosBD = new ArrayList<Livro>();
	private ArrayList<Alugador> alugadoresBD = new ArrayList<Alugador>();
	private ArrayList<Object> alugados = new ArrayList<Object>();

	public static BD getInstance() {
		return bd;
	}

	public boolean adicionarLivroBD(Livro l) {
		if (l != null) {
			this.livrosBD.add(l);
			return true;
		}
		return false;
	}

	public boolean adicionarAlugadoresBD(Alugador a) {
		if (a != null) {
			this.alugadoresBD.add(a);
			return true;
		}
		return false;
	}

	public Livro buscarLivroID(long idLivro) {
		for (int i = 0; i < livrosBD.size(); i++) {
			if (livrosBD.get(i).getId() == idLivro) {
				return livrosBD.get(i);
			}
		}
		return null;
	}

	public int buscarLivroIDIndex(long idLivro) {
		for (int i = 0; i < livrosBD.size(); i++) {
			if (livrosBD.get(i).getId() == idLivro) {
				return i;
			}
		}
		return -1;
	}

	public Alugador buscarAlugadorID(long idAlugador) {
		for (int i = 0; i < alugadoresBD.size(); i++) {
			if (alugadoresBD.get(i).getId() == idAlugador) {
				return alugadoresBD.get(i);
			}
		}
		return null;
	}

	public int buscarAlugadorIDIndex(long idAlugador) {
		for (int i = 0; i < alugadoresBD.size(); i++) {
			if (alugadoresBD.get(i).getId() == idAlugador) {
				return i;
			}
		}
		return -1;
	}

	public int buscarAlugadorIDAlugadosIndex(Alugador idAlugador) {
		for (int i = 0; i < alugados.size(); i++) {
			if (alugados.get(i) == idAlugador) {
				return i;
			}
		}
		return -1;
	}

	public int buscarLivroIDAlugadosIndex(Livro idLivro) {
		for (int i = 0; i < alugados.size(); i++) {
			if (alugados.get(i) == idLivro) {
				return i;
			}
		}
		return -1;
	}

	public boolean alugarLivro(Alugador idAlugador, Livro idLivro) {
		if (idAlugador == null || idLivro == null) {
			return false;
		}
		idAlugador.adicionarLivro(idLivro);
		idLivro.Alugado();
		alugados.add(idAlugador);
		alugados.add(idLivro);

		if (alugados.size() > 0) {
			return true;
		}
		return false;
	}

	public boolean DevolverLivro(Alugador posicaoAlugador, Livro posicaoLivro) {
		if (posicaoAlugador == null || posicaoLivro == null)
			return false;
		int total = alugados.size();
		alugados.remove(posicaoAlugador);
		alugados.remove(posicaoLivro);
		posicaoAlugador.removerAlugado(posicaoLivro);
		posicaoLivro.Devolvido();
		if (total > alugados.size()) {
			return true;
		}
		return false;
	}

	public boolean removerAlugador(Alugador a) {
		alugadoresBD.remove(a);
		if (buscarAlugadorID(a.getId()) == null)
			return true;
		return false;
	}

	public boolean removerLivro(Livro l) {
		livrosBD.remove(l);
		if (buscarLivroID(l.getId()) == null)
			return true;
		return false;
	}

	public ArrayList<Livro> getLivrosBD() {
		return livrosBD;
	}

	public ArrayList<Alugador> getAlugadoresBD() {
		return alugadoresBD;
	}

	public ArrayList<Object> getAlugados() {
		return alugados;
	}

}
