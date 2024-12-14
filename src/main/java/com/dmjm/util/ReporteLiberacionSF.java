package com.dmjm.util;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


public class ReporteLiberacionSF extends Conexion {

	@SuppressWarnings("deprecation")
	public void getReporte(String ruta, String IDENTRADA, String IDDOC) {
		this.ConectarSysProd();
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("IDENTRADA", IDENTRADA);
		try {
			File file = new File(ruta);
			// JRProperties.setProperty("net.sf.jasperreports.default.font.name", "Arial");
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.setDateHeader("Expires", 0);
			httpServletResponse.setContentType("application/PDF");
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(file.getPath());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, getCnSysProd());
			@SuppressWarnings("rawtypes")
			JRExporter jrExporter = null;
			jrExporter = new JRPdfExporter();
			jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, httpServletResponse.getOutputStream());
			if (jrExporter != null) {
				try {
					jrExporter.exportReport();
				} catch (JRException e) {
					System.err.println(e.getMessage());
				}
			}

		} catch (JRException | IOException e) {
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
