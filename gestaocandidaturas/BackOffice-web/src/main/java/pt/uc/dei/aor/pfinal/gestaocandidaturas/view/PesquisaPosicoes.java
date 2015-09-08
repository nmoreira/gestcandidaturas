package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.AreaTecnica;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.EstadoPosicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.LocalPosicao;

@Named
@ViewScoped
public class PesquisaPosicoes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PosicaoService posicaoServ;

	private Set<Posicao> resultadosPesquisa = null;

	private List<Posicao> resultadosList = new ArrayList<Posicao>();

	private Date data;
	private String codigo;
	private String titulo;
	private LocalPosicao local;
	private EstadoPosicao estado;
	private String empresa;
	private AreaTecnica area;

	private Posicao posicaoSelecionada;

	public PesquisaPosicoes() {
	}

	public void Pesquisar() {
		resultadosPesquisa = null;

		List<List<Posicao>> resultados = new ArrayList<>();
		List<Posicao> pdata = null;
		List<Posicao> pcodigo = null;
		List<Posicao> ptitulo = null;
		List<Posicao> plocal = null;
		List<Posicao> pestado = null;
		List<Posicao> pempresa = null;
		List<Posicao> parea = null;

		if (data != null) {
			pdata = new ArrayList<>();
			pdata.addAll(posicaoServ.getPosicoesByData(data));
			resultados.add(pdata);
		}

		if (!codigo.equals("")) {
			pcodigo = new ArrayList<>();
			pcodigo.addAll(posicaoServ.pesquisaCodPosicao(codigo));
			resultados.add(pcodigo);
		}

		if (!titulo.equals("")) {
			ptitulo = new ArrayList<>();
			ptitulo.addAll(posicaoServ.pesquisaTitulo(titulo));
			resultados.add(ptitulo);
		}

		if (local != null) {
			plocal = new ArrayList<>();
			plocal.addAll(posicaoServ.pesquisaLocal(local));
			resultados.add(plocal);
		}

		if (estado != null) {
			pestado = new ArrayList<>();
			pestado.addAll(posicaoServ.pesquisaEstado(estado));
			resultados.add(pestado);
		}

		if (!empresa.equals("")) {
			pempresa = new ArrayList<>();
			pempresa.addAll(posicaoServ.pesquisaEmpresa(empresa));
			resultados.add(pempresa);
		}

		if (area != null) {
			parea = new ArrayList<>();
			parea.addAll(posicaoServ.pesquisaArea(area));
			resultados.add(parea);
		}

		if (resultados.size() == 0) {
			resultadosPesquisa = new HashSet<>();
			resultadosPesquisa.addAll(posicaoServ.listaPosicoes());
		} else {
			for (List<Posicao> list : resultados) {
				if (resultadosPesquisa == null) {
					resultadosPesquisa = new HashSet<>();
					resultadosPesquisa.addAll(list);
				} else {
					resultadosPesquisa.retainAll(list);
				}
			}
		}
	}

	public LocalPosicao[] getLocaisEnum() {
		return LocalPosicao.values();
	}

	public EstadoPosicao[] getEstadosEnum() {
		return EstadoPosicao.values();
	}

	public AreaTecnica[] getAreasTecnicasEnum() {
		return AreaTecnica.values();
	}

	public List<Posicao> getResultadosPesquisa() {

		if (resultadosPesquisa == null) {
			return resultadosList;
		} else {
			resultadosList.clear();
			resultadosList.addAll(resultadosPesquisa);
			return resultadosList;
		}
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void resetData() {
		this.data = null;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public LocalPosicao getLocal() {
		return local;
	}

	public void setLocal(LocalPosicao local) {
		this.local = local;
	}

	public EstadoPosicao getEstado() {
		return estado;
	}

	public void setEstado(EstadoPosicao estado) {
		this.estado = estado;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public AreaTecnica getArea() {
		return area;
	}

	public void setArea(AreaTecnica area) {
		this.area = area;
	}

	public Posicao getPosicaoSelecionada() {
		return posicaoSelecionada;
	}

	public void setPosicaoSelecionada(Posicao posicaoSelecionada) {
		this.posicaoSelecionada = posicaoSelecionada;
		System.out.println("Posição selecionada: " + posicaoSelecionada);
	}

}
