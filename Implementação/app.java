import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class app {

	public static void menuPrincipal() {
		System.out.println("0 - Sair\n1 - Log In\n2 - Cadastrar\n3 - Espaço Secretaria");
	}

	public static void menuCadastrar() {
		System.out.println("0 - Sair\n1 - Cadastrar como Professor\n2 - Cadastrar como Aluno");
	}

	public static void menuSecretaria(String x) {
		System.out.println("0 - Sair\n1 - Cadastrar Disciplina\n2 - Gerar Curriculo\n3 - Cadastrar Curso\n4 - Cadastrar disciplinas em um curso\n" + x);
	}

	public static void menuProfessor() {
		System.out.println("0 - Sair\n1 - Visualizar alunos de sua disciplina");
	}

	public static void menuAluno(String x) {
		System.out.println("0 - Sair\n1 - Se matricular nas disciplinas\n2 - Visualizar disciplinas matriculadas\n3 - Cancelar Matricula\n4 - Visualizar historico\n" + x);
	}

	public static void menuCurriculo() {
		System.out.println("0 - Sair\n1 - Visualizar alunos da universidade\n2 - Visualizar professores da universidade\n3 - Visualizar disciplinas da universidade\n4 - Visualizar cursos da universidade\n5 - Visualizar alunos de uma certa Disciplina\n6 - Visualizar professores de uma certa disciplina\n7 - Visualizar alunos de um certo curso\n8 - Visualizar professores de um certo curso\n9 - Visualizar Disciplinas de um certo curso");
	}
	
	public static void menuVisualizarAlunos() {
		System.out.println("0 - Sair\n1 - Visualizar alunos que efetuaram o pagamento\n2 - Visualizar alunos que não efetuaram o pagamento\n3 - Visualizar todos os alunos");
	}
	
	public static void menuVisualizarDisciplina() {
		System.out.println("0 - Sair\n1 - Visualizar disciplinas ativas\n2 - Visualizar disciplinas desativas\n3 - Visualizar todas as disciplinas");
	}
	
	public static Secretaria SerializarLeitor() throws ClassNotFoundException, FileNotFoundException, IOException, EOFException {
		Secretaria uni = new Secretaria();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Universidade.ser"))) {
			uni = (Secretaria) ois.readObject();
		} catch (FileNotFoundException e) {}
		return uni;
	}
	
	public static void SerializarEscrita(Secretaria uni) {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Universidade.ser"))) {
			oos.writeObject(uni);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, FileNotFoundException, IOException {
		Secretaria UNI = SerializarLeitor();
		Scanner tec = new Scanner(System.in);
		int scan = 10;
		int scan2 = 10;
		int scan3 = 10;
		do {
			menuPrincipal();
			scan = tec.nextInt();
			switch(scan) {
			case 1:
				int senha2;
				int teste4 = 0;
				Usuario login;
				do {
					System.out.println("Insira o seu email:");
					String email2 = tec.next();
					login = UNI.encontrarLogIn(email2);
					if(login != null) {
						teste4 = 1;
					}
					else
					{
						System.out.println("email incorreto!");
					}
				}while(teste4 == 0);
				int teste5 = 0;
				do {
					System.out.println("Insira a senha:");
					senha2 = tec.nextInt();
					if(login.confirmaSenha(senha2)) {
						teste5 = 1;
					}
					else 
					{
						System.out.println("Senha invalida!");
					}
				}while(teste5 == 0);
				if(login instanceof Professor) {
					int scan4 = 10;
					do {
						menuProfessor();
						scan4 = tec.nextInt();
						switch(scan4) {
						case 1:
							System.out.println(((Professor) login).printAlunos());
						}
					}while(scan4 != 0);
				}
				else
				{
					int scan5 = 10;
					do {
						String s;
						if(((Aluno) login).isPago())
						{
							s = "";
						}
						else
						{
							s = "5 - Pagar: " + ((Aluno) login).getPrecoAPagar();
						}
						menuAluno(s);
						scan5 = tec.nextInt();
						switch(scan5) {
						case 1:
							if(UNI.isMatriculasAbertas())
							{
								System.out.println("Desja-se cadastrar em uma disciplina obrigatoria ou opcional? (Obrigatoria = 1 / Opcional = 2)");
								int resposta3 = tec.nextInt();
								if(resposta3 == 1) {
									System.out.println("Escolha uma das Disciplinas abaixo:");
									System.out.println(((Aluno) login).disciplinasObriDoCurso());
									String nome = tec.next();
									System.out.println(((Aluno) login).matricularObri(nome));
								}
								else
								{
									System.out.println("Escolha uma das Disciplinas abaixo:");
									System.out.println(((Aluno) login).disciplinasOpciDoCurso());
									String nome = tec.next();
									System.out.println(((Aluno) login).matricularOpci(nome));
								}
							}
							else
							{
								System.out.println("Tempo de Matricula já finalizado!");
							}
							break;
						case 2:
							System.out.println(((Aluno) login).disciplinasMatriculadas());
							break;
						case 3:
							UNI.getUsuario().remove(login);
							System.out.println("Matricula removida com sucesso!");
							scan5 = 0;
							break;
						case 4:
							System.out.println(((Aluno) login).printHistorico());
							break;
						case 5: 
							UNI.getSis().receberPagamento((Aluno) login);
							break;
						}
					}while(scan5 != 0);
				}
				break;
			case 2:
				if(UNI.isMatriculasAbertas())
				{
				do {
					menuCadastrar();
					scan2 = tec.nextInt();
					switch(scan2) {
					case 1:
						String email;
						int senha;
						int confirmar;
						String Nome;
						Disciplina disc;
						System.out.println("Insira o nome do professor:");
						Nome = tec.next();
						int teste = 0;
						do {
							System.out.println("Insira a senha:");
							senha = tec.nextInt();
							System.out.println("Confirme a senha:");
							confirmar = tec.nextInt();
							if(senha == confirmar) {
								teste = 1;
							}
							else {
								System.out.println("Confirmação invalidada!");
							}
						}while(teste == 0);
						System.out.println("Insira o email:");
						email = tec.next();
						int teste2 = 0;
						do {
							System.out.println("Insira o nome da disciplina em que exercera a função:");
							String tentativa = tec.next();
							disc = UNI.encontrarDisciplina(tentativa);
							if(disc != null)
							{
								teste2 = 1;
							}
							else
							{
								System.out.println("Disiplina não encontrada!");
							}
						}while(teste2 == 0);
						Professor p = new Professor(email, senha, confirmar, disc, Nome);
						disc.setProfessor(p);
						UNI.addUsuario(p);
						break;
					case 2:
						int matricula;
						String nome;
						String email1;
						int senha1;
						int confirmar1;
						System.out.println("Insira o nome do aluno:");
						nome = tec.next();
						int teste3 = 0;
						do {
							System.out.println("Insira a senha:");
							senha1 = tec.nextInt();
							System.out.println("Confirme a senha:");
							confirmar1 = tec.nextInt();
							if(senha1 == confirmar1) {
								teste3 = 1;
							}
							else {
								System.out.println("Confirmação invalidada!");
							}
						}while(teste3 == 0);
						System.out.println("Insira o email:");
						email1 = tec.next();
						System.out.println("Insira a matricula:");
						matricula = tec.nextInt();
						int teste7 = 0;
						Curso c;
						do {
							System.out.println("Insira o nome do curso: ");
							String curso = tec.next();
							c = UNI.encontrarCurso(curso);
							if(c == null) {
								System.out.println("Nome do curso não encontrado!");
							}
							else
							{
								teste7 = 1;
							}
						}while(teste7 == 0);
						UNI.addUsuario(new Aluno(email1, senha1, confirmar1, matricula, nome, c));
						break;
					}
				}while(scan2 != 0);
				break;
				}
				else
				{
					System.out.println("Periodo de inscrição ja finalizado");
				}
				break;
			case 3:
				do {
					String x = "5 - Finalizar periodo de inscrição de matriculas";
					String y = "5 - Finalizar semestre Letivo";
					boolean z = false;
					if(UNI.isMatriculasAbertas()) {
						menuSecretaria(x);
						z = false;
					}
					else
					{
						menuSecretaria(y);
						z = true;
					}
					scan3 = tec.nextInt();
					switch(scan3) {
					case 1:
						String nome;
						double preco;
						System.out.println("Insira o nome da Disciplina: ");
						nome = tec.next();
						System.out.println("Insira o preco da Disciplina: ");
						preco = tec.nextDouble();
						UNI.addDisc(new Disciplina(nome, null, preco));
						break;
					case 2:
						int scan6 = 10;
						do {
							menuCurriculo();
							tec.nextLine();
							scan6 = tec.nextInt();
							switch(scan6) {
							case 1:
								int scan7 = 10;
								do {
									menuVisualizarAlunos();
									tec.nextLine();
									scan7 = tec.nextInt();
									switch(scan7) {
									case 1:
										System.out.println(UNI.alunosComPagamento());
										break;
									case 2:
										System.out.println(UNI.alunosSemPagamento());
										break;
									case 3:
										System.out.println(UNI.printAlunos());
										break;
									}
								}while(scan7 != 0);
								break;
							case 2:
								System.out.println(UNI.printProfessores());
								break;
							case 3:
								int scan8 = 10;
								do {
									menuVisualizarDisciplina();
									tec.nextLine();
									scan8 = tec.nextInt();
									switch(scan8) {
									case 1:
										System.out.println(UNI.disciplinasAtivas());
										break;
									case 2:
										System.out.println(UNI.disciplinasDesativas());
										break;
									case 3:
										System.out.println(UNI.printDisciplinas());
									}
								}while(scan8 != 0);
								break;
							case 4:
								System.out.println(UNI.printCursos());
								break;
							case 5:
								int teste = 0;
								Disciplina c;
								do {
									System.out.println("Insira o nome da disciplina:");
									String resposta = tec.next();
									c = UNI.encontrarDisciplina(resposta);
									if(c == null) {
										System.out.println("Nome do disciplina não encontrado!");
									}
									else
									{
										teste = 1;
									}
								}while(teste == 0);
								System.out.println(c.printAlunos());
								break;
							case 6:
								int teste1 = 0;
								Disciplina c1;
								do {
									System.out.println("Insira o nome da disciplina:");
									String resposta = tec.next();
									c1 = UNI.encontrarDisciplina(resposta);
									if(c1 == null) {
										System.out.println("Nome do curso não encontrado!");
									}
									else
									{
										teste1 = 1;
									}
								}while(teste1 == 0);
								System.out.println(c1.getProfessor().toString());
								break;
							case 7:
								int teste9 = 0;
								Curso c2;
								do {
									System.out.println("Insira o nome do curso: ");
									String resposta = tec.next();
									c2 = UNI.encontrarCurso(resposta);
									if(c2 == null) {
										System.out.println("Nome do curso não encontrado!");
									}
									else
									{
										teste9 = 1;
									}
								}while(teste9 == 0);
								Curso curso = c2;
								System.out.println(UNI.encontrarAlunoDoCurso(curso));
								break;
							case 8:
								int teste10 = 0;
								Curso c3;
								do {
									System.out.println("Insira o nome do curso: ");
									String resposta = tec.next();
									c3 = UNI.encontrarCurso(resposta);
									if(c3 == null) {
										System.out.println("Nome do curso não encontrado!");
									}
									else
									{
										teste10 = 1;
									}
								}while(teste10 == 0);
								System.out.println(c3.profsDoCurso());
								break;
							case 9:
								int teste11 = 0;
								Curso c4;
								do {
									System.out.println("Insira o nome do curso: ");
									String resposta = tec.next();
									c4 = UNI.encontrarCurso(resposta);
									if(c4 == null) {
										System.out.println("Nome do curso não encontrado!");
									}
									else
									{
										teste11 = 1;
									}
								}while(teste11 == 0);
								System.out.println(c4.disciplinasDoCurso());
								break;
							}
						}while(scan6 != 0);
						System.out.println();
						break;
					case 3:
						String nome1;
						int creditos;
						System.out.println("Insira o nome do Curso:");
						nome1 = tec.next();
						System.out.println("Insira os creditos da Curso");
						tec.nextLine();
						creditos = tec.nextInt();
						Curso curso = new Curso(nome1, creditos);
						UNI.addCurso(curso);
						System.out.println("Deseja adicionar disciplinas no curso agora? (Sim = 1 / Não = 2)");
						int resposta = tec.nextInt();
						if(resposta == 1) {
							int teste6 = 0;
							do {
								System.out.println("Insira o nome da Disciplina:");
								String nome3 = tec.next();
								Disciplina disc = UNI.encontrarDisciplina(nome3);
								if(disc == null)
								{
									System.out.println("Nome Incorreto!");
								}
								else
								{
									System.out.println("A disciplina que será adicionada é obrigatoria? (Sim = 1 / Não = 2)");
									tec.nextLine();
									int resposta2 = tec.nextInt();
									if(resposta2 == 1)
									{
										curso.addDisciplinaObri(disc);
									}
									else
									{
										curso.addDisciplinasOpci(disc);
									}
								}
								System.out.println("Deseja adicionar mais disciplinas no curso agora? (Sim = 1 / Não = 2)");
								int resposta1 = tec.nextInt();
								if(resposta1 == 2) {
									teste6 = 1;
								}
							}while(teste6 == 0);
						}
						break;
					case 4:
						int teste8 = 0;
						Curso c;
						do {
							System.out.println("Insira o nome do curso: ");
							String nomeCurso = tec.next();
							c = UNI.encontrarCurso(nomeCurso);
							if(c == null) {
								System.out.println("Curso não encontrado!");
							}
							else {
								teste8 = 1;
							}
						}while(teste8 == 0);
						int scan5 = 10;
						do {
							System.out.println("0 - Sair\n1 - Adicionar Disciplina ao Curso " + c.getNome());
							tec.nextLine();
							scan5 = tec.nextInt();
							switch(scan5) {
							case 1:
								int teste9 = 0;
								Disciplina d;
								do {
									System.out.println("Insira o nome da disciplina");
									String resposta1 = tec.next();
									d = UNI.encontrarDisciplina(resposta1);
									if(d == null) {
										System.out.println("Disciplina não encontrada!");
									}
									else {
										teste9 = 1;
									}
								}while(teste9 == 0);
								System.out.println("A disciplina que será adicionada é obrigatoria? (Sim = 1 / Não = 2)");
								tec.nextLine();
								int resposta2 = tec.nextInt();
								if(resposta2 == 1) {
									c.addDisciplinaObri(d);
								}
								else
								{
									c.addDisciplinasOpci(d);
								}
								break;
							}
						}while(scan5 != 0);
						break;
					case 5:
						UNI.setMatriculasAbertas(z);
						break;
					}
				}while(scan3 != 0);

			}
		}while(scan != 0);
		SerializarEscrita(UNI);
	}

}
