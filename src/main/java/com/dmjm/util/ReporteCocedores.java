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
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReporteCocedores extends Conexion {

	public void getReporte(String ruta, String fecha, int folio) throws SQLException {
		ConectarSysProd();
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fecha", fecha);
		parameters.put("folio", folio);
		parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX")); // México
		try (Connection connection = getCnSysProd()) {
			File file = new File(ruta);
			if (!file.exists()) {
				throw new IOException("El archivo del reporte no se encuentra: " + ruta);
			}

			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");

			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

			// Exportar el reporte como PDF
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

			exporter.exportReport();
			FacesContext.getCurrentInstance().responseComplete();

		} catch (JRException | IOException e) {
			System.err.println("Error al generar el reporte: " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				CerrarSysProd();
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexión: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void getReporteExcel(String ruta, String fecha, int folio, int folio_cocedor) throws SQLException {
	    ConectarSysProd();
	    Map<String, Object> parameters = new HashMap<>();
	    parameters.put("fecha", fecha);
	    parameters.put("folio", folio);
	    parameters.put("folio_cocedor", folio_cocedor);
	    parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX")); // México

	    try (Connection connection = getCnSysProd()) {
	        File file = new File(ruta);
	        if (!file.exists()) {
	            throw new IOException("El archivo del reporte no se encuentra: " + ruta);
	        }

	        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
	                .getResponse();
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=reporte.xlsx");

	        JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

	        // Exportar el reporte como Excel
	        JRXlsxExporter exporter = new JRXlsxExporter();
	        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
	        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

	        exporter.exportReport();
	        FacesContext.getCurrentInstance().responseComplete();

	    } catch (JRException | IOException e) {
	        System.err.println("Error al generar el reporte: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        try {
	            CerrarSysProd();
	        } catch (SQLException e) {
	            System.err.println("Error al cerrar la conexión: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
	}


}
