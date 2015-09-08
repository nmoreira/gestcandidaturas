package pt.uc.dei.aor.pfinal.gestaocandidaturas.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidatoService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.CandidaturaService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.ejb.UtilizadorService;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidato;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.entidades.Candidatura;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;

@Named
@ViewScoped
public class EditarPerfil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private CurrentSession current;

	@Inject
	private UtilizadorService userServ;

	@Inject
	private CandidatoService candServ;

	@Inject
	private CandidaturaService candidaturaServ;

	private Candidato candidato;

	private String login;
	private String email;

	@PostConstruct
	public void init() {
		setCandidato((Candidato) current.getCurrentUser());
		this.login = this.candidato.getLogin();
		this.email = this.candidato.getEmail();

	}

	public void validaLogin() {
		if (!this.login.equals(candidato.getLogin())) {
			if (userServ.existeLogin(login) == true)
				DisplayMessages.addErrorMessage("O login " + login
						+ " já está em uso! Por favor escolha outro");
			else {
				DisplayMessages
						.addInfoMessage("Login " + login + " disponível");
			}
		}
	}

	public void validaEmail() {
		if (!this.email.equals(candidato.getEmail())) {
			if (userServ.existeEmail(email) == true) {
				DisplayMessages.addErrorMessage("O email " + email
						+ " já está registado!");
			}
		}
	}

	public void fileUpload(FileUploadEvent event) {
		if (this.candidato.getNome() == null
				|| this.candidato.getApelido() == null) {
			DisplayMessages
					.addWarnMessage("Por favor indique primeiro o seu Nome e Apelido");
		} else {
			String path = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");

			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
			String name = fmt.format(new Date())
					+ event.getFile()
							.getFileName()
							.substring(
									event.getFile().getFileName()
											.lastIndexOf('.'));
			String local = "/data/cv/" + this.candidato.getNome() + "_"
					+ this.candidato.getApelido() + name;
			File file = new File(path + local);

			File oldfile = new File(path + candidato.getCv());
			oldfile.delete();

			try {
				InputStream is = event.getFile().getInputstream();
				OutputStream out = new FileOutputStream(file);
				byte buf[] = new byte[1024];
				int len;
				while ((len = is.read(buf)) > 0)
					out.write(buf, 0, len);
				is.close();
				out.close();
				this.candidato.setCv(local);
				DisplayMessages
						.addInfoMessage("Ficheiro carregado com sucesso");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void atualizarCandidato() {
		if (!this.login.equals(candidato.getLogin())) {
			String path = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");
			File folder = new File(path + "/data/cartas/"
					+ candidato.getLogin());

			if (folder.exists()) {
				// atualizar pastas
				File newFolder = new File(path + "/data/cartas/" + login);
				newFolder.mkdirs();
				File[] files = folder.listFiles();
				for (File file : files) {
					file.renameTo(new File(path + "/data/cartas/" + login + "/"
							+ file.getName()));
				}
				folder.delete();

				// atualizar cartas de motivação
				List<String> cartas = candServ.getCartas(candidato.getId());
				List<String> cartasUpdated = new ArrayList<>();
				for (String filename : cartas) {
					cartasUpdated
							.add(filename.replace("/" + candidato.getLogin()
									+ "/", "/" + login + "/"));
				}
				candidato.setCartas(cartasUpdated);

				// atualizar candidaturas
				List<Candidatura> candidaturas = candidaturaServ
						.getCandidaturasByCandidatoId(candidato.getId());
				for (Candidatura candidatura : candidaturas) {
					String carta = candidatura.getCartaMotivacao()
							.replace("/" + candidato.getLogin() + "/",
									"/" + login + "/");
					candidatura.setCartaMotivacao(carta);
					candidaturaServ.atualizarCandidatura(candidatura);
				}

			} else {
			}

			candidato.setLogin(login);
		}
		if (!this.email.equals(candidato.getEmail())) {
			candidato.setEmail(email);
		}

		if (candServ.atualizarCandidato(candidato)) {
			DisplayMessages.addInfoMessage("Perfil atualizado com sucesso!");
		} else {
			DisplayMessages.addWarnMessage("Falha ao atualizar o perfil");
		}
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
