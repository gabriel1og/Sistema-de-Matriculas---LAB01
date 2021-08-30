import java.util.ArrayList;
import java.util.List;

public class Aluno extends Usuario{
	private int matricula;
	private List<Disciplina> discs;
	private String nome;
	private List<Historico> historico;
	private Curso curso;
	private double precoAPagar;
	private Matricula matri;
	private boolean cadastrarObri;
	private boolean cadastrarOpci;

	public Aluno(String email, int senha, int confirmar, int matricula, String nome, Curso curso) {
		super(email, senha, confirmar);
		this.setNome(nome);
		this.setMatricula(matricula);
		this.discs = new ArrayList<>();
		this.historico = new ArrayList<>();
		this.matri = new Matricula(this);
		this.setCadastrarObri(true);
		this.setCadastrarOpci(true);
		this.setCurso(curso);
	}

	public void matricularObri(String nome) {
		if(this.isCadastrarObri())
		{
			Disciplina d = this.getCurso().getDiscObri().stream().filter((m) -> m.getNome().equals(nome)).findFirst().orElse(null);
			if(d == null) {
				System.out.println("Disciplina não encontrada!");
			}
			else {
				d.addAluno(this);
				this.getMatri().contObriMaisUm();
				this.addDiscs(d);
				this.setPrecoAPagar(d.getPreco() + this.getPrecoAPagar());
			}
		}
		else
		{
			System.out.println("Maximo de materias Atingidas!");
		}
	}
	
	public void matricularOpci(String nome) {
		if(this.isCadastrarOpci()) {
			Disciplina d = this.getCurso().getDiscOpci().stream().filter((m) -> m.getNome().equals(nome)).findFirst().orElse(null);
			if(d == null) {
				System.out.println("Disciplina não encontrada!");
			}
			else {
				d.addAluno(this);
				this.getMatri().contOpciMaisUm();
				this.addDiscs(d);
				this.setPrecoAPagar(d.getPreco() + this.getPrecoAPagar());
			}
		}
		else {
			System.out.println("Maximo de materias atingidas!");
		}
	}
	
	public void addDiscs(Disciplina d) {
		this.getDiscs().add(d);
	}

	public Matricula getMatri() {
		return matri;
	}

	public void setMatri(Matricula matri) {
		this.matri = matri;
	}

	public boolean isCadastrarObri() {
		return cadastrarObri;
	}

	public void setCadastrarObri(boolean cadastrarObri) {
		this.cadastrarObri = cadastrarObri;
	}

	public boolean isCadastrarOpci() {
		return cadastrarOpci;
	}

	public void setCadastrarOpci(boolean cadastrarOpci) {
		this.cadastrarOpci = cadastrarOpci;
	}



	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public List<Disciplina> getDiscs() {
		return discs;
	}

	public void setDiscs(List<Disciplina> disc) {
		this.discs = disc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Historico> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}

	@Override
	public String toString() {
		return "Aluno [Matricula: " + matricula + ", Nome: " + nome + ", Historico: " + historico.toString()
		+ ", Curso: " + curso + ", Preço a Pagar: " + precoAPagar + "]";
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public double getPrecoAPagar() {
		return precoAPagar;
	}

	public void setPrecoAPagar(double precoAPagar) {
		this.precoAPagar = precoAPagar;
	}
}
