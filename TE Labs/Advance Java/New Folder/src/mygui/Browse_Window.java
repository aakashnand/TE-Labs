package mygui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
public class Browse_Window extends JFrame implements ActionListener {

	public Browse_Window()
	{
	    setTitle("Browse Window");
	    setLayout(new FlowLayout());
	   JLabel pls_Label =new JLabel("Please browse the File here");
	   JTextField txt_Area= new JTextField(40);
	   JButton btn_Obj=new JButton("Browse");
	   JButton btn_nxt =new JButton("Next");
	   JFileChooser fc_obj =new JFileChooser();
	   add(pls_Label);
	   add(txt_Area);
	   add(btn_Obj);
	   add(btn_nxt);
	  
	   btn_Obj.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			fc_obj.showOpenDialog(null);
			File fobj =fc_obj.getSelectedFile();
			txt_Area.setText(fobj.getAbsolutePath());
		}
	});
	   txt_Area.addActionListener(this);
	   btn_nxt.addActionListener(new ActionListener() {
		  
		public void actionPerformed(ActionEvent e) {
		
		try {
			File fobj =fc_obj.getSelectedFile();
			Display dobj;
			dobj = new Display(fobj);
			dobj.setVisible(true);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		}
	});
	}
	public static void main(String args[])
	{
		Browse_Window bw_obj=new Browse_Window();
	    bw_obj.setSize(500, 400);
	    bw_obj.setVisible(true);
	    bw_obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
