import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Aluno extends Usuario implements Serializable{
	private int matricula;
	private List<Disciplina> discs;
	private String nome;
	private List<Disciplina> historico;
	private Curso curso;
	private double precoAPagar;
	private Matricula matri;
	private boolean cadastrarObri;
	private boolean cadastrarOpci;
	private boolean pago;

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
		this.setPago(true);
	}
	
	public String disciplinasMatriculadas() {
		return this.getDiscs().stream().map(Disciplina::toString).collect(Collectors.joining("\n"));
	}
	
	public String disciplinasOpciDoCurso() {
		return this.getCurso().getDiscOpci().stream().map(Disciplina::toString).collect(Collectors.joining());
	}
	
	public String disciplinasObriDoCurso() {
		return this.getCurso().getDiscObri().stream().map(Disciplina::toString).collect(Collectors.joining());
	}
	
	public String printHistorico() {
		return this.getHistorico().stream().map(Disciplina::toString).collect(Collectors.joining("\n"));
	}

	public String matricularObri(String nome) {
		String s;
		if(this.isCadastrarObri())
		{
			Disciplina d = this.getCurso().disciplina(nome, true);
			if(d == null) {
				s = "Disciplina não encontrada!";
			}
			else {
				this.getMatri().contObriMaisUm();
				if(this.isCadastrarObri())
				{
					d.addAluno(this);
					this.addDiscs(d);
					this.setPrecoAPagar(this.getPrecoAPagar() + d.getPreco());
					s = "Matriculado na disciplina com sucesso!";
				}
				else
				{
					s = "Maximo de obrigatorias ja atingido!";
				}
			}
		}
		else
		{
			s = "Maximo de materias Atingidas!";
		}
		return s;
	}

	public String matricularOpci(String nome) {
		String s;
		if(this.isCadastrarOpci()) {
			Disciplina d = this.getCurso().disciplina(nome, false);
			if(d == null) {
				s = "Disciplina não encontrada!";
			}
			else {
				this.getMatri().contOpciMaisUm();
				if(this.isCadastrarOpci())
				{
					d.addAluno(this);
					this.addDiscs(d);
					this.setPrecoAPagar(this.getPrecoAPagar() + d.getPreco());
					s = "Matriculado na disciplina com sucesso!";
				}
				else
				{
					s = "Maximo de opcionais ja atingido!";
				}
			}
		}
		else {
			s = "Maximo de materias Atingidas!";
		}
		return s;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
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

	public List<Disciplina> getHistorico() {
		return historico;
	}

	public void setHistorico(List<Disciplina> historico) {
		this.historico = historico;
	}

	@Override
	public String toString() {
		return " Nome: " + nome + " \\ Matrícula: " + matricula +  " \\ Preço a Pagar: " + precoAPagar + " \\ email: " + super.getEmail();
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
	
	public void disciplinasDesativadas() {
		this.getDiscs().removeIf((m) -> m.isAtiva() == false);
	}
}
