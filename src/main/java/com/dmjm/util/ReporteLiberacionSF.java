package com.dmjm.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReporteLiberacionSF extends Conexion {

	public void getReporte(String ruta, String IDENTRADA, String IDDOC) {
		this.ConectarSysProd();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("IDENTRADA", IDENTRADA);
		parameter.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX")); // México
		try (Connection connection = getCnSysProd()) { // Manejo automático de recursos
			File file = new File(ruta);

			if (!file.exists()) {
				throw new IOException("El archivo del reporte no existe en la ruta especificada: " + ruta);
			}

			// Configuración de la respuesta HTTP
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");

			// Cargar y llenar el reporte
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, connection);

			// Exportar a PDF
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
			exporter.exportReport();

			FacesContext.getCurrentInstance().responseComplete(); // Completar la respuesta HTTP

		} catch (JRException | SQLException | IOException e) {
			System.err.println("Error al generar el reporte: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (getCnSysProd() != null) {
				try {
					CerrarSysProd();
				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

}
