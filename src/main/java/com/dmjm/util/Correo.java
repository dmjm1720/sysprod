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

import com.dmjm.bean.EntradasBean;

public class Correo extends Configuracion {

	private static final Logger LOGGER = LogManager.getLogger(Correo.class.getName());

	public void enviarNotificacion(int tolva, String noTicket, String proveedor, String factura, String tipoPiel, int bg, int cc) {
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
		String mensaje = null;
		if (bg == 1) {
			mensaje = getMsgLiberacionTolva();
		}
		if (cc == 1) {
			mensaje = getMsgRegistroTolva();
		}

		try {
			texto.setContent(
					"<html><head><title></title></head><body><table width='678' height='315' border='0' bordercolor='#0000FF' bgcolor='#FFFFFF'><tr><td height='10' colspan='3' bordercolor='#FFFFFF'></td></tr><tr><td colspan='3' bordercolor='#FFFFFF'>"
					+ "<p align='left' style='font-family:arial; font-size:17px'><font color='#086A87'>"
							+ mensaje + "</font><br><br><b><font color='#000000'>" + "Tolva: " + tolva
							+ "| No. Factura: " + factura + " | No. Ticket: " + noTicket + " | Tipo de piel: " + tipoPiel +" | Proveedor: " + proveedor
							+ " </font></b><br></tr><tr><td width='425' bordercolor='#FFFFFF'>"
							+ "<p align='left' style='font-family:arial; font-size:17px'><br><font color='#17202a'>Sistema de Captura de Producci&oacute;n</font><font color='#17202a'><br>Coloidales Duch&eacute;, S.A. de C.V.</font><br></td><td width='122' bordercolor='#FFFFFF'></td>"
							+ "<td width='222' rowspan='2' bordercolor='#FFFFFF'></td></tr><tr><td colspan='2' bordercolor='#17202a'><br><br><p align='center' style='font-family:arial; font-size:15px'>"
							+ "<font color='#086A87'><br> Mensaje autom&aacute;tico enviado desde el Sistema de Producci&oacute;n, favor de no responder.</font></p></td></tr></table></body></html>",
					"text/html");

			MimeMultipart multiParte = new MimeMultipart();

			multiParte.addBodyPart(texto);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress(getDominioCorreo()));//PROPS

			if (bg == 1) {
				//message.addRecipients(Message.RecipientType.TO, getMsgToContador());//PROPS
				//message.addRecipients(Message.RecipientType.TO, getMsgToAdmin());//PROPS

			}
			if (cc == 1) {
				message.addRecipients(Message.RecipientType.TO, getMsgToGerencia());//PROPS
				message.addRecipients(Message.RecipientType.TO, getMsgToGerencia2());//PROPS
				message.addRecipients(Message.RecipientType.TO, getMsgToAdmin());//PROPS

			}

			message.addRecipients(Message.RecipientType.BCC, getDominioCorreo());//PROPS

			message.setSubject("Notificación de liberación de Tolva número: " + tolva);

			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect(getDominioCorreo(), getPwdMail());//PROPS
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			LOGGER.info("SE HA ENVIADO EL CORREO CON INFO DE LA TOLVA: " + tolva);
		} catch (MessagingException e) {
			LOGGER.info("ERROR AL ENVIAR EL CORREO: " + e);
			throw new RuntimeException(e);
		}
	}

}
