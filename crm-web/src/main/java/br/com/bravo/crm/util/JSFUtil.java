package br.com.bravo.crm.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe utilitária que permite aos controllers exibir mensagens na tela ou
 * redirecionar o fluxo de navegação.
 * 
 * @author Bruno Soares Bravo
 *
 */
public class JSFUtil {

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        
    	return FacesContext.getCurrentInstance();
    
    }
    
    public static final void showErrorMessage(Exception e) {
        
    	String errorMessage = JSFUtil.getErrorMessage(e);
        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, "Falha ao realizar operação.");
        FacesContext.getCurrentInstance().addMessage(null, m);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    	
    }
    
    public static final void showErrorMessage(String msgErro) {
    
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msgErro, "Falhar ao realizar operação"));
    	FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    	
    }
    
    public static final void showMessage(String msg) {
        
    	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    
    }
    
    public static final void redirectPage(String pagina) {

    	FacesContext ctx = FacesContext.getCurrentInstance();
    	ctx.getApplication().getNavigationHandler().handleNavigation(ctx, null, pagina + "?faces-redirect=true");
    	
    }
    
    private static final String getErrorMessage(Exception e) {

    	String msgErro = "Falha ao realizar operação.";
        if (e == null) {
            return msgErro;
        }

        Throwable t = e;
        while (t != null) {
            msgErro = t.getLocalizedMessage();
            t = t.getCause();
        }

        return msgErro;
    }

}
