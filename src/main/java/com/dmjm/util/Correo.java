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

public class Correo {

	public void enviarNotificacion(int tolva, String proveedor, String factura, String tipoPiel, int bg, int cc) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.hostinger.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		BodyPart texto = new MimeBodyPart();
		String mensaje = null;
		if (bg == 1) {
			mensaje = "Se ha liberado una nueva Tolva: ";
		}
		if (cc == 1) {
			mensaje = "Se ha registrado una nueva Tolva para su liberaci&oacute;n: ";
		}

		try {
			texto.setContent(
					"<html><head><title></title></head><body><table width='678' height='315' border='0' bordercolor='#0000FF' bgcolor='#FFFFFF'><tr><td height='10' colspan='3' bordercolor='#FFFFFF'></td></tr><tr><td colspan='3' bordercolor='#FFFFFF'><p align='left' style='font-family:calibri; font-size:17px'><font color='#086A87'>"
							+ mensaje + "</font><br><br><b><font color='#000000'>" + "Tolva: " + tolva
							+ "| No. Factura: " + factura + " | Tipo de piel: " + tipoPiel
							+ " </font></b><br></tr><tr><td width='425' bordercolor='#FFFFFF'><p align='left' style='font-family:calibri; font-size:17px'><br><font color='#17202a'>Sistema de Captura de Producci&oacute;n | </font><font color='#E60013'>Coloidales Duch&eacute;, S.A. de C.V.</font><br></td><td width='122' bordercolor='#FFFFFF'></td><td width='222' rowspan='2' bordercolor='#FFFFFF'></td></tr><tr><td colspan='2' bordercolor='#17202a'><br><br><p align='center' style='font-family:calibri; font-size:15px'><font color='#086A87'><br> Mensaje autom&aacute;tico enviado desde el Sistema de Producci&oacute;n, favor de no responder.</font></p></td></tr></table></body></html>",
					"text/html");

			MimeMultipart multiParte = new MimeMultipart();

			multiParte.addBodyPart(texto);

			MimeMessage message = new MimeMessage(session);

			message.setFrom(new InternetAddress("notificaciones@noficaciones.dmjm-sistemas.com"));

			if (bg == 1) {
				message.addRecipients(Message.RecipientType.TO, "ghernandez@duche.com");
				message.addRecipients(Message.RecipientType.TO, "ggutierrez@duche.com");
			}
			if (cc == 1) {
				message.addRecipients(Message.RecipientType.TO, "jnolasco@duche.com");
				message.addRecipients(Message.RecipientType.TO, "ggutierrez@duche.com");
			}
			
			
			message.addRecipients(Message.RecipientType.BCC, "notificaciones@noficaciones.dmjm-sistemas.com");

			message.setSubject("Notificación de liberación de Tolva número: " + tolva);

			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect("notificaciones@noficaciones.dmjm-sistemas.com", "y#=W>[keO3Z");
			t.sendMessage(message, message.getAllRecipients());
			t.close();
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}