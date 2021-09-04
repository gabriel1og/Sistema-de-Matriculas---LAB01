import java.io.Serializable;
import java.util.List;

public class Oferta implements Serializable{
	private static final int MAX_ALUNOS = 60;
	private static final int MIN_ALUNOS = 3;
	private Disciplina disc;
	
	public Oferta(Disciplina disc) {
		this.setDisc(disc);
	}


	public Disciplina getDisc() {
		return disc;
	}

	public void setDisc(Disciplina disc) {
		this.disc = disc;
	}

	public int getMaxAlunos() {
		return MAX_ALUNOS;
	}

	public static int getMinAlunos() {
		return MIN_ALUNOS;
	}
}
