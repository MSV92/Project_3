package UI;
import Algorithms.*;
import Parameters.GlobalParameters;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.SpringLayout;

import com.sun.java.swing.plaf.windows.resources.windows;
import com.sun.javafx.scene.traversal.Algorithm;

import jdk.nashorn.internal.objects.Global;
import jdk.nashorn.internal.runtime.GlobalConstants;
//import sun.security.provider.JavaKeyStore.CaseExactJKS;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frontPageGUI {

	private JFrame frame;
	private JTextField txtPopSize;
	private JTextField txtStringLength;
	private JTextField txtNoGen;
	private JTextField txtM;
	private static String algo = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frontPageGUI window = new frontPageGUI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frontPageGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 621, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblStringLength = new JLabel("String length");
		springLayout.putConstraint(SpringLayout.NORTH, lblStringLength, 110, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblStringLength, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblStringLength, 132, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblStringLength, 93, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblStringLength);
		
		JLabel lblAlgorithms = new JLabel("Algorithms");
		springLayout.putConstraint(SpringLayout.NORTH, lblAlgorithms, 25, SpringLayout.SOUTH, lblStringLength);
		springLayout.putConstraint(SpringLayout.WEST, lblAlgorithms, 0, SpringLayout.WEST, lblStringLength);
		frame.getContentPane().add(lblAlgorithms);
		
		JLabel lblPopulationSize = new JLabel("Population Size");
		springLayout.putConstraint(SpringLayout.NORTH, lblPopulationSize, 44, SpringLayout.SOUTH, lblAlgorithms);
		springLayout.putConstraint(SpringLayout.WEST, lblPopulationSize, 0, SpringLayout.WEST, lblStringLength);
		frame.getContentPane().add(lblPopulationSize);
		
		JLabel lblNewLabel = new JLabel("Number of generations");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 56, SpringLayout.SOUTH, lblPopulationSize);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, lblStringLength);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblM = new JLabel("m");
		springLayout.putConstraint(SpringLayout.WEST, lblM, 0, SpringLayout.WEST, lblStringLength);
		springLayout.putConstraint(SpringLayout.SOUTH, lblM, -42, SpringLayout.SOUTH, frame.getContentPane());
		frame.getContentPane().add(lblM);
		
		txtPopSize = new JTextField();
		frame.getContentPane().add(txtPopSize);
		txtPopSize.setColumns(10);
		
		txtStringLength = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtStringLength, 39, SpringLayout.EAST, lblStringLength);
		springLayout.putConstraint(SpringLayout.WEST, txtPopSize, -86, SpringLayout.EAST, txtStringLength);
		springLayout.putConstraint(SpringLayout.EAST, txtPopSize, 0, SpringLayout.EAST, txtStringLength);
		springLayout.putConstraint(SpringLayout.NORTH, txtStringLength, 1, SpringLayout.NORTH, lblStringLength);
		frame.getContentPane().add(txtStringLength);
		txtStringLength.setColumns(10);
		
		txtNoGen = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtPopSize, -56, SpringLayout.NORTH, txtNoGen);
		springLayout.putConstraint(SpringLayout.WEST, txtNoGen, -86, SpringLayout.EAST, txtPopSize);
		springLayout.putConstraint(SpringLayout.EAST, txtNoGen, 0, SpringLayout.EAST, txtPopSize);
		frame.getContentPane().add(txtNoGen);
		txtNoGen.setColumns(10);
		
		txtM = new JTextField();
		springLayout.putConstraint(SpringLayout.SOUTH, txtNoGen, -32, SpringLayout.NORTH, txtM);
		springLayout.putConstraint(SpringLayout.WEST, txtM, 114, SpringLayout.EAST, lblM);
		springLayout.putConstraint(SpringLayout.SOUTH, txtM, 0, SpringLayout.SOUTH, lblM);
		frame.getContentPane().add(txtM);
		txtM.setColumns(10);
		
		
		
		
		final DefaultComboBoxModel algorithms = new DefaultComboBoxModel();
		algorithms.addElement("Genetic Algorithms");
		algorithms.addElement("Particle Swarm");
		JComboBox cBoxAlgorithms = new JComboBox(algorithms);
		springLayout.putConstraint(SpringLayout.WEST, cBoxAlgorithms, 0, SpringLayout.WEST, txtPopSize);
		springLayout.putConstraint(SpringLayout.SOUTH, cBoxAlgorithms, 0, SpringLayout.SOUTH, lblAlgorithms);
		springLayout.putConstraint(SpringLayout.EAST, cBoxAlgorithms, 156, SpringLayout.WEST, txtPopSize);
		frame.getContentPane().add(cBoxAlgorithms);
		
		JButton btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GlobalParameters.vectorLength = Integer.parseInt(txtStringLength.getText());
				GlobalParameters.populationSize = Integer.parseInt(txtPopSize.getText());
				GlobalParameters.numGenerations = Integer.parseInt(txtNoGen.getText());
				GlobalParameters.m = Integer.parseInt(txtM.getText());
				if(cBoxAlgorithms.getSelectedIndex() != -1){
					algo = cBoxAlgorithms.getItemAt(cBoxAlgorithms.getSelectedIndex()).toString();
					switch (algo) {
					case "Genetic Algorithms":
						AlgorithmGA awesomeAlgorithm = new AlgorithmGA();
						gaUI genAlg = new gaUI(awesomeAlgorithm);
						frame.setVisible(false);								

						
						break;
					case "Particle Swarm":
						AlgorithmPS briliantAlgorithm = new AlgorithmPS();
						psUI particleSwAlg = new psUI(briliantAlgorithm);
						frame.setVisible(false);		

						
						break;

					default:
						break;
					}
				}

				
			}
			
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 183, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnNewButton, -130, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, lblPopulationSize);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -47, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnNewButton);
	}
}
