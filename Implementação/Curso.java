import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import sun.invoke.empty.Empty;

public class Curso implements Serializable{
	@Override
	public String toString() {
		return " Nome: " + nome + ", Número de Créditos: " + numeroCreditos + ", Disciplinas Obrigatórias: " + discObri.stream().map(Disciplina::getNome).collect(Collectors.joining(" \\ ")) + ", Disciplinas Opcionais: "
				+ discOpci.stream().map(Disciplina::getNome).collect(Collectors.joining(" \\ "));
	}

	private String nome;
	private int numeroCreditos;
	private List<Disciplina> discObri;
	private List<Disciplina> discOpci;

	public Disciplina disciplina(String nome, boolean x) {
		if(x) {
			return this.getDiscObri().stream().filter((m) -> m.getNome().equals(nome)).findFirst().orElse(null);
		}
		else
		{
			return this.getDiscOpci().stream().filter((m) -> m.getNome().equals(nome)).findFirst().orElse(null);
		}
	}

	public String profsDoCurso() {
		ArrayList<Professor> prof = new ArrayList<>();
		prof.addAll(this.getDiscObri().stream().map(Disciplina::getProfessor).collect(Collectors.toList()));
		prof.addAll(this.getDiscOpci().stream().map(Disciplina::getProfessor).collect(Collectors.toList()));
		prof.removeAll(Collections.singletonList(null));
		return prof.stream().map(Professor::toString).collect(Collectors.joining("\n"));
	}

	public String disciplinasDoCurso() {
		ArrayList<Disciplina> discs = new ArrayList<>();
		discs.addAll(this.getDiscObri());
		discs.addAll(this.getDiscOpci());
		return discs.stream().map(Disciplina::toString).collect(Collectors.joining());
	}

	public Curso(String nome, int Creditos) {
		this.setNome(nome);
		this.setNumeroCreditos(Creditos);
		this.discObri = new ArrayList<>();
		this.discOpci = new ArrayList<>();
	}

	public void addDisciplinaObri(Disciplina D) {
		this.getDiscObri().add(D);
	}

	public void addDisciplinasOpci(Disciplina D) {
		this.getDiscOpci().add(D);
	}

	public List<Disciplina> getDiscObri() {
		return discObri;
	}

	public void setDiscObri(List<Disciplina> discObri) {
		this.discObri = discObri;
	}

	public List<Disciplina> getDiscOpci() {
		return discOpci;
	}

	public void setDiscOpci(List<Disciplina> discOpci) {
		this.discOpci = discOpci;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumeroCreditos() {
		return numeroCreditos;
	}

	public void setNumeroCreditos(int numeroCreditos) {
		this.numeroCreditos = numeroCreditos;
	}

}
