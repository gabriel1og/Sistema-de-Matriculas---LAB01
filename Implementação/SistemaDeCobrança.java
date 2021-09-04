import java.io.Serializable;

public class SistemaDeCobrança implements Serializable{

	private Secretaria sec;
	
	public SistemaDeCobrança(Secretaria s) {
		this.setSec(s);
	}
	
	public void cobrarAluno() {
		this.getSec().getUsuario().stream().filter((m) -> m instanceof Aluno).map((m) -> (Aluno) m).filter((m) -> m.getPrecoAPagar() > 0).forEach((m) -> m.setPago(false));
	}
	
	public void receberPagamento(Aluno a) {
		a.setPrecoAPagar(0);
		a.setPago(true);
	}

	public Secretaria getSec() {
		return sec;
	}

	public void setSec(Secretaria sec) {
		this.sec = sec;
	}
}
