import java.util.ArrayList;
import java.util.List;

public class Curso {
	private String nome;
	private int numeroCreditos;
	private List<Disciplina> discObri;
	private List<Disciplina> discOpci;

	
	public Curso(String nome, int Creditos) {
		this.setNome(nome);
		this.setNumeroCreditos(Creditos);
		this.discObri = new ArrayList<>();
		this.discOpci = new ArrayList<>();
	}
	
	public void addDisciplinaObri(Disciplina D) {
		this.getDiscObri().add(D);
	}
	
	public void addDisciplinasOpci(Disciplina D) {
		this.getDiscOpci().add(D);
	}

	public List<Disciplina> getDiscObri() {
		return discObri;
	}

	public void setDiscObri(List<Disciplina> discObri) {
		this.discObri = discObri;
	}

	public List<Disciplina> getDiscOpci() {
		return discOpci;
	}

	public void setDiscOpci(List<Disciplina> discOpci) {
		this.discOpci = discOpci;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroCreditos() {
		return numeroCreditos;
	}

	public void setNumeroCreditos(int numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}

}
