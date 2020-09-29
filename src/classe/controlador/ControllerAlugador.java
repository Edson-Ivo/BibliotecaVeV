package classe.controlador;

import java.util.ArrayList;

import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class ControllerAlugador{

	public boolean cadastroAlugador(long id, String nome) {
		if (!nome.equals("")) {
			if (BD.getInstance().buscarAlugadorID(id) == null) {
				Alugador a = new Alugador(id, nome, new ArrayList<Livro>());
				BD.getInstance().adicionarAlugadoresBD(a);
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	public boolean removerAlugador(long id) {
		// remover da lista de alugados para depois remover do banco
		Alugador a = BD.getInstance().buscarAlugadorID(id);
		if (a == null)
			return false;
		int index = BD.getInstance().buscarAlugadorIDAlugadosIndex(a);
		if (index == -1)
			return false;
		while (index != -1) {
			BD.getInstance().DevolverLivro((Alugador) BD.getInstance().getAlugados().get(index),
					(Livro) BD.getInstance().getAlugados().get(index + 1));
			index = BD.getInstance().buscarAlugadorIDAlugadosIndex(a);
		}
		if (BD.getInstance().removerAlugador(a))
			return true;
		return false;

	}


}
