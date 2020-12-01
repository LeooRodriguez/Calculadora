package Grafica;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Logica.Calculadora;
import Logica.InvalidOperationException;
import Logica.InvalidPathException;

import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class CalculadoraSimple extends JFrame {

	private JPanel contentPane;
	private JTextField txtNmero1;
	private JTextField txtNmero2;
	private JTextField numero1;
	private JTextField numero2;
	private JTextField txtResultado;
	private JTextField resultado;
	private Calculadora calculadora;
	private ArrayList<String>nombres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculadoraSimple frame = new CalculadoraSimple();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CalculadoraSimple() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("Img\\IconoCalcu.png"));
		setTitle("Calculadora");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 336, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel_resultado = new JPanel();
		panel_resultado.setBounds(0, 0, 324, 264);
		contentPane.add(panel_resultado);
		panel_resultado.setLayout(null);

		txtNmero1 = new JTextField();
		txtNmero1.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		txtNmero1.setText("N\u00FAmero 1:");
		txtNmero1.setEditable(false);
		txtNmero1.setBounds(173, 30, 69, 28);
		panel_resultado.add(txtNmero1);
		txtNmero1.setColumns(10);

		txtNmero2 = new JTextField();
		txtNmero2.setEditable(false);
		txtNmero2.setText("N\u00FAmero 2:");
		txtNmero2.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		txtNmero2.setBounds(173, 69, 69, 28);
		panel_resultado.add(txtNmero2);
		txtNmero2.setColumns(10);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(0, 0, 159, 20);
		panel_resultado.add(comboBox);



		JButton b_actualizar = new JButton("Actualizar");
		b_actualizar.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		b_actualizar.setBounds(0, 200, 324, 28);
		panel_resultado.add(b_actualizar);

		numero1 = new JTextField();
		numero1.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		numero1.setBounds(252, 30, 62, 27);
		panel_resultado.add(numero1);
		numero1.setColumns(10);

		numero2 = new JTextField();
		numero2.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		numero2.setBounds(252, 68, 62, 28);
		panel_resultado.add(numero2);
		numero2.setColumns(10);

		txtResultado = new JTextField();
		txtResultado.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		txtResultado.setText("Resultado:");
		txtResultado.setEditable(false);
		txtResultado.setBounds(10, 161, 141, 28);
		panel_resultado.add(txtResultado);
		txtResultado.setColumns(10);

		resultado = new JTextField();
		resultado.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		resultado.setEditable(false);
		resultado.setBounds(161, 162, 153, 27);
		panel_resultado.add(resultado);
		resultado.setColumns(10);

		JButton b_calcular = new JButton("Calcular");
		b_calcular.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		b_calcular.setBounds(10, 122, 304, 28);
		panel_resultado.add(b_calcular);
		
		JButton b_info = new JButton("Informaci\u00F3n");
		b_info.setFont(new Font("Microsoft PhagsPa", Font.BOLD, 12));
		b_info.setBounds(0, 225, 324, 28);
		panel_resultado.add(b_info);
		
		JLabel l_imagen = new JLabel("");
		l_imagen.setIcon(new ImageIcon("Img\\Fondo_Juego.jpg"));
		l_imagen.setBounds(0, 0, 324, 264);
		panel_resultado.add(l_imagen);

		calculadora= new Calculadora();
		nombres=calculadora.getNames();
		try {
			calculadora.getPlugins();
		} catch (InvalidPathException e2) {
			JOptionPane.showMessageDialog(null,"ERROR: Ruta inválida","Información", 2);
			System.exit(0);
			e2.printStackTrace();
		}

		if(nombres.size()==0) {
			JOptionPane.showMessageDialog(null,"ERROR: No se detectan plugins","Información", 2);
		}
		for(int i=0;i<nombres.size();i++) {
			comboBox.addItem(nombres.get(i));
		}
	


		b_calcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int num1;
				int num2;
				double toRet;
				String plugin;
				
				plugin= (String) comboBox.getItemAt(comboBox.getSelectedIndex());
				
				try {
				num1= Integer.parseInt(numero1.getText());
				num2= Integer.parseInt(numero2.getText());
				toRet=calculadora.runPlugins(plugin, num1, num2);
				resultado.setText(""+toRet);
				} catch (NumberFormatException excepcion) {
					JOptionPane.showMessageDialog(contentPane,"ERROR: Números no válidos","Información", 2);
				} catch (InvalidOperationException e1) {
					JOptionPane.showMessageDialog(null,"ERROR: Verifique los números ingresados","Información", 1);
					e1.printStackTrace();
				}
			}
		});
		
		b_actualizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {			    
				comboBox.removeAllItems();
				calculadora.limpiarNombres(); //Preguntar si es correcto esto a Martín.
				nombres=calculadora.getNames();
				try {
					calculadora.getPlugins();
				} catch (InvalidPathException e1) {
					JOptionPane.showMessageDialog(null,"ERROR: Ruta inválida","Información", 2);
					System.exit(0);
					e1.printStackTrace();
				}

				for(int i=0;i<nombres.size();i++) {
					comboBox.addItem(nombres.get(i));
				}
			}
		});
		
		b_info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String[] opcion = new String[] {"Cancelar","GitHub"};
				int x=JOptionPane.showOptionDialog(null, "Version 1.0"+"   "+"Desarrollado por Leonardo Rodríguez", "Información adicional",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, opcion, opcion[0]);
				switch(x){
				case 1:
					if(Desktop.isDesktopSupported())
					{
						try {
							Desktop.getDesktop().browse(new URI("https://github.com/LeooRodriguez"));
						} catch (Exception ex) {}
					}
					break;
				default:
					break;
				}
			}
		});
		
}
	
}
