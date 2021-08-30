import java.util.ArrayList;
import java.util.List;

public class Universidade {
	private List<Disciplina> disc;
	private List<Usuario> Usuario;
	private List<Curso> Cursos;
	
	public List<Curso> getCursos() {
		return Cursos;
	}

	public void setCursos(List<Curso> cursos) {
		Cursos = cursos;
	}

	public Universidade() {
		this.disc = new ArrayList<>();
		this.Usuario = new ArrayList<>();
		this.Cursos = new ArrayList<>();
	}
	
	public void addCurso(Curso C) {
		this.getCursos().add(C);
	}
	
	public void addDisc(Disciplina D) {
		this.getDisc().add(D);
	}

	public void addUsuario(Usuario U) {
		this.getUsuario().add(U);
	}
	
	public List<Disciplina> getDisc() {
		return disc;
	}

	public void setDisc(List<Disciplina> disc) {
		this.disc = disc;
	}

	public List<Usuario> getUsuario() {
		return Usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		Usuario = usuario;
	}

	@Override
	public String toString() {
		return "Universidade [Disciplinas:" + disc.toString() + "]";
	}
	
	
}
