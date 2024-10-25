package com.dmjm.util;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class FiltroUrl implements PhaseListener {

    private static final long serialVersionUID = 1L;

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String currentPage = facesContext.getViewRoot().getViewId();
        boolean isPageLogin = currentPage != null && currentPage.contains("/index.xhtml");

        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        if (session == null) {

            if (!isPageLogin) {
                NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
                nHandler.handleNavigation(facesContext, null, "/index.xhtml");
            }
            return;
        }

        Object nombre = session.getAttribute("nombre");
        if (!isPageLogin && nombre == null) {
            NavigationHandler nHandler = facesContext.getApplication().getNavigationHandler();
            nHandler.handleNavigation(facesContext, null, "/index.xhtml");
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
