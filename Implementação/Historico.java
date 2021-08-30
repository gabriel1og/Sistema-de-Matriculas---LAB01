
public class Historico {
	private double nota;
	private Disciplina disc;
	
	public Historico(double nota, Disciplina disc) {
		this.setDisc(disc);
		this.setNota(nota);
	}

	@Override
	public String toString() {
		return "Historico [Nota: " + nota + "]";
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public Disciplina getDisc() {
		return disc;
	}

	public void setDisc(Disciplina disc) {
		this.disc = disc;
	}
}
