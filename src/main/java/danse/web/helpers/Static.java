package danse.web.helpers;

import java.util.ArrayList;
import java.util.List;

public class Static {
	public Static() {
	}

	// liste des messages d'erreur d'une exception
	public static List<String> getErreursForException(Exception exception) {
		// on récupère la liste des messages d'erreur de l'exception
		Throwable cause = exception;
		List<String> erreurs = new ArrayList<String>();
		while (cause != null) {
			erreurs.add(cause.getMessage());
			cause = cause.getCause();
		}
		return erreurs;
	}
}
