package com.dmjm.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ReporteEsterilizadores {

	public void getReporte(String ruta, String fecha, Integer folio) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			session.doWork(connection -> {
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("fecha", fecha);
				parameters.put("folio", folio);
				parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX"));

				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

				try {
					
					File file = new File(ruta);
					if (!file.exists()) {
						throw new IOException("El archivo del reporte no se encuentra: " + ruta);
					}
					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ruta);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", "inline; filename=reporte.pdf");

					ServletOutputStream out = response.getOutputStream();
					JasperExportManager.exportReportToPdfStream(jasperPrint, out);
					out.flush();
					out.close();

					facesContext.responseComplete();
				} catch (JRException | IOException e) {
					e.printStackTrace();
					throw new RuntimeException("Error generando reporte PDF", e);
				}
			});
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}
	
	
	public void getReporteExcel(String ruta, String fecha) throws SQLException {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();

			session.doWork(connection -> {
				Map<String, Object> parameters = new HashMap<>();
				parameters.put("fecha", fecha);
				parameters.put(JRParameter.REPORT_LOCALE, new Locale("es", "MX"));

				try {
					File file = new File(ruta);
					if (!file.exists()) {
						throw new IOException("El archivo del reporte no se encuentra: " + ruta);
					}

					JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);

					FacesContext facesContext = FacesContext.getCurrentInstance();
					HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext()
							.getResponse();

					response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					response.setHeader("Content-Disposition", "attachment; filename=reporte.xlsx");

					JRXlsxExporter exporter = new JRXlsxExporter();
					exporter.setExporterInput(new SimpleExporterInput(jasperPrint));

					ServletOutputStream out = response.getOutputStream();
					exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

					exporter.exportReport();
					out.flush();
					out.close();

					facesContext.responseComplete();
				} catch (JRException | IOException e) {
					e.printStackTrace();
					throw new RuntimeException("Error al generar el reporte Excel", e);
				}
			});

		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

}
