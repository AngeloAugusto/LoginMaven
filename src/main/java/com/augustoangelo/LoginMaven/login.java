package com.augustoangelo.LoginMaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class login extends SelectorComposer<Component> {
	ligadb ligacao = new ligadb();
	PreparedStatement pstm = null;
	Connection con = null;
	ResultSet rs = null;

	public String nome, password;

	@Wire
	Textbox pass;
	@Wire
	Textbox user;
	@Wire
	Button login;

	@Init
	public void ini() {
		Messagebox.show("Insira o seu username e a sua password.");
	}

	@Listen("onClick = #login")
	public void login() {
		nome = user.getText();
		password = pass.getText();
		con = ligacao.obterLigacao();

		try {
			pstm = con.prepareStatement("SELECT password FROM utilizadores where nome=?");
			pstm.setString(1, nome);
			rs = pstm.executeQuery();
			if (rs.next()) {
				String passwordDB = rs.getString(1);
				if (password.equals(passwordDB)) {
					Messagebox.show("Login com sucesso");
					Executions.sendRedirect("paginaInicial.zul");
				} else {
					Messagebox.show("Password errada");
				}
			} else {
				Messagebox.show("Nome de utilizador errado");
			}
		} catch (SQLException ex) {
			Messagebox.show("Preencha todos os campos");
		}
		ligacao.fecharLigacao(con);
		//Messagebox.show("user: " + nome + " pass: " + password);
	}

}
