package pt.uc.dei.aor.pfinal.gestaocandidaturas.mail;

import java.net.MalformedURLException;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.JAXBException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.uc.dei.aor.pfinal.gestaocandidaturas.configuration.ConfigurationXML;

@ApplicationScoped
public class CommonsMail {

	private ConfigurationXML conf;

	static Logger logger = LoggerFactory.getLogger(CommonsMail.class);

	public CommonsMail() {
		try {
			conf = ConfigurationXML.unmarshal();
			logger.info("Configurações de email lidas com sucesso");
		} catch (JAXBException e) {
			logger.error("Erro ao ler o ficheiro de configurações");
		}
	}

	/**
	 * envia email simples(somente texto)
	 * 
	 * @throws EmailException
	 */
	public void enviaEmailSimples(String assunto, String mensagem,
			String destinatario) {

		try {
			Email email = new SimpleEmail();
			email.setHostName(conf.getSmtpHostName());
			email.setSmtpPort(Integer.parseInt(conf.getSmtpPort()));
			email.setAuthenticator(new DefaultAuthenticator(conf
					.getSmtpUsername(), conf.getSmtpPassword()));
			email.setSSLOnConnect(true);

			email.setFrom(conf.getSmtpFromEmail());
			email.setSubject(assunto);
			email.setMsg(mensagem);
			email.addTo(destinatario);
			email.send();
			logger.info("Email enviado com sucesso para " + destinatario);
		} catch (NumberFormatException | EmailException e) {
			logger.error("Erro ao enviar email para " + destinatario);
		}

	}

	/**
	 * envia email com arquivo anexo
	 * 
	 * @throws EmailException
	 */
	public void enviaEmailComAnexo() {

		// cria o anexo 1.
		EmailAttachment anexo1 = new EmailAttachment();
		anexo1.setPath("teste/teste.txt"); // caminho do arquivo
											// (RAIZ_PROJETO/teste/teste.txt)
		anexo1.setDisposition(EmailAttachment.ATTACHMENT);
		anexo1.setDescription("Exemplo de arquivo anexo");
		anexo1.setName("teste.txt");

		// cria o anexo 2.
		EmailAttachment anexo2 = new EmailAttachment();
		anexo2.setPath("teste/teste2.jsp"); // caminho do arquivo
											// (RAIZ_PROJETO/teste/teste2.jsp)
		anexo2.setDisposition(EmailAttachment.ATTACHMENT);
		anexo2.setDescription("Exemplo de arquivo anexo");
		anexo2.setName("teste2.jsp");

		// configura o email
		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio
													// do
													// e-mail
			email.addTo("teste@gmail.com", "Guilherme"); // destinat�rio
			email.setFrom("teste@gmail.com", "Eu"); // remetente
			email.setSubject("Teste -> Email com anexos"); // assunto do e-mail
			email.setMsg("Teste de Email utilizando commons-email"); // conteudo
																		// do
																		// e-mail
			email.setAuthentication("teste", "xxxxx");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);

			// adiciona arquivo(s) anexo(s)
			email.attach(anexo1);
			email.attach(anexo2);
			// envia o email
			email.send();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Envia email no formato HTML
	 * 
	 * @throws EmailException
	 * @throws MalformedURLException
	 */
	public void enviaEmailFormatoHtml() {

		HtmlEmail email = new HtmlEmail();

		try {
			// adiciona uma imagem ao corpo da mensagem e retorna seu id
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");

			// configura a mensagem para o formato HTML
			email.setHtmlMsg("<html>Logo do Apache - <img ></html>");

			// configure uma mensagem alternativa caso o servidor n�o suporte
			// HTML
			email.setTextMsg("Seu servidor de e-mail n�o suporta mensagem HTML");

			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio
													// do
													// e-mail
			email.addTo("teste@gmail.com", "Guilherme"); // destinat�rio
			email.setFrom("teste@gmail.com", "Eu"); // remetente
			email.setSubject("Teste -> Html Email"); // assunto do e-mail
			email.setMsg("Teste de Email HTML utilizando commons-email"); // conteudo
																			// do
																			// e-mail
			email.setAuthentication("teste", "xxxxx");
			email.setSmtpPort(465);
			email.setSSLOnConnect(true);
			email.setStartTLSEnabled(true);
			// envia email
			email.send();
		} catch (MalformedURLException | EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
