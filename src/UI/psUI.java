package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;

import Algorithms.AlgorithmPS;
import Parameters.GlobalParameters;
import Parameters.ParametersPS;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class psUI {

	private JFrame frame;
	private JTextField txtW;
	private JTextField txtC1;
	private JTextField txtC2;
	private AlgorithmPS algorithmPS;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					psUI window = new psUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param briliantAlgorithm 
	 */
	public psUI(AlgorithmPS briliantAlgorithm) {
		algorithmPS = briliantAlgorithm;		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("Inertia constant (W)");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 62, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognitive Factor (C1)");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 34, SpringLayout.SOUTH, lblNewLabel);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Social Factor (C2)");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 35, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 0, SpringLayout.EAST, lblNewLabel);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtW = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtW, -3, SpringLayout.NORTH, lblNewLabel);
		frame.getContentPane().add(txtW);
		txtW.setColumns(10);
		
		txtC1 = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, txtW, 0, SpringLayout.EAST, txtC1);
		springLayout.putConstraint(SpringLayout.NORTH, txtC1, -3, SpringLayout.NORTH, lblNewLabel_1);
		frame.getContentPane().add(txtC1);
		txtC1.setColumns(10);
		
		txtC2 = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtC2, 29, SpringLayout.SOUTH, txtC1);
		springLayout.putConstraint(SpringLayout.WEST, txtC2, 104, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, txtC1, 0, SpringLayout.WEST, txtC2);
		frame.getContentPane().add(txtC2);
		txtC2.setColumns(10);
		
		JButton btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ParametersPS.C1 = Double.parseDouble(txtC1.getText());
				ParametersPS.C2 = Double.parseDouble(txtC2.getText());
				ParametersPS.W = Double.parseDouble(txtW.getText());
				algorithmPS.executePS();
				System.out.println("Best solution found: fitness = " + algorithmPS.globalBest.fitness);
				for (int i = 0; i < GlobalParameters.vectorLength; i++) {
					System.out.print(algorithmPS.globalBest.structure[i] + "\n");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRun, 87, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, btnRun, -133, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, btnRun, 0, SpringLayout.SOUTH, lblNewLabel_1);
		springLayout.putConstraint(SpringLayout.EAST, btnRun, -35, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(btnRun);
	}

}
