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

public class CorreoRangos extends Configuracion {

	private static final Logger LOGGER = LogManager.getLogger(CorreoRangos.class.getName());

	public void enviarNotificacion(int tolva, String noTicket, String proveedor, String factura, String tipoPiel,
			String banderaHumedad, double humedad, String banderaCalcios, double calcios, double porcentajeHumedad, double porcentajeCalcios) {
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
		String mediciones = null;
		String textMeciones = null;
		String porcentajes = null;

		switch (banderaCalcios + "-" + banderaHumedad) {
		case "FUERA DE RANGO-FUERA DE RANGO" -> {
			mediciones = "Humedad: " + humedad + " y Calcios: " + calcios;
			porcentajes = "Porcentaje Humedad: " + porcentajeHumedad + " y Porcentaje Calcios: " + porcentajeCalcios;
			textMeciones = "Calcios y Humedad fuera de rangos en la tolva: ";
			mensaje="El sistema ha detectado Calcios y Humedad fuera de rangos";
		}
		case "FUERA DE RANGO-OK" -> {
			mediciones = "Calcios: " + calcios;
			porcentajes = "Porcentaje Calcios: " + porcentajeCalcios;
			textMeciones = "Calcios fuera de rango en la tolva: ";
			mensaje="El sistema ha detectado Calcios fuera de rangos";
		}
		case "OK-FUERA DE RANGO" -> {
			mediciones = "Humedad: " + humedad;
			porcentajes = "Porcentaje Humedad: " + porcentajeHumedad;
			textMeciones = "Humedad fuera de rango en la tolva: ";
			mensaje="El sistema ha detectado Humedad fuera de rangos";
		}
		case "null-FUERA DE RANGO" -> {
			mediciones = "Humedad: " + humedad;
			porcentajes = "Porcentaje Humedad: " + porcentajeHumedad;
			textMeciones = "Humedad fuera de rango en la tolva: ";
			mensaje="El sistema ha detectado Humedad fuera de rangos";
		}
		case "FUERA DE RANGO-null" -> {
			mediciones = "Calcios: " + calcios;
			porcentajes = "Porcentaje Calcios: " + porcentajeCalcios;
			textMeciones = "Calcios fuera de rango en la tolva: ";
			mensaje="El sistema ha detectado Calcios fuera de rangos";
		}
		default -> {
			mediciones = "Valores dentro de los rangos esperados";
			textMeciones = "Todo está en orden en la tolva.";
		}
		}

		try {
			texto.setContent(
					"<html><head><title></title></head><body><table width='678' height='315' border='0' bordercolor='#0000FF' bgcolor='#FFFFFF'><tr>"
					+ "<td height='10' colspan='3' bordercolor='#FFFFFF'></td></tr>"
					+ "<tr><td colspan='3' bordercolor='#FFFFFF'><p align='left' style='font-family:arial; font-size:17px'><font color='#086A87'>"
							+ mensaje + "</font><br><br><b><font color='#000000'>" + "Tolva: </b>" + tolva
							+ "<br><b> Proveedor: </b>" + proveedor + " <br><b> No. Factura: </b>" + factura+ " <br> <b>No. Ticket: </b>"
							+ noTicket + " <br><b> Tipo de piel: </b>"  + tipoPiel + " <br><font color='#E60013'><b>" + mediciones + "<br>" + porcentajes
							+ "</font>"
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

			message.addRecipients(Message.RecipientType.TO, getMsgToAlerta1());// PROPS
			message.addRecipients(Message.RecipientType.TO, getMsgToAlerta2());// PROPS
			message.addRecipients(Message.RecipientType.TO, getMsgToAlerta3());// PROPS
			message.addRecipients(Message.RecipientType.TO, getMsgToAlerta4());// PROPS
			message.addRecipients(Message.RecipientType.TO, getMsgToAlerta5());// PROPS
			message.addRecipients(Message.RecipientType.TO, getMsgToAdmin());// PROPS
			message.addRecipients(Message.RecipientType.BCC, getDominioCorreo());// PROPS

			message.setSubject(textMeciones + tolva);

			message.setContent(multiParte);

			Transport t = session.getTransport("smtp");
			t.connect(getDominioCorreo(), getPwdMail());// PROPS
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			LOGGER.warn("SE HA ENVIADO EL CORREO DE ALERTAS DE RANGOS CON INFO DE LA TOLVA: " + tolva);
		} catch (MessagingException e) {
			LOGGER.info("ERROR AL ENVIAR EL CORREO: " + e);
			throw new RuntimeException(e);
		}
	}

}
