package com.dmjm.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;

import com.dmjm.bean.LoginBean;

public class FiltroUrl implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static final Logger LOGGER = LogManager.getLogger(FiltroUrl.class);
	// Constantes para rutas
	private static final String LOGIN_PAGE = "/index.xhtml";


	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		String currentPage = facesContext.getViewRoot().getViewId();

		boolean isLoginPage = currentPage != null && currentPage.contains(LOGIN_PAGE);

		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

		if (session == null) {
			if (!isLoginPage) {
				redirectToPage(facesContext, LOGIN_PAGE);
			}
			return;
		}

		// Verificar autenticación
		Object nombre = session.getAttribute("nombre");
		Object rol = session.getAttribute("rol"); // Supongamos que se guarda el rol en sesión

		if (nombre == null) {
			if (!isLoginPage) {
				redirectToPage(facesContext, LOGIN_PAGE);
			}
		} else {
			// Verificar roles según la página
			if (!isLoginPage) {
				if (!isAuthorized(rol, currentPage)) {
					redirectToPage(facesContext, LOGIN_PAGE); // Página predeterminada
				}
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// No se requiere implementación específica en esta fase
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	/**
	 * Verifica si el usuario tiene acceso a la página según su rol.
	 */
	private boolean isAuthorized(Object rol, String page) {
		if (rol == null)
			return false;

		// Reglas
		if (rol.equals("Administrador") && page.contains("/admin/")) {
			return true;
		} else if (rol.equals("Coordinador") && page.contains("/coordinador/")) {
			return true;
		} else if (rol.equals("Control de calidad") && page.contains("/calidad/")) {
			return true;
		} else if (rol.equals("Gerencia") && page.contains("/gerencia/")) {
			return true;
		} else if (rol.equals("Contador") && page.contains("/contador/")) {
			return true;
		} else if (rol.equals("Presidencia") && page.contains("/presidencia/")) {
			return true;
		} else if (rol.equals("Otros") && page.contains("/pend/")) {
			return true;
		}

		return false;
	}

	/**
	 * Redirige a una página específica.
	 */
	private void redirectToPage(FacesContext facesContext, String page) {
		// Verificar autenticación
		//HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		//Object nombre = session.getAttribute("nombre");

		NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
		navHandler.handleNavigation(facesContext, null, page + "?faces-redirect=true");
		facesContext.renderResponse();
		LOGGER.warn("VIOLACIÓN DE ACCESO");
	}
}
