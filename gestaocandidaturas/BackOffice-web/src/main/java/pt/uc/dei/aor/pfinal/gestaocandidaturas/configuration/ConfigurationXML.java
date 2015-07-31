package pt.uc.dei.aor.pfinal.gestaocandidaturas.configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;

import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Named
@ApplicationScoped
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

			System.out.println(path);

			fileOutputStream = new FileOutputStream(new File(path));
			System.out.println("DONEp1");
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
		System.out.println("DONE p2");
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

}
