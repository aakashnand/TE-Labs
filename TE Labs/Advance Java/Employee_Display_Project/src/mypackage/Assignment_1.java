package mypackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.DropMode;

public class Assignment_1 {

	private JFrame frame;
	private JButton btnBrowse;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment_1 window = new Assignment_1();
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
	public Assignment_1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel file_Label = new JLabel("Please Select the File");
		file_Label.setVerticalAlignment(SwingConstants.BOTTOM);
		file_Label.setHorizontalAlignment(SwingConstants.LEFT);
		frame.getContentPane().add(file_Label);
		JFileChooser obj =new JFileChooser();
		btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				obj.showOpenDialog(null);
				File f1= obj.getSelectedFile();
				textField.setText(f1.getAbsolutePath());
				
			    try {
					FileInputStream fis =new FileInputStream(f1);
					int count;
					while((count=fis.read())!=-1){
						
					}
				} catch ( IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		});
		
		textField = new JTextField();
		frame.getContentPane().add(textField);
		textField.setColumns(30);
		frame.getContentPane().add(btnBrowse);
		
		btnNewButton = new JButton("Next");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				Display frame =new Display();
				frame.show(true);
				Assignment_1.this.frame.show(false);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		frame.getContentPane().add(btnNewButton);
	}

}
