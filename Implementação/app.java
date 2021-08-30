import java.util.Scanner;

public class app {

	public static void menuPrincipal() {
		System.out.println("0 - Sair\n1 - Log In\n2 - Cadastrar\n3 - Espaço Secretaria");
	}

	public static void menuCadastrar() {
		System.out.println("0 - Sair\n1 - Cadastrar como Professor\n2 - Cadastrar como Aluno");
	}

	public static void menuSecretaria() {
		System.out.println("0 - Sair\n1 - Cadastrar Disciplina\n2 - Gerar Curriculo\n3 - Cadastrar Curso");
	}

	public static void menuProfessor() {
		System.out.println("0 - Sair\n1 - Visualizar alunos de sua disciplina");
	}

	public static void menuAluno() {
		System.out.println("0 - Sair\n1 - Se matricular nas disciplinas\n2 - Cancelar Matricula");
	}

	public static void main(String[] args) {
		Universidade UNI = new Universidade();
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
					login = UNI.getUsuario().stream().filter((m) -> m.getEmail().equals(email2)).findFirst().orElse(null);
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
					if(login.getSenha() == senha2) {
						teste5 = 1;
					}
					else {
						System.out.println("Senha invalida!");
					}
				}while(teste5 == 0);
				if(login instanceof Professor) {
					int scan4 = 10;
					do {
						menuProfessor();
						switch(scan4) {
						case 1:
							System.out.println(((Professor) login).getDisc().getAlunos().toString());
						}
					}while(scan4 != 0);
				}
				else
				{
					int scan5 = 10;
					do {
						menuAluno();
						scan5 = tec.nextInt();
						switch(scan5) {
						case 1:
							System.out.println("Desja-se cadastrar em uma disciplina obrigatoria ou opcional? (Obrigatoria = 1 / Opcional = 2)");
							int resposta3 = tec.nextInt();
							if(resposta3 == 1) {
								System.out.println("Insira o nome da Disciplina:");
								String nome = tec.next();
								((Aluno) login).matricularObri(nome);
							}
							else
							{
								System.out.println("Insira o nome da Disciplina:");
								String nome = tec.next();
								((Aluno) login).matricularOpci(nome);
							}
						}
					}while(scan5 != 0);
				}
				break;
			case 2:
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
						disc = UNI.getDisc().stream().filter(m -> m.getNome().equals(tentativa)).findFirst().orElse(null);
						if(disc != null)
						{
							teste2 = 1;
						}
						else
						{
							System.out.println("Disiplina não encontrada!");
						}
					}while(teste2 == 0);
					UNI.addUsuario(new Professor(email, senha, confirmar, disc, Nome));
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
						c = UNI.getCursos().stream().filter((m) -> m.getNome().equals(curso)).findFirst().orElse(null);
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
				break;
			case 3:
				do {
					menuSecretaria();
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
						System.out.println(UNI.toString());
						break;
					case 3:
						String nome1;
						int creditos;
						System.out.println("Insira o nome do Curso:");
						nome1 = tec.next();
						System.out.println("Insira os creditos da Disciplina");
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
								Disciplina disc = UNI.getDisc().stream().filter(m -> m.getNome().equals(nome3)).findFirst().orElse(null);
								if(disc == null)
								{
									System.out.println("Nome Incorreto!");
								}
								else {
									System.out.println("A disciplina que será adicionada é obrigatoria? (Sim = 1 / Não = 2)");
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
					}
				}while(scan != 0);

			}
		}while(scan != 0);
	}

}
