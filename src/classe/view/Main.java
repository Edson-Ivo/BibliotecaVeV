package classe.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import classe.controlador.ControllerAlugador;
import classe.controlador.ControllerAlugar;
import classe.controlador.ControllerLivros;
import classe.model.Alugador;
import classe.model.Livro;
import classe.sigleton.BD;

public class Main {

	public static void main(String[] args) {

		boolean sistema = true;
		ControllerLivros cl = new ControllerLivros();
		ControllerAlugador calu = new ControllerAlugador();
		ControllerAlugar ca = new ControllerAlugar();

		Scanner str = new Scanner(System.in);
		Scanner inteiro = new Scanner(System.in);

		while (sistema) {
			System.out.println("-------------------------------------");
			System.out.println("Bem vindo a biblioteca");
			System.out.println("Digite 1 para adicionar livro");
			System.out.println("Digite 2 mostrar livros");
			System.out.println("Digite 3 para adicionar um alugador");
			System.out.println("Digite 4 mostrar alugadores");
			System.out.println("Digite 5 alugar livro");
			System.out.println("Digite 6 mostrar livros alugados");
			System.out.println("Digite 7 devolver livro");
			System.out.println("Digite 8 para remover livro");
			System.out.println("Digite 9 para remover Alugador");
			System.out.println("-------------------------------------");

			switch (str.nextLine()) {

			case "1": {
				boolean loop = true;

				while (loop) {
					System.out.println("-------------------------------------");
					if (BD.getInstance().getLivrosBD().size() > 0) {
						System.out.println("Livros ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
							System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
									+ BD.getInstance().getLivrosBD().get(i).getNome());
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
					}
					System.out.println("-------------------------------------");
					System.out.println("Digite o id");
					long id = inteiro.nextInt();
					System.out.println("Digite o nome");
					String nome = str.nextLine();
					System.out.println("Digite o autor");
					String autor = str.nextLine();
					System.out.println("Digite o tipo");
					String tipo = str.nextLine();
					if (cl.cadastroLivro(id, nome, autor, tipo)) {
						System.out.println("Cadastrado com sucesso");
						System.out.println("-------------------------------------");
					} else {
						System.out.println("FALHA - Possui um id igual a esse, ou passou algum valor em branco");
						System.out.println("-------------------------------------");
					}
					System.out.println("Livros ja cadastrados na biblioteca");
					System.out.println("-------------------------------------");
					System.out.println("id - nome");
					for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
						System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
								+ BD.getInstance().getLivrosBD().get(i).getNome());
					}
					System.out.println("-------------------------------------");
					System.out.println("Deseja continuar cadastrando? s - n");
					String rsp = str.nextLine();

					if (!rsp.equals("s")) {
						loop = false;
					}
				}

				break;
			}
			case "2": {

				System.out.println("-------------------------------------");
				if (BD.getInstance().getLivrosBD().size() > 0) {
					System.out.println("Livros ja cadastrados na biblioteca");
					System.out.println("-------------------------------------");
					System.out.println("id - nome");
					for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
						System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
								+ BD.getInstance().getLivrosBD().get(i).getNome());
					}
				} else {
					System.out.println("nao possui nenhum cadastrado");
				}
				System.out.println("-------------------------------------");

