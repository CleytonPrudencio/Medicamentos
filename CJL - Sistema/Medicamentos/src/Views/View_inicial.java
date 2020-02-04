package Views;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import Conexao.conexao;
import Inicio.BeansMed;
import Inicio.chamadas;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Panel;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import javax.swing.ScrollPaneConstants;
import java.awt.ComponentOrientation;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class View_inicial {
	
	conexao conex = new conexao();
	BeansMed mod = new BeansMed();
	chamadas dao = new chamadas();

	private JFrame frmSistemaDeMedicamentos;
	private JTable table;
	private JTextField text_campodomedicamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_inicial window = new View_inicial();
					window.frmSistemaDeMedicamentos.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public View_inicial() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeMedicamentos = new JFrame();
		frmSistemaDeMedicamentos.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Cleyton Sales\\Documents\\CJL - Sistema\\Medicamentos\\ic_launcher_logo.png"));
		frmSistemaDeMedicamentos.setTitle("Sistema de Medicamentos KA");
		frmSistemaDeMedicamentos.setBounds(100, 100, 1767, 975);
		frmSistemaDeMedicamentos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 1721, 829);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Numero de Registro", "Nome T\u00E9cnico", "Classe de Risco", "Nome Comercial", "CNPJ do Detentor", "Detentor do Registro Cadastrado", "Nome Fabricante", "Pais Fabricante", "Data de Cadastro", "Data de Validade de Registro"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblNomeComercial = new JLabel("Nome Comercial:\r\n");
		lblNomeComercial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		text_campodomedicamento = new JTextField();
		text_campodomedicamento.setColumns(10);
		
		JButton btnPesquisar = new JButton("Pesquisar\r\n");
		btnPesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String pesquisatexto = text_campodomedicamento.getText();
				mod.setPesquisa(pesquisatexto);
				carrega_pesquisa();
				
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Cleyton Sales\\Pictures\\CJL_CLEYTON.png"));
		GroupLayout groupLayout = new GroupLayout(frmSistemaDeMedicamentos.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1741, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(lblNomeComercial)
					.addGap(4)
					.addComponent(text_campodomedicamento, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 854, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(36))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNomeComercial))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(3)
									.addComponent(text_campodomedicamento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnPesquisar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(label)))
					.addGap(38)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE))
		);
		frmSistemaDeMedicamentos.getContentPane().setLayout(groupLayout);
		carregatabela_medicamento();
	}
	
	
	
	
public void carregatabela_medicamento () {
		
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setNumRows(0);
		

		table.getColumnModel().getColumn(0).setPreferredWidth(20);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(3).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);
		table.getColumnModel().getColumn(5).setPreferredWidth(20);
		table.getColumnModel().getColumn(6).setPreferredWidth(20);
		table.getColumnModel().getColumn(7).setPreferredWidth(20);
		table.getColumnModel().getColumn(8).setPreferredWidth(20);
		table.getColumnModel().getColumn(9).setPreferredWidth(20);


			
				try {
				
				conex.Conexao();
				conex.executaSql("SELECT * FROM [Med].[dbo].[TA_PRODUTO_SAUDE_SITE$]");
				
				while (conex.rs.next()) {
					
					modelo.addRow(new Object[] {
							conex.rs.getInt("NUMERO_REGISTRO_CADASTRO"),
							conex.rs.getString("NOME_TECNICO"),
							conex.rs.getString("CLASSE_RISCO"),
							conex.rs.getString("NOME_COMERCIAL"),
							conex.rs.getInt("CNPJ_DETENTOR_REGISTRO_CADASTRO"),
							conex.rs.getString("DETENTOR_REGISTRO_CADASTRO"),
							conex.rs.getString("NOME_FABRICANTE"),
							conex.rs.getString("NOME_PAIS_FABRIC"),
							conex.rs.getDate("DT_PUB_REGISTRO_CADASTRO"),
							conex.rs.getString("VALIDADE_REGISTRO_CADASTRO"),
						
								
					});
					
				} 
				
			} catch (Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro no select\n\n" + e.getMessage());
				// TODO: handle exception
			}
	
		
	}

public void carrega_pesquisa () {
	
	
	DefaultTableModel modelo = (DefaultTableModel) table.getModel();
	modelo.setNumRows(0);
	
	table.getColumnModel().getColumn(0).setPreferredWidth(20);
	table.getColumnModel().getColumn(1).setPreferredWidth(20);
	table.getColumnModel().getColumn(2).setPreferredWidth(20);
	table.getColumnModel().getColumn(3).setPreferredWidth(20);
	table.getColumnModel().getColumn(4).setPreferredWidth(20);
	table.getColumnModel().getColumn(5).setPreferredWidth(20);
	table.getColumnModel().getColumn(6).setPreferredWidth(20);
	table.getColumnModel().getColumn(7).setPreferredWidth(20);
	table.getColumnModel().getColumn(8).setPreferredWidth(20);
	table.getColumnModel().getColumn(9).setPreferredWidth(20);

		
			try {
			
			conex.Conexao();
			conex.executaSql("SELECT * FROM [Med].[dbo].[TA_PRODUTO_SAUDE_SITE$] where NOME_COMERCIAL like '%" + mod.getPesquisa() + "%'");
			
			while (conex.rs.next()) {
				
				modelo.addRow(new Object[] {
						conex.rs.getInt("NUMERO_REGISTRO_CADASTRO"),
						conex.rs.getString("NOME_TECNICO"),
						conex.rs.getString("CLASSE_RISCO"),
						conex.rs.getString("NOME_COMERCIAL"),
						conex.rs.getInt("CNPJ_DETENTOR_REGISTRO_CADASTRO"),
						conex.rs.getString("DETENTOR_REGISTRO_CADASTRO"),
						conex.rs.getString("NOME_FABRICANTE"),
						conex.rs.getString("NOME_PAIS_FABRIC"),
						conex.rs.getDate("DT_PUB_REGISTRO_CADASTRO"),
						conex.rs.getString("VALIDADE_REGISTRO_CADASTRO"),
		
							
				});
				
			} 
			
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro no select\n\n" + e.getMessage());
			// TODO: handle exception
		}

	
}
}
