package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import Algorithms.AlgorithmGA;
import Parameters.GlobalParameters;
import Parameters.ParametersGA;
//import sun.security.provider.JavaKeyStore.CaseExactJKS;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class gaUI {

	private JFrame frame;
	private JTextField txtCrossoverRate;
	private JTextField txtMutationRate;
	private JTextField txtMutationStep;
	private JTextField txtTournamentSize;
	private AlgorithmGA algorithmGA;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gaUI window = new gaUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param awesomeAlgorithm 
	 */
	public gaUI(AlgorithmGA awesomeAlgorithm) {
		initialize();
		algorithmGA = awesomeAlgorithm;
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 389);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Crossover Rate");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 83, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mutation Rate");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 24, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mutation Step");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 26, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Elitism");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 25, SpringLayout.SOUTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Selection Method");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 26, SpringLayout.SOUTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_4, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Tournament Size");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 28, SpringLayout.SOUTH, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_5, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_5);
		
		txtCrossoverRate = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtCrossoverRate, 55, SpringLayout.EAST, lblNewLabel);
		springLayout.putConstraint(SpringLayout.SOUTH, txtCrossoverRate, 0, SpringLayout.SOUTH, lblNewLabel);
		frame.getContentPane().add(txtCrossoverRate);
		txtCrossoverRate.setColumns(10);
		
		txtMutationRate = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtMutationRate, 0, SpringLayout.NORTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, txtMutationRate, 0, SpringLayout.EAST, txtCrossoverRate);
		frame.getContentPane().add(txtMutationRate);
		txtMutationRate.setColumns(10);
		
		txtMutationStep = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtMutationStep, 0, SpringLayout.WEST, txtCrossoverRate);
		springLayout.putConstraint(SpringLayout.SOUTH, txtMutationStep, 0, SpringLayout.SOUTH, lblNewLabel_2);
		frame.getContentPane().add(txtMutationStep);
		txtMutationStep.setColumns(10);
		
		txtTournamentSize = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtTournamentSize, 0, SpringLayout.WEST, txtCrossoverRate);
		springLayout.putConstraint(SpringLayout.SOUTH, txtTournamentSize, 0, SpringLayout.SOUTH, lblNewLabel_5);
		frame.getContentPane().add(txtTournamentSize);
		txtTournamentSize.setColumns(10);
		
		final DefaultComboBoxModel selectionMethod = new DefaultComboBoxModel();
		selectionMethod.addElement("Roulette Method");
		selectionMethod.addElement("Ranking");
		selectionMethod.addElement("Tournament");
		JComboBox cBoxSelectionMethod = new JComboBox(selectionMethod);
		springLayout.putConstraint(SpringLayout.WEST, cBoxSelectionMethod, 48, SpringLayout.EAST, lblNewLabel_4);
		springLayout.putConstraint(SpringLayout.SOUTH, cBoxSelectionMethod, -22, SpringLayout.NORTH, txtTournamentSize);
		springLayout.putConstraint(SpringLayout.EAST, cBoxSelectionMethod, 60, SpringLayout.EAST, txtCrossoverRate);
		frame.getContentPane().add(cBoxSelectionMethod);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Elitism");
		springLayout.putConstraint(SpringLayout.NORTH, chckbxNewCheckBox, -4, SpringLayout.NORTH, lblNewLabel_3);
		springLayout.putConstraint(SpringLayout.WEST, chckbxNewCheckBox, 0, SpringLayout.WEST, txtCrossoverRate);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ParametersGA.crossoverRate = Double.parseDouble(txtCrossoverRate.getText());
				ParametersGA.mutationRate = Double.parseDouble(txtMutationRate.getText());
				ParametersGA.mutationStep = Double.parseDouble(txtMutationStep.getText());
				ParametersGA.elitism = chckbxNewCheckBox.isSelected();
				if(cBoxSelectionMethod.getSelectedIndex() != -1){
					String selectionMethod = cBoxSelectionMethod.getSelectedItem().toString();
					switch (selectionMethod) {
					case "Roulette Method":
						ParametersGA.selectionMethod = 1;
						break;
					case "Ranking":
						ParametersGA.selectionMethod = 2;
						break;
					case "Tournament":
						ParametersGA.selectionMethod = 3;
						ParametersGA.tournamentSize = Integer.parseInt(txtTournamentSize.getText());
						break;
					default:
						break;
					}
				}
				algorithmGA.executeGA();
				System.out.println("Best solution found: fitness = " + algorithmGA.bestIndividual.fitness);
				for (int i = 0; i < GlobalParameters.vectorLength; i++) {
					System.out.print(algorithmGA.bestIndividual.structure[i] + "\n");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRun, -2, SpringLayout.NORTH, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, btnRun, -158, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, btnRun, -37, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRun);
	}
}
