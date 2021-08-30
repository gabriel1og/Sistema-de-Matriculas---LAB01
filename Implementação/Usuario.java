
public class Usuario {
	private String email;
	private int senha;
	private int confirmar;
	
	public Usuario(String email, int senha, int confirmar) {
		this.setConfirmar(confirmar);
		this.setEmail(email);
		this.setSenha(senha);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public int getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(int confirmar) {
		this.confirmar = confirmar;
	}
	
}
