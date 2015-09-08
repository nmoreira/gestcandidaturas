package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;

@Named
@ViewScoped
public class PesquisaCandidaturas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CandidaturaService candidaturaServ;

	private Set<Candidatura> resultadosPesquisa = null;
	private List<Candidatura> resultadosList = new ArrayList<Candidatura>();

	private String nome;
	private String apelido;
	private String email;
	private String morada;
	private String cidade;
	private long telefone;
	private long telemovel;
	private String pais;
	private String curso;
	private String escola;

	public void Pesquisar() {
		resultadosPesquisa = null;

		List<List<Candidatura>> resultados = new ArrayList<>();
		List<Candidatura> pnome = null;
		List<Candidatura> papelido = null;
		List<Candidatura> pemail = null;
		List<Candidatura> pmorada = null;
		List<Candidatura> pcidade = null;
		List<Candidatura> ptelefone = null;
		List<Candidatura> ptelemovel = null;
		List<Candidatura> ppais = null;
		List<Candidatura> pcurso = null;
		List<Candidatura> pescola = null;

		if (!nome.equals("")) {
			pnome = new ArrayList<>();
			pnome.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoNome(nome));
			resultados.add(pnome);
		}

		if (!apelido.equals("")) {
			papelido = new ArrayList<>();
			papelido.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoApelido(apelido));
			resultados.add(papelido);
		}

		if (!email.equals("")) {
			pemail = new ArrayList<>();
			pemail.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoEmail(email));
			resultados.add(pemail);
		}

		if (!morada.equals("")) {
			pmorada = new ArrayList<>();
			pmorada.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoMorada(morada));
			resultados.add(pmorada);
		}

		if (!cidade.equals("")) {
			pcidade = new ArrayList<>();
			pcidade.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoCidade(cidade));
			resultados.add(pcidade);
		}

		if (telefone != 0) {
			ptelefone = new ArrayList<>();
			ptelefone.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoTelefone(telefone));
			resultados.add(ptelefone);
		}

		if (telemovel != 0) {
			ptelemovel = new ArrayList<>();
			ptelemovel.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoTelemovel(telemovel));
			resultados.add(ptelemovel);
		}

		if (!pais.equals("")) {
			ppais = new ArrayList<>();
			ppais.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoPais(pais));
			resultados.add(ppais);
		}

		if (!curso.equals("")) {
			pcurso = new ArrayList<>();
			pcurso.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoCurso(curso));
			resultados.add(pcurso);
		}

		if (!escola.equals("")) {
			pescola = new ArrayList<>();
			pescola.addAll(candidaturaServ
					.pesquisaCandidaturasByCandidatoEscola(escola));
			resultados.add(pescola);
		}

		if (resultados.size() == 0) {
			resultadosPesquisa = new HashSet<>();
			resultadosPesquisa.addAll(candidaturaServ.listaCandidaturas());
		} else {
			for (List<Candidatura> list : resultados) {
				if (resultadosPesquisa == null) {
					resultadosPesquisa = new HashSet<>();
					resultadosPesquisa.addAll(list);
				} else {
					resultadosPesquisa.retainAll(list);
				}
			}
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		this.morada = morada;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public long getTelemovel() {
		return telemovel;
	}

	public void setTelemovel(long telemovel) {
		this.telemovel = telemovel;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getEscola() {
		return escola;
	}

	public void setEscola(String escola) {
		this.escola = escola;
	}

	public List<Candidatura> getResultadosPesquisa() {

		if (resultadosPesquisa == null) {
			return resultadosList;
		} else {
			resultadosList.clear();
			resultadosList.addAll(resultadosPesquisa);
			return resultadosList;
		}

	}
}
