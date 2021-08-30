
public class Matricula {
	private int contObrigatorias = 0;
	private int contOpcionais = 0;
	private Aluno aluno;
	private static final int MAX_OBRIGATORIAS = 4;
	private static final int MAX_OPCIONAIS = 2;
	
	public Matricula(Aluno aluno) {
		this.setAluno(aluno);
	}
	
	public void contObriMaisUm() {
		this.setContObrigatorias(this.getContObrigatorias() + 1);
		if(this.getContObrigatorias() == this.getMaxObrigatorias()) {
			this.getAluno().setCadastrarObri(false);
		}
	}
	
	public void contOpciMaisUm() {
		this.setContOpcionais(this.getContOpcionais() + 1);
		if(this.getContOpcionais() == this.getMaxOpcionais()) {
			this.aluno.setCadastrarOpci(false);
		}
	}
	
	public int getContObrigatorias() {
		return contObrigatorias;
	}
	public void setContObrigatorias(int contObrigatorias) {
		this.contObrigatorias = contObrigatorias;
	}
	public int getContOpcionais() {
		return contOpcionais;
	}
	public void setContOpcionais(int contOpcionais) {
		this.contOpcionais = contOpcionais;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public static int getMaxObrigatorias() {
		return MAX_OBRIGATORIAS;
	}
	public static int getMaxOpcionais() {
		return MAX_OPCIONAIS;
	}
	
	
}
