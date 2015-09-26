package pt.uc.dei.aor.pfinal.gestaocandidaturas.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ApplicationScoped
public class Configuration {

	// public static void main(String[] args) {
	// Configuration conf = new Configuration();
	// }
	//
	// public Configuration() {
	// init();
	// }

	private ConfigurationXML conf;

	static Logger logger = LoggerFactory.getLogger(Configuration.class);

	@PostConstruct
	private void init() {
		try {
			setConf(ConfigurationXML.unmarshal());
		} catch (JAXBException e) {
			logger.error("Erro ao ler o ficheiro de configurações");
		}
	}

	public void saveConfiguration() {
		this.conf.setSmtpHostName("smtp.gmail.com");
		this.conf.setSmtpPort("465");
		this.conf.setSmtpUsername("nunofrmoreira@gmail.com");
		this.conf.setSmtpPassword("ArcaMlam26");
		this.conf.setSmtpFromEmail("nunofrmoreira@gmail.com");

		try {
			ConfigurationXML.marshalConfiguration(conf);
			logger.info("Ficheiro de configurações gravado com sucesso");
		} catch (IOException | JAXBException e) {
			logger.error("Erro ao guardar o ficheiro de configurações");
		}
	}

	public ConfigurationXML getConf() {
		return conf;
	}

	public void setConf(ConfigurationXML conf) {
		this.conf = conf;
	}

}
