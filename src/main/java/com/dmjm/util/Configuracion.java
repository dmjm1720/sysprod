package com.dmjm.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Configuracion {
	// Leer el archivo de propiedades
	// **LOCAL**//

	private String configurationPath = "/home/aigm/AIGM/utilerias/config.properties";

	// **DUCHE**//
	 
	
	//private String configurationPath ="C:\\Sistema_Prod_Htas\\config\\config.properties";

	private final String ALGORITMO = "AES";
	private String keyPwd = "ef08401fe1c6d522fd83c406b49b2072966f8e86aedb996e9cc11aa6c9388716";

	// **CONEXIÓN SQL SERVER**//
	private String conexDBSysProd = "";
	private String conexUser = "";
	private String conexPwd = "";

	// **DATOS CORREO**//
	private String smtpHost = "";
	private String smtpPort = "";
	private String dominioCorreo = "";
	private String msgLiberacionTolva = "";
	private String msgRegistroTolva = "";
	private String msgToContador = "";
	private String msgToGerencia = "";
	private String msgToAdmin = "";
	private String pwdMail = "";
	private String pathCert = "";
	private String urlCert = "";
	private String msgToGerencia2 = "";

	private static final Logger LOGGER = LogManager.getLogger(Configuracion.class.getName());

	public String getConfigurationPath() {
		return configurationPath;
	}

	public void setConfigurationPath(String configurationPath) {
		this.configurationPath = configurationPath;
	}

	public String getConexDBSysProd() {
		return conexDBSysProd;
	}

	public void setConexDBSysProd(String conexDBSysProd) {
		this.conexDBSysProd = conexDBSysProd;
	}

	public String getConexUser() {
		return conexUser;
	}

	public void setConexUser(String conexUser) {
		this.conexUser = conexUser;
	}

	public String getConexPwd() {
		return conexPwd;
	}

	public void setConexPwd(String conexPwd) {
		this.conexPwd = conexPwd;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getDominioCorreo() {
		return dominioCorreo;
	}

	public void setDominioCorreo(String dominioCorreo) {
		this.dominioCorreo = dominioCorreo;
	}

	public String getMsgLiberacionTolva() {
		return msgLiberacionTolva;
	}

	public void setMsgLiberacionTolva(String msgLiberacionTolva) {
		this.msgLiberacionTolva = msgLiberacionTolva;
	}

	public String getMsgRegistroTolva() {
		return msgRegistroTolva;
	}

	public void setMsgRegistroTolva(String msgRegistroTolva) {
		this.msgRegistroTolva = msgRegistroTolva;
	}

	public String getMsgToContador() {
		return msgToContador;
	}

	public void setMsgToContador(String msgToContador) {
		this.msgToContador = msgToContador;
	}

	public String getMsgToGerencia() {
		return msgToGerencia;
	}

	public void setMsgToGerencia(String msgToGerencia) {
		this.msgToGerencia = msgToGerencia;
	}

	public String getMsgToAdmin() {
		return msgToAdmin;
	}

	public void setMsgToAdmin(String msgToAdmin) {
		this.msgToAdmin = msgToAdmin;
	}

	public String getPwdMail() {
		return pwdMail;
	}

	public void setPwdMail(String pwdMail) {
		this.pwdMail = pwdMail;
	}

	public String getPathCert() {
		return pathCert;
	}

	public void setPathCert(String pathCert) {
		this.pathCert = pathCert;
	}

	public String getUrlCert() {
		return urlCert;
	}

	public void setUrlCert(String urlCert) {
		this.urlCert = urlCert;
	}

	public String getMsgToGerencia2() {
		return msgToGerencia2;
	}

	public void setMsgToGerencia2(String msgToGerencia2) {
		this.msgToGerencia2 = msgToGerencia2;
	}

	public void leerConfig() {
		try {
			Properties propiedades = new Properties();
			// Leer el archivo de propiedades
			FileInputStream archivo = new FileInputStream(configurationPath);
			propiedades.load(archivo);

			// Leer las propiedades
			conexDBSysProd = propiedades.getProperty("conexDBSysProd");
			conexUser = propiedades.getProperty("conexUser");
			conexPwd = propiedades.getProperty("conexPwd");

			smtpHost = propiedades.getProperty("smtpHost");
			smtpPort = propiedades.getProperty("smtpPort");
			dominioCorreo = propiedades.getProperty("dominioCorreo");
			msgLiberacionTolva = propiedades.getProperty("msgLiberacionTolva");
			msgRegistroTolva = propiedades.getProperty("msgRegistroTolva");
			msgToContador = propiedades.getProperty("msgToContador");
			msgToGerencia = propiedades.getProperty("msgToGerencia");
			msgToAdmin = propiedades.getProperty("msgToAdmin");
			pwdMail = propiedades.getProperty("pwdMail");
			pathCert = propiedades.getProperty("pathCert");
			urlCert = propiedades.getProperty("urlCert");
			msgToGerencia2 = propiedades.getProperty("msgToGerencia2");

			LOGGER.info("RUTA CONFIG LEÍDA");
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String encriptar(String datos) throws Exception {
		SecretKeySpec clave = new SecretKeySpec(keyPwd.getBytes(), ALGORITMO);
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.ENCRYPT_MODE, clave);
		byte[] encriptado = cipher.doFinal(datos.getBytes());
		return Base64.getEncoder().encodeToString(encriptado);
	}

	public String desencriptar(String datosEncriptados) throws Exception {
		SecretKeySpec clave = new SecretKeySpec(keyPwd.getBytes(), ALGORITMO);
		Cipher cipher = Cipher.getInstance(ALGORITMO);
		cipher.init(Cipher.DECRYPT_MODE, clave);
		byte[] decodificado = Base64.getDecoder().decode(datosEncriptados);
		byte[] desencriptado = cipher.doFinal(decodificado);
		return new String(desencriptado);
	}

	public String cifrado(String dato) {

		String textoDesencriptado = "";
		try {
			String textoOriginal = "sa";
			String textoEncriptado = encriptar(textoOriginal);
			textoDesencriptado = desencriptar(textoEncriptado);

			System.out.println("Texto Original: " + textoOriginal);
			System.out.println("Texto Encriptado: " + textoEncriptado);
			System.out.println("Texto Desencriptado: " + textoDesencriptado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textoDesencriptado;
	}

}
