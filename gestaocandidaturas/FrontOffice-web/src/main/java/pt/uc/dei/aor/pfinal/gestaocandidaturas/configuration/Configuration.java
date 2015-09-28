package pt.uc.dei.aor.pfinal.gestaocandidaturas.configuration;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.utilities.DisplayMessages;
import pt.uc.dei.aor.pfinal.gestaocandidaturas.view.Theme;

@Named
@ApplicationScoped
public class Configuration {

	private ConfigurationXML conf;

	static Logger logger = LoggerFactory.getLogger(Configuration.class);

	@PostConstruct
	private void init() {
		try {
			setConf(ConfigurationXML.unmarshal());
		} catch (JAXBException e) {
			logger.error("Erro ao ler o ficheiro de configurações" + e);
		}
	}

	public void saveConfiguration() {
		try {
			ConfigurationXML.marshalConfiguration(conf);
			DisplayMessages
					.addInfoMessage("Configurações guardadas com sucesso!");
			logger.info("Ficheiro de configurações gravado com sucesso");
		} catch (IOException | JAXBException e) {
			DisplayMessages.addErrorMessage("Erro ao guardar as configurações");
			logger.error("Erro ao guardar o ficheiro de configurações" + e);
		}
	}

	public ConfigurationXML getConf() {
		return conf;
	}

	public void setConf(ConfigurationXML conf) {
		this.conf = conf;
	}

	public Theme[] getTemas() {
		return Theme.values();
	}

}
