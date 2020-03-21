package com.augustoangelo.LoginMaven;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Messagebox;

public class bemVindo {
	@Init
	public void bemVindo() {
		Messagebox.show(
				"Boas utilizador, este programa encontra-se em desenvolvimento e tem como objetivo criar um site com ligacao a base de dados em linguagem ZK e Maven",
				"Entrada", null, null, null);
	}
}
