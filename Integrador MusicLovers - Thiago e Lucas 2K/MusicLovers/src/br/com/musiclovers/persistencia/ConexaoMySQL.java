package br.com.musiclovers.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements Conexao {

	private String ip;
	private String login;
	private String nomeBD;
	private String porta;
	private String senha;
	
	private Connection conexao;
	
	public ConexaoMySQL() {
		super();
		ip = DefaultBD.IP;
		login = DefaultBD.LOGIN;
		nomeBD = DefaultBD.NOME_BD;
		porta = DefaultBD.PORTA;
		senha = DefaultBD.SENHA;
	}

	public ConexaoMySQL(String ip, String login, String nomeBD, 
						String porta, String senha) {
		super();
		this.ip = ip;
		this.login = login;
		this.nomeBD = nomeBD;
		this.porta = porta;
		this.senha = senha;
	}

	public Connection getConexao() {
		return conexao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomeBD() {
		return nomeBD;
	}

	public void setNomeBD(String nomeBD) {
		this.nomeBD = nomeBD;
	}

	public String getPorta() {
		return porta;
	}

	public void setPorta(String porta) {
		this.porta = porta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setConexao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void abrirConexao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String endereco = "jdbc:mysql://"+this.ip+":"+this.porta+"/"+this.nomeBD;
			this.conexao = DriverManager.getConnection(endereco, this.login, this.senha);
		} catch (ClassNotFoundException  | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void fecharConexao() {
		try {
			if(this.conexao!=null) {
				this.conexao.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
