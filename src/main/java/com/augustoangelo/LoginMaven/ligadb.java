package com.augustoangelo.LoginMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

public class ligadb {
	
	
	public Connection obterLigacao() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true",
					"root", "");
		} catch (ClassNotFoundException ex) {
			Messagebox.show("Erro na class: " + ex);
		} catch (SQLException ex) {
			Messagebox.show("Erro no SQL: " + ex);
		}
		return con;
	}
	public void fecharLigacao(Connection con) {
        try {
            con.close();
        } catch (SQLException ex) {
        	Messagebox.show("Erro no SQL: " + ex);
        }
    }

	

	@Command
	@NotifyChange("cmd")
	public void cmd() {
		Messagebox.show("cmd");
	}

	@Command
	@NotifyChange("bomdia")
	public void bomdia() {
		Messagebox.show("Bom dia caro user");
	}
}
