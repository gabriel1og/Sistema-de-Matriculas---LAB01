
public class Professor extends Usuario{
	@Override
	public String toString() {
		return "Professor [Nome: " + nome + "]";
	}

	private Disciplina disc;
	private String nome;
	
	public Professor(String email, int senha, int confirmar, Disciplina disc, String nome) {
		super(email, senha, confirmar);
		this.setDisc(disc);
		this.setNome(nome);
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
