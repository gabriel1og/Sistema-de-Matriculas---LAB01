import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	private String nome;
	private List<Aluno> alunos;
	private Professor professor;
	private boolean ativa;
	private double preco;
	private Oferta oferta;
	private boolean matriculas;
	
	public boolean isMatriculas() {
		return matriculas;
	}

	public void setMatriculas(boolean matriculas) {
		this.matriculas = matriculas;
	}

	public Disciplina(String nome, Professor professor, double preco) {
		this.setNome(nome);
		this.setPreco(preco);
		this.setAtiva(false);
		this.setProfessor(professor);
		this.alunos = new ArrayList<>();
		this.oferta = new Oferta(this);
	}

	public void addAluno(Aluno a) {
		if(this.getAlunos().size() < this.getOferta().getMaxAlunos() && this.isMatriculas())
		{
			this.getAlunos().add(a);
		}
		else
		{
			this.setMatriculas(false);
		}
	}
	
	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	@Override
	public String toString() {
		return "Disciplina [Nome: " + nome + ", Alunos: " + alunos.stream().map((m) -> m.toString()) + ", professor=" + professor.toString() + ", ativa=" + ativa
				+ ", preco=" + preco + "]";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public boolean isAtiva() {
		return ativa;
	}

	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
