package classe.controlador;

import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class ControllerAlugar{

	public boolean AlugarLivros(long idAlugador, long idLivro) {

		Alugador a = BD.getInstance().buscarAlugadorID(idAlugador);
		Livro l = BD.getInstance().buscarLivroID(idLivro);

		if (a == null || l == null) {
			return false;
		} else {
			if (l.isAlugado()) {
				return false;
			}
			BD.getInstance().alugarLivro(a, l);
			return true;
		}
	}

	public boolean DevolverLivro(long idLivro) {

		Livro l = BD.getInstance().buscarLivroID(idLivro);

		if (l == null)
			return false;
		int li = BD.getInstance().buscarLivroIDAlugadosIndex(l);
		if (li == -1)
			return false;
		Alugador a = (Alugador) BD.getInstance().getAlugados().get(li - 1);
		if (a == null || li - 1 < 0)
			return false;

		BD.getInstance().DevolverLivro(a, l);
		return true;

	}


}
