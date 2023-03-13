package com.metar_planner.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}