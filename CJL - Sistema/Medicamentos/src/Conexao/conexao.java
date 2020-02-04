package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class conexao {
	
	public Statement stm;
	public ResultSet rs;
	private String driver = "jdbc:sqlserver://";
	private String caminho = "jdbc:sqlserver://cleyton.database.windows.net:1433;database=Med";
	private String usuario = "cleyton@cleyton";
	private String senha = "25242598@Rc";
	public Connection con;
	
	
	//Vamos criar nosso método de conexão
	public void Conexao () {
		try {
			
			System.setProperty("jdbc.Drivers", driver);
			con=DriverManager.getConnection(caminho, usuario, senha);
			//JOptionPane.showMessageDialog(null, "Conectou! ");
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			JOptionPane.showMessageDialog(null, "Erro ao Conectar" + e.getMessage());
			
		}
	}
	
	public void executaSql (String sql) {
		try {
			stm = con.createStatement(rs.TYPE_SCROLL_INSENSITIVE, rs.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao executa SQL \n" + ex.getMessage());
		}
	}

	public void desconecta () {
		try {
			con.close();
			//JOptionPane.showMessageDialog(null, "BD Desconectado com sucesso! ");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao fechar conexaoo com BD \n" + e.getMessage());
		}
	}
	

}
