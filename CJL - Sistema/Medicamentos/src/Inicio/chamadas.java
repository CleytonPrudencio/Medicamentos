package Inicio;

import javax.swing.JOptionPane;

import Conexao.conexao;

public class chamadas {
	
	conexao conex = new conexao();
	BeansMed mod = new BeansMed();
	
	
	public BeansMed tabela_completa (BeansMed pegar_tabela) {
		
		conex.Conexao();
		conex.executaSql("SELECT NOME_COMERCIAL, NOME_TECNICO FROM TA_PRODUTO_SAUDE_SITE$");
		try {
			while (conex.rs.next()) {
				pegar_tabela.setConteudo_total(conex.rs.getString("NOME_TECNICO")); //Faz a contagem dos chamados
			}
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao pegar quantidade de chamados \n\n" + e + "\n\nResultado: " + pegar_tabela.getConteudo_total());
		}
		return pegar_tabela;
	}

}
