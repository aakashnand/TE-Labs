package mypackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Symbol_Table {

	char symbol_int[]=new char[40]  ;
    char symbol_float[]=null;
    char symbol_char[]=null;
    BufferedReader br;
	FileOutputStream fos;
	String name;
	//1)create file connections;
	public Symbol_Table(String name) throws IOException{
		File fobj =new File(name);
		File fobj1;
		this.name=name;
		//BufferedReader br;
		//FileOutputStream fos;
		if(name.equals("code1.c"))
		{
			 fobj1 = new File("table1.txt");	
		}
		else
		{
			 fobj1 = new File("table2.txt");
		}
		FileInputStream fis =new FileInputStream (fobj);
		InputStreamReader in =new InputStreamReader(fis);
		br =new BufferedReader(in);	
	    fos = new FileOutputStream(fobj1);
	}
	
	//2)write a method to get each line of code which will return the each string;
	
	
	public void process() throws IOException
	{
       String line;
       char arr[];
       File extFile;
       FileOutputStream extFos;
		while((line=br.readLine())!=null)
		{
			if(line.startsWith("#include"))
			{
				continue;
			}
			
			else if(line.startsWith("void main"))
			{
				continue;
			}
			else if(line.startsWith("{"))
			{
				continue;
			}
			else if(line.startsWith("int"))
			{
				String arr1[],arr2[]=null;
				int memory=2000;
				line=line.replaceFirst("int ","");
				arr1=line.split("=");
				fos.write("********************SYMBOL TABLE***************\n".getBytes());
				String temp ="DataType\t\t\t"+"Variable\t\t"+"Memory Address\n\n";
				fos.write(temp.getBytes());
				fos.write("INTEGER(2 Bytes)\t".getBytes());
				fos.write((arr1[0]+"\t\t\t ").getBytes());
				for(int k=0;k<arr1.length;k++)
				{
					String temp2 = memory+" ";
					fos.write(temp2.getBytes());
					memory=memory+2;
				}
				fos.write("\n".getBytes());
				fos.write("--------------------------------------------------------\n".getBytes());
				continue;
			}
			else if(line.startsWith("float"))
			{
				//fos.write("----------------------------------------------------------------------------------------------------".getBytes());
				fos.write("".getBytes());
				String arr1[],arr2[]=null;
				int memory=3000;
				line=line.replaceFirst("float ","");
				arr1=line.split("=");
				//System.out.println("DataType"+"\t\t"+"Variable"+"\t\t"+"Memory Address");
			    fos.write("FLOAT(4 Bytes)\t\t".getBytes());
				//fos.write("4Bytes\t".getBytes());
				/*for(int i=0;i<arr1.length;i++)
				{
		                 if(arr1[i].contains("="))
		                 {
		                   arr2=arr1[i].split("=");
		                 }
		                 else
		                 {
		                	 //fos.write("\t".getBytes());
		                	 String temp3 ="\t"+arr1[i];
		                	 fos.write(temp3.getBytes());
		                	 fos.write("\n".getBytes());
		                 }
				}*/
				
				fos.write((arr1[0]+"\t\t\t").getBytes());
				for(int k=0;k<arr1.length;k++)
				{
					fos.write((" "+ memory).getBytes());
					memory=memory+4;
				}
				fos.write("\n".getBytes());
				fos.write("--------------------------------------------------------\n".getBytes());
				continue;
				
			}
			
			else if(line.startsWith("char"))
			{

				String arr1[],arr2[]=null;
				int memory=4000;
				line=line.replaceFirst("char ","");
				arr1=line.split("=");
				fos.write("CHARACTER(1 Byte)\t".getBytes());
				fos.write((arr1[0]+"\t\t").getBytes());
				for(int k=0;k<arr1.length;k++)
				{
					fos.write((" "+memory).getBytes());
					memory=memory+1;
				}
				
				continue;
				
			}
			else if(line.startsWith("extern"))
			{
			  if(name.equals("code1.c"))
			  {
				  extFile= new File("extern1.txt");
				  extFos= new FileOutputStream(extFile);
			  }
			  else
			  {
				  extFile= new File("extern2.txt");
				  extFos= new FileOutputStream(extFile);
			  }
			  String arr1[],arr2[]=null;
				int memory=2000;
				line=line.replaceFirst("extern int ","");
				arr1=line.split(" ");
				extFos.write("***************EXTERNAL SYMBOL TABLE***************\n".getBytes());
				String temp ="DataType\t\t\t"+"Variable\t\t"+"Memory Address\n\n";
				extFos.write(temp.getBytes());
				extFos.write("INTEGER(2 Bytes)\t".getBytes());
				extFos.write((arr1[0]+"\t\t\t ").getBytes());
				for(int k=0;k<30;k++)
				{
					String temp2 = memory+" ";
					extFos.write(temp2.getBytes());
					memory=memory+2;
				}
				extFos.write("\n".getBytes());
				
			}
		}
		
		
	}
}
	

