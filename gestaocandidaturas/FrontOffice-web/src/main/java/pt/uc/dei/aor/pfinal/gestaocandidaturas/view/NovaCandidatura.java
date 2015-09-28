package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.PosicaoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Posicao;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.mail.CommonsMail;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.StringNormalizer;

@Named
@ViewScoped
public class NovaCandidatura implements Serializable {

	private static final long serialVersionUID = 1L;

	static Logger logger = LoggerFactory.getLogger(NovaCandidatura.class);

	private String cartamotivacao;
	private String fonte;
	private Candidato candidato;
	private Posicao posicao;
	private List<String> cartas;

	private String pathCartas;

	private List<String> fontes = new ArrayList<>();

	@Inject
	private CandidatoService candidatoServ;

	@Inject
	private PosicaoService posicaoServ;

	@Inject
	private CurrentSession current;

	@Inject
	private CandidaturaService candidaturaServ;

	@Inject
	private CommonsMail mail;

	public NovaCandidatura() {
	}

	@PostConstruct
	private void init() {
		carregaFontes();
		this.candidato = (Candidato) current.getCurrentUser();
		carregaCartasMotivacao();

		Map<String, String> params = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap();
		long posid = Long.parseLong(params.get("posid"));

		if (posid == 0) {
			this.posicao = null;
		} else {
			this.posicao = posicaoServ.getPosicaoById(posid);
		}
	}

	private void carregaFontes() {
		fontes.add("Expresso");
		fontes.add("Linkdin");
		fontes.add("Diário de Notícias");
		fontes.add("Jornal de Notícias");
		fontes.add("Centro de Emprego");
		fontes.add("Facebook");
		fontes.add("Amigos");
	}

	public List<String> sugerirfonte(String consulta) {
		List<String> fontesugeridas = new ArrayList<>();
		for (String fonte : fontes) {
			if (fonte.toLowerCase().startsWith(consulta.toLowerCase()))
				fontesugeridas.add(fonte);
		}
		return fontesugeridas;
	}

	public void carregaCartasMotivacao() {
		List<String> cartas = candidatoServ.getCartas(candidato.getId());
		if (cartas.size() > 0) {
			pathCartas = cartas.get(0).substring(0,
					cartas.get(0).lastIndexOf("/") + 1);
		}
		List<String> paths = new ArrayList<>();
		for (String path : cartas) {
			String file = path.substring(path.lastIndexOf("/") + 1);
			paths.add(file);
		}
		this.cartas = paths;
	}

	public void candidatar() {
		if (cartamotivacao == null) {
			DisplayMessages
					.addWarnMessage("Por favor selecione ou carregue uma carta de motivação para esta posição");
		} else {
			Candidatura candidatura = new Candidatura(cartamotivacao, fonte,
					posicao, candidato);
			if (candidatura.getPosicao() == null) {
				if (candidaturaServ.criaCandidaturaEspontanea(candidatura)) {
					DisplayMessages
							.addInfoMessage("Candidatura espontânea efetuada com sucesso!");
					mail.enviaEmailSimples(
							"Candidatura espontânea submetida",
							"Obrigado por submeter uma candidatura espontânea\n"
									+ "Será notificado assim que a sua candidatura for analisada e atribuida a uma posição específica",
							candidato);
				} else {
					DisplayMessages
							.addErrorMessage("Falha ao submeter a candidatura espontânea!");
				}
			} else {
				if (candidaturaServ.createNewCandidatura(candidatura)) {
					DisplayMessages
							.addInfoMessage("Candidatura efetuada com sucesso!");
					mail.enviaEmailSimples(
							"Nova candidatura submetida à posição "
									+ candidatura.getPosicao().getTitulo(),
							"O candidato "
									+ candidato.getNome()
									+ " "
									+ candidato.getApelido()
									+ " submeteu uma nova candidatura à posição "
									+ candidatura.getPosicao().getTitulo()
									+ " da qual você é gestor", candidatura
									.getPosicao().getResponsavel());
					mail.enviaEmailSimples(
							"Nova candidatura",
							"Obrigado pela sua candidatura à posição de "
									+ posicao.getTitulo()
									+ ".\n"
									+ "Acompanhe a evolução da sua candidatura na opção Minhas candidaturas, depois de fazer login no site!",
							candidato);
				} else {
					DisplayMessages
							.addErrorMessage("Falha ao submeter a candidatura!");
				}
			}
		}
	}

	public void fileUpload(FileUploadEvent event) {

		String path = FacesContext.getCurrentInstance().getExternalContext()
				.getRealPath("/");
		path = path + "/data/cartas/" + this.candidato.getLogin();
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String fileName = StringNormalizer.unaccent(event.getFile()
				.getFileName());

		file = new File(path + "/" + fileName);
		if (file.exists()) {
			DisplayMessages.addWarnMessage("Ficheiro já existente!");
		} else {
			try {
				InputStream is = event.getFile().getInputstream();
				OutputStream out = new FileOutputStream(file);
				byte buf[] = new byte[1024];
				int len;
				while ((len = is.read(buf)) > 0)
					out.write(buf, 0, len);
				is.close();
				out.close();
				adicionaCarta("/data/cartas/" + this.candidato.getLogin() + "/"
						+ fileName);
				DisplayMessages
						.addInfoMessage("Ficheiro carregado com sucesso");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				DisplayMessages.addErrorMessage("Erro ao carregar o ficheiro");
				logger.error("Erro ao carregar carta de motivação: "
						+ file.getAbsolutePath() + " : FileNotFoundException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				DisplayMessages.addErrorMessage("Erro ao carregar o ficheiro");
				logger.error("Erro ao carregar carta de motivação: "
						+ file.getAbsolutePath() + " : IOException");
			}
		}
	}

	private void adicionaCarta(String local) {
		candidatoServ.adicionarCartaMotivacao(candidato.getId(), local);
		carregaCartasMotivacao();
	}

	public String getCartamotivacao() {
		return cartamotivacao;
	}

	public void setCartamotivacao(String cartamotivacao) {
		this.cartamotivacao = pathCartas + cartamotivacao;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Posicao getPosicao() {
		if (this.posicao == null) {
			Posicao p = new Posicao();
			p.setTitulo("Candidatura espontânea");
			return p;
		} else {
			return posicao;
		}
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public List<String> getCartas() {
		return cartas;
	}

	public void setCartas(List<String> cartas) {
		this.cartas = cartas;
	}

	public String getPathCartas() {
		return pathCartas;
	}

}
