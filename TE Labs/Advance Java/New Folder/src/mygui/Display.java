package mygui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
public class Display extends JFrame implements ActionListener{
   Employee empl_obj[] =new Employee[9];
   int i=0;
   int currentIndexBeingDisplayed=0;
   public Display(File obj) throws IOException
   {
	   setTitle("Display Window");
	   setLayout(new FlowLayout(FlowLayout.LEFT));
	   JButton btn_NEXT =new JButton("Next Record");
	   JButton btn_PRE =new JButton("Previous Record");
	   JLabel lbl_Name =new JLabel();
	   JLabel lbl_Age = new JLabel();
	   JLabel lbl_Salary=new JLabel();
	   add(lbl_Name); 
	   add(lbl_Age);
	   add(lbl_Salary);
	   add(btn_PRE);
	   add(btn_NEXT);
	   setSize(400, 400);
	   FileInputStream fis =new FileInputStream(obj);
	   InputStreamReader in =new InputStreamReader(fis);
	   BufferedReader br =new BufferedReader(in);
	   
	   String line;
	   Employee temp;
	   i=0;
	   while((line=br.readLine())!=null)
			   {
		         temp=new Employee();
		         String lines[]=line.split("-");
		         
		         temp.name=lines[0];
		         temp.Age=lines[1];
		         temp.Salary=lines[2];
		         empl_obj[i] = temp;
		         i++;
			   }
	   br.close();
	   lbl_Name.setText(empl_obj[0].name);
	   lbl_Age.setText(empl_obj[0].Age);
	   lbl_Salary.setText(empl_obj[0].Salary);
	  
	   btn_NEXT.addActionListener(new ActionListener() {
		   
		@Override
		public void actionPerformed(ActionEvent e) {
			if(currentIndexBeingDisplayed==i-1)
			   {
				   currentIndexBeingDisplayed=0;
			   }
			   else
			   {
				   currentIndexBeingDisplayed++;
			   }
			
			lbl_Name.setText(empl_obj[currentIndexBeingDisplayed].name);
			   lbl_Age.setText(empl_obj[currentIndexBeingDisplayed].Age);
			   lbl_Salary.setText(empl_obj[currentIndexBeingDisplayed].Salary);
		}
	});
	   /*btn_PRE.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			lbl_Name.setText(empl_obj[j].name);
			   lbl_Age.setText(empl_obj[j].Age);
			   lbl_Salary.setText(empl_obj[j].Salary);
			   if(j==0)
			   {
				   j=i;
			   }
			   else
			   {
				   j--;
			   }
		}
	});*/
   }
	
   public void actionPerformed(ActionEvent e) {

		
	}
   
}
