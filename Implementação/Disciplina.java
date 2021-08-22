import java.util.ArrayList;
import java.util.List;

public class Disciplina {
	private String nome;
	private List<Aluno> alunos;
	
	public List<Aluno> getAl() {
		return alunos;
	}

	public void setAl(List<Aluno> al) {
		this.alunos = al;
	}

	public Disciplina() {
		alunos = new ArrayList<Aluno>();
	}
	
}