				break;
			}
			case "3": {

				boolean loop = true;

				while (loop) {
					System.out.println("-------------------------------------");
					if (BD.getInstance().getAlugadoresBD().size() > 0) {
						System.out.println("Alugadores ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
							System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
									+ BD.getInstance().getAlugadoresBD().get(i).getNome());
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
					}
					System.out.println("-------------------------------------");
					System.out.println("Digite o id");
					long id = inteiro.nextInt();
					System.out.println("Digite o nome");
					String nome = str.nextLine();
					if (calu.cadastroAlugador(id, nome)) {
						System.out.println("Cadastrado com sucesso");
						System.out.println("-------------------------------------");
					} else {
						System.out.println("FALHA - Possui um id igual a esse, ou passou algum valor em branco");
						System.out.println("-------------------------------------");
					}
					System.out.println("Alugadores ja cadastrados na biblioteca");
					System.out.println("-------------------------------------");
					System.out.println("id - nome");
					for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
						System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
								+ BD.getInstance().getAlugadoresBD().get(i).getNome());
					}
					System.out.println("-------------------------------------");
					System.out.println("Deseja continuar cadastrando? s - n");
					String rsp = str.nextLine();

					if (!rsp.equals("s")) {
						loop = false;
					}
				}

				break;
			}
			case "4": {
				System.out.println("-------------------------------------");
				if (BD.getInstance().getAlugadoresBD().size() > 0) {
					System.out.println("Alugadores ja cadastrados na biblioteca");
					System.out.println("-------------------------------------");
					System.out.println("id - nome");
					for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
						System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
								+ BD.getInstance().getAlugadoresBD().get(i).getNome());
					}
				} else {
					System.out.println("nao possui nenhum cadastrado");
				}
				System.out.println("-------------------------------------");
				break;
			}
			case "5": {

				String loop = "s";

				while (loop.equals("s")) {

					System.out.println("-------------------------------------");
					if (BD.getInstance().getAlugadoresBD().size() > 0) {
						System.out.println("Alugadores");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
							System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
									+ BD.getInstance().getAlugadoresBD().get(i).getNome());
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
					}
					System.out.println("-------------------------------------");
					System.out.println("-------------------------------------");
					if (BD.getInstance().getLivrosBD().size() > 0) {
						System.out.println("Livros");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
							System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
									+ BD.getInstance().getLivrosBD().get(i).getNome());
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
					}

					System.out.println("-------------------------------------");
					if (BD.getInstance().getAlugados().size() > 0) {
						System.out.println("Alugadores");
						System.out.println("-------------------------------------");
						System.out.println("id Alugador  -  id Livro");
						for (int i = 0; i < BD.getInstance().getAlugados().size(); i += 2) {
							Livro l = (Livro) BD.getInstance().getAlugados().get(i + 1);
							Alugador a = (Alugador) BD.getInstance().getAlugados().get(i);
							System.out.println(a.getId() + " " + a.getNome() + " // " + l.getId() + " " + l.getNome());
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
					}

					if (BD.getInstance().getLivrosBD().size() > 0 && BD.getInstance().getAlugadoresBD().size() > 0) {
						System.out.println("-------------------------------------");

						System.out.println("-------------------------------------");
						System.out.println("Quem vai alugar( digita os códigos )");
						long idalu = inteiro.nextInt();
						System.out.println("Qual livro( digita os códigos )");
						long idlivro = inteiro.nextInt();
						System.out.println("-------------------------------------");
						if (ca.AlugarLivros(idalu, idlivro)) {
							System.out.println("Alugado com sucesso");
						} else {
							System.out.println(
									"Falha para alugar, ou não existe o Alugador ou livro, ou o livro ja foi alugado");
						}
						System.out.println("-------------------------------------");
						System.out.println("Deseja continuar alugando? s - n");
						loop = str.nextLine();
					} else {
						System.out.println("Não possui livros para alugar ou alugadores");
						loop = "n";
					}

				}
				break;
			}
			case "6": {

				if (BD.getInstance().getAlugados().size() > 0) {
					System.out.println("Alugadores");
					System.out.println("-------------------------------------");
					System.out.println("id Alugador  -  id Livro");
					for (int i = 0; i < BD.getInstance().getAlugados().size(); i += 2) {
						Livro l = (Livro) BD.getInstance().getAlugados().get(i + 1);
						Alugador a = (Alugador) BD.getInstance().getAlugados().get(i);
						System.out.println(a.getId() + " " + a.getNome() + " // " + l.getId() + " " + l.getNome());
					}
				} else {
					System.out.println("nao possui nenhum cadastrado");
				}

				break;
			}
			case "7": {

				String loop = "s";

				while (loop.equals("s")) {

					System.out.println("-------------------------------------");
					if (BD.getInstance().getAlugados().size() > 0) {
						System.out.println("Alugadores");
						System.out.println("-------------------------------------");
						System.out.println("id Alugador  -  id Livro");
						for (int i = 0; i < BD.getInstance().getAlugados().size(); i += 2) {
							Livro l = (Livro) BD.getInstance().getAlugados().get(i + 1);
							Alugador a = (Alugador) BD.getInstance().getAlugados().get(i);
							System.out.println(a.getId() + " " + a.getNome() + " // " + l.getId() + " " + l.getNome());
						}
						System.out.println("-------------------------------------");
						System.out.println("Qual livro vai devolver( digita os códigos )");
						long idlivro = inteiro.nextInt();
						System.out.println("-------------------------------------");
						if (ca.DevolverLivro(idlivro)) {
							System.out.println("devolvido com sucesso");
						} else {
							System.out.println(
									"Falha para devolver, ou Alugador nao alugou esse livro, ou o livro nao existe");
						}
						System.out.println("-------------------------------------");
						System.out.println("Deseja continuar devo? s - n");
						loop = str.nextLine();

					} else {
						System.out.println("nao possui nenhum cadastrado");
						loop = "n";
					}
				}

				break;
			}
			case "8": {

				boolean loop = true;

				while (loop) {
					System.out.println("-------------------------------------");
					if (BD.getInstance().getLivrosBD().size() > 0) {
						System.out.println("Livros ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
							System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
									+ BD.getInstance().getLivrosBD().get(i).getNome());
						}
						System.out.println("-------------------------------------");
						System.out.println("Digite o id para remover");
						long id = inteiro.nextInt();
						if (cl.removerLivro(id)) {
							System.out.println("removido com sucesso");
							System.out.println("-------------------------------------");
						} else {
							System.out.println("FALHA - Livro não encontrado");
							System.out.println("-------------------------------------");
						}
						System.out.println("Livros ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getLivrosBD().size(); i++) {
							System.out.println(BD.getInstance().getLivrosBD().get(i).getId() + " "
									+ BD.getInstance().getLivrosBD().get(i).getNome());
						}
						System.out.println("-------------------------------------");
						System.out.println("Deseja continuar removendo? s - n");
						String rsp = str.nextLine();

						if (!rsp.equals("s")) {
							loop = false;
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
						loop = false;
					}
				}
				break;
			}
			case "9": {

				boolean loop = true;

				while (loop) {
					System.out.println("-------------------------------------");
					if (BD.getInstance().getAlugadoresBD().size() > 0) {
						System.out.println("Alugadores ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
							System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
									+ BD.getInstance().getAlugadoresBD().get(i).getNome());
						}
						System.out.println("-------------------------------------");
						System.out.println("Digite o id para remover");
						long id = inteiro.nextInt();
						if (calu.removerAlugador(id)) {
							System.out.println("removido com sucesso");
							System.out.println("-------------------------------------");
						} else {
							System.out.println("FALHA - Alugador não encontrado");
							System.out.println("-------------------------------------");
						}
						System.out.println("Alugadores ja cadastrados na biblioteca");
						System.out.println("-------------------------------------");
						System.out.println("id - nome");
						for (int i = 0; i < BD.getInstance().getAlugadoresBD().size(); i++) {
							System.out.println(BD.getInstance().getAlugadoresBD().get(i).getId() + " "
									+ BD.getInstance().getAlugadoresBD().get(i).getNome());
						}
						System.out.println("-------------------------------------");
						System.out.println("Deseja continuar cadastrando? s - n");
						String rsp = str.nextLine();

						if (!rsp.equals("s")) {
							loop = false;
						}
					} else {
						System.out.println("nao possui nenhum cadastrado");
						loop = false;
					}
					
				}

				break;
			}
			default: {
				System.out.println("insira os numeros citados no menu");
			}

			}

		}

	}

}
