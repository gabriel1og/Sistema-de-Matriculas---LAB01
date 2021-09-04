import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Secretaria implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Disciplina> disc;
	private List<Usuario> Usuario;
	private List<Curso> Cursos;
	private boolean matriculasAbertas;
	private SistemaDeCobrança sis;

	public void setMatriculasAbertas(boolean matriculasAbertas) {
		this.matriculasAbertas = matriculasAbertas;
		if(!this.isMatriculasAbertas())
		{
			this.getDisc().stream().forEach((m) -> m.verificarDisciplina());
			this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).forEach((m) -> m.disciplinasDesativadas());
			this.getSis().cobrarAluno();
		}
		else
		{
			this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).forEach((m) -> m.getHistorico().addAll(m.getDiscs()));
			this.getDisc().stream().forEach((m) -> m.setAtiva(false));
			this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).forEach((m) -> m.getDiscs().clear());
		}
	}

	public Usuario encontrarLogIn(String s) {
		return this.getUsuario().stream().filter((m) -> m.getEmail().equals(s)).findFirst().orElse(null);
	}
	
	public String alunosComPagamento() {
		return this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).filter((m) -> m.isPago() == true).map(Aluno::toString).collect(Collectors.joining("\n"));
	}

	public String alunosSemPagamento() {
		return this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).filter((m) -> m.isPago() == false).map(Aluno::toString).collect(Collectors.joining("\n"));
	}

	public String printAlunos() {
		return this.getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).map(Aluno::toString).collect(Collectors.joining("\n"));
	}

	public String printProfessores() {
		return this.getUsuario().stream().filter((m) -> m instanceof Professor).map((m) -> (Professor) m).map(Professor::toString).collect(Collectors.joining("\n"));
	}

	public String disciplinasAtivas() {
		return this.getDisc().stream().filter((m) -> m.isAtiva() == true).map(Disciplina::toString).collect(Collectors.joining());
	}

	public String disciplinasDesativas() {
		return this.getDisc().stream().filter((m) -> m.isAtiva() == false).map(Disciplina::toString).collect(Collectors.joining());
	}

	public String printDisciplinas() {
		return this.getDisc().stream().map(Disciplina::toString).collect(Collectors.joining());
	}
	
	public String printCursos() {
		return this.getCursos().stream().map(Curso::toString).collect(Collectors.joining("\n"));
	}
	
	public Disciplina encontrarDisciplina(String r) {
		return getDisc().stream().filter((m) -> m.getNome().equals(r)).findFirst().orElse(null);
	}
	
	public Curso encontrarCurso(String r) {
		return this.getCursos().stream().filter((m) -> m.getNome().equals(r)).findFirst().orElse(null);
	}
	
	public String encontrarAlunoDoCurso(Curso curso) {
		return getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).filter((m) -> m.getCurso().equals(curso)).map(Aluno::toString).collect(Collectors.joining("\n"));
	}

	public boolean isMatriculasAbertas() {
		return matriculasAbertas;
	}

	public List<Curso> getCursos() {
		return Cursos;
	}

	public void setCursos(List<Curso> cursos) {
		Cursos = cursos;
	}

	public Secretaria() {
		this.disc = new ArrayList<>();
		this.Usuario = new ArrayList<>();
		this.Cursos = new ArrayList<>();
		this.sis = new SistemaDeCobrança(this);
		this.setMatriculasAbertas(true);
	}

	public SistemaDeCobrança getSis() {
		return sis;
	}

	public void setSis(SistemaDeCobrança sis) {
		this.sis = sis;
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
		return " Universidade -> Disciplinas: " + disc.toString() + " Cursos: " + Cursos.toString();
	}


}
