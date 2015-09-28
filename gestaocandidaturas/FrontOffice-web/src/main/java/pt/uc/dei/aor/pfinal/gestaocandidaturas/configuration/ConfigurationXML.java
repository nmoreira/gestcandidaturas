package pt.uc.dei.aor.pfinal.gestaocandidaturas.configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "configuration")
public class ConfigurationXML {

	@XmlElement(required = true)
	protected String logotipo;

	@XmlElement(required = true)
	protected String nome;

	@XmlElement
	protected String morada;

	@XmlElement
	protected String telefone;

	@XmlElement
	protected String email;

	@XmlElement
	protected String tema;

	@XmlElement
	protected String smtpHostName;

	@XmlElement
	protected String smtpPort;

	@XmlElement
	protected String smtpUsername;

	@XmlElement
	protected String smtpPassword;

	@XmlElement
	protected String smtpFromEmail;

	// static ConfigurationXML conf = new ConfigurationXML();
	//
	// public static void main(String[] args) {
	//
	// conf.setLogotipo("logo");
	// conf.setNome("nome");
	//
	// try {
	// marshalConfiguration(conf);
	// System.out.println("Marshal done");
	// ConfigurationXML lido = unmarshal();
	// System.out.println(lido.getLogotipo());
	// System.out.println(lido.getNome());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (JAXBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public ConfigurationXML() {
	}

	public static void marshalConfiguration(ConfigurationXML conf)
			throws IOException, JAXBException {
		// FileOutputStream fileOutputStream = new FileOutputStream(new
		// File("configuration.xml"));
		FileOutputStream fileOutputStream = null;
		try {
			String path = ConfigurationXML.class
					.getResource("configuration.xml").toURI().getPath();

			String context = FacesContext.getCurrentInstance()
					.getExternalContext().getRealPath("/");
			String resource = path.split("WEB-INF")[1];
			path = context + "/WEB-INF" + resource;

			fileOutputStream = new FileOutputStream(new File(path));

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				fileOutputStream, "UTF-8"));

		Marshaller m = JAXBContext.newInstance(ConfigurationXML.class)
				.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

		m.marshal(conf, writer);
		writer.close();

	}

	public static ConfigurationXML unmarshal() throws JAXBException {
		ConfigurationXML readConfig = null;
		InputStream is = ConfigurationXML.class
				.getResourceAsStream("configuration.xml");
		Unmarshaller um = JAXBContext.newInstance(ConfigurationXML.class)
				.createUnmarshaller();
		readConfig = (ConfigurationXML) um.unmarshal(is);

		return readConfig;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMorada() {
		return morada;
	}

	public void setMorada(String morada) {
		if (morada != null) {
			this.morada = morada;
		} else {
			this.morada = "";
		}
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		if (telefone != null) {
			this.telefone = telefone;
		} else {
			this.telefone = "";
		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null) {
			this.email = email;
		} else {
			this.email = "";
		}
	}

	public String getSmtpHostName() {
		return smtpHostName;
	}

	public void setSmtpHostName(String smtpHostName) {
		this.smtpHostName = smtpHostName;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSmtpFromEmail() {
		return smtpFromEmail;
	}

	public void setSmtpFromEmail(String smtpFromEmail) {
		this.smtpFromEmail = smtpFromEmail;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
}
