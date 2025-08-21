package com.dmjm.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CorreoPrecios extends Configuracion {

	private static final Logger LOGGER = LogManager.getLogger(CorreoPrecios.class.getName());

	public void enviarNotificacion(String materia, String proveedor, int tolva) {
		// LEER LAS PROPIEDADES
		leerConfig();

		Properties props = new Properties();
		props.put("mail.smtp.host", getSmtpHost()); // PROPS
		props.put("mail.smtp.port", getSmtpPort());// PROPS
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		BodyPart texto = new MimeBodyPart();
		String mensaje = "Se ha actualizado precio en Tolva";

		try {
			texto.setContent(
					"<html><head><title></title></head><body><table width='678' height='315' border='0' bordercolor='#0000FF' bgcolor='#FFFFFF'><tr>"
							+ "<td height='10' colspan='3' bordercolor='#FFFFFF'></td></tr>"
							+ "<tr><td colspan='3' bordercolor='#FFFFFF'><p align='left' style='font-family:arial; font-size:17px'><font color='#086A87'>"
							+ mensaje + "</font><br><br><b><font color='#000000'>" + "Tolva: </b>" + tolva
							+ "<br><b> Proveedor: </b>" + proveedor + " <br><b> Materia y Precio: </b>" + materia
							+ " <br>"
							+ " </font></b><br></tr><tr><td width='425' bordercolor='#FFFFFF'><p align='left' style='font-family:arial; font-size:17px'>"
							+ "<br><font color='#17202a'>Sistema de Captura de Producci&oacute;n </font><br><font color='#17202a'>Coloidales Duch&eacute;, S.A. de C.V.</font>"
							+ "<br></td><td width='122' bordercolor='#FFFFFF'></td><td width='222' rowspan='2' bordercolor='#FFFFFF'></td></tr><tr>"
							+ "<td colspan='2' bordercolor='#17202a'><br><br><p align='center' style='font-family:arial; font-size:15px'><font color='#086A87'>"
							+ "<br> Mensaje autom&aacute;tico enviado desde el Sistema de Producci&oacute;n, favor de no responder.</font></p></td></tr></table></body></html>",
					"text/html");

			MimeMultipart multiParte = new MimeMultipart();

			multiParte.addBodyPart(texto);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(getDominioCorreo()));// PROPS

			message.addRecipients(Message.RecipientType.TO, "ghernandez@duche.com");// PROPS
			message.addRecipients(Message.RecipientType.BCC, "ggutierrez@duche.com");// PROPS
			message.addRecipients(Message.RecipientType.TO, "llbudd@duche.com");// PROPS
			message.addRecipients(Message.RecipientType.TO, "wbudd@duche.com");// PROPS
			message.addRecipients(Message.RecipientType.TO, "gelatin@duche.com");// PROPS
			message.addRecipients(Message.RecipientType.BCC, getDominioCorreo());// PROPS

			message.setSubject("Actualización de precios en la Tolva: " + tolva);

			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect(getDominioCorreo(), getPwdMail());// PROPS
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			LOGGER.warn("SE HA ENVIADO EL CORREO DE ACTUALIZACIÓN DE PRECIOS EN LA TOLVA: " + tolva);
		} catch (MessagingException e) {
			LOGGER.info("ERROR AL ENVIAR EL CORREO: " + e);
			throw new RuntimeException(e);
		}
	}

}
