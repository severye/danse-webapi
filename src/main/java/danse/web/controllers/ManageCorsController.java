package danse.web.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import danse.web.models.ApplicationModel;

@Controller
public class ManageCorsController {
	@Autowired
	private ApplicationModel application;

	// envoi des options au client
	private void sendOptions(HttpServletResponse response) {
		if (application.isCORSneeded()) {
			// on fixe le header CORS
			response.addHeader("Access-Control-Allow-Origin", "*");
			// on autorise certains headers
			response.addHeader("Access-Control-Allow-Headers", "accept, authorization, content-type");
			// on autorise le POST
			response.addHeader("Access-Control-Allow-Methods", "POST");
		}
	}

	// liste des couleurs
	@RequestMapping(value = "/getAllColours", method = RequestMethod.OPTIONS)
	public void getAllColours(HttpServletResponse response) {
		
	}
}
