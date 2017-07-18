package mypackage;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
public class Get_File extends JFrame implements ActionListener {

	private JButton browse_button,next_button;
	private JTextField file_textfield;
	private JLabel lbl;
	
	public Get_File()
	{
		super("File Selector");
		browse_button=new JButton("Browse");
		next_button=new JButton("Next");
		lbl=new JLabel("Please Enter the File path or Browse the File");
		file_textfield=new JTextField(10);
		setLayout(new FlowLayout());
		add(lbl);
		add(file_textfield);
		add(browse_button);
		add(next_button);
		browse_button.addActionListener(this);
		next_button.addActionListener(this);
		file_textfield.addActionListener(this);
		
	}
	public static void main(String args[])
	{
		Get_File gf=new Get_File();
		gf.setSize(200, 200);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gf.setVisible(true);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
