import java.io.Serializable;
import java.util.stream.Collectors;

public class Professor extends Usuario implements Serializable{
	@Override
	public String toString() {
		return " Nome: " + nome + " \\ Ministra na Disciplina: " + this.disc.getNome() + " \\ email: " + super.getEmail();
	}

	private Disciplina disc;
	private String nome;
	
	public Professor(String email, int senha, int confirmar, Disciplina disc, String nome) {
		super(email, senha, confirmar);
		this.setDisc(disc);
		this.setNome(nome);
	}
	
	public String printAlunos() {
		return this.getDisc().getAlunos().stream().map(Aluno::toString).collect(Collectors.joining("\n"));
	}
	
	public Disciplina getDisc() {
		return disc;
	}

	public void setDisc(Disciplina disc) {
		this.disc = disc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
