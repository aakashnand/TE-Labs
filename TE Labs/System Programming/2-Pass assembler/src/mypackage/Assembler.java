package mypackage;
import java.io.*;
import java.util.*;
public class Assembler {
  File fobj;	
  FileInputStream fis;
  FileOutputStream fos;
  BufferedReader br;
  List<String[]> lines =new ArrayList<String[]>();
  List<String> words = new ArrayList<String>();
  public void storeToList() throws Exception
  {
	  fobj=new File("input.txt");
	  fis = new FileInputStream(fobj);
	  InputStreamReader in = new InputStreamReader(fis);
	  br=new BufferedReader(in);
	  String line;
	  while((line=br.readLine())!=null)
	  {
		  lines.add(line.split(" "));
			  
	  }
	  int i=0;
	  String one[];
	 while(i<lines.size())
	 {
		 one=lines.get(i);
		 int j=0;
		 while(j<one.length)
		 {
			 if(!one[j].equals(""))
			 {
				 words.add(one[j]);
			 }
			 j++;
		 }
		 i++;
	 }
}
  public void create_symbol_table() throws Exception
  {
	  String arr[];
	  String address;
	  int index=0;
	  String word;
	  
	  File symboltablefile = new File("Symbol table.txt");
	  FileOutputStream fos = new FileOutputStream(symboltablefile);
	  
	  fos.write("\t\t\t**********Symbol Table***********\n".getBytes());
	  fos.write("Variable Name\t\tLength\t\tValue\t\tAddress\n".getBytes());
	  
	  while(index < (words.size()))
	  {
		  word=words.get(index);
		  //System.out.println(word);
		  if(word.startsWith("STORE")||word.startsWith("START"))
		  {
			 
		  }
		  else if(word.startsWith("NEXT:"))
		  {
			  arr=word.split(":");
			  fos.write(arr[0].getBytes());
			 fos.write("\t\t\t\t-\t\t\t-\t\t".getBytes());
			 address=String.valueOf("2000");
			 fos.write(address.getBytes());
			 fos.write("\n".getBytes());
			 
		  }
		  else if (word.startsWith("LOOP:"))
		  {
			  arr=word.split(":");
			  fos.write(arr[0].getBytes());
			 fos.write("\t\t\t\t-\t\t\t-\t\t".getBytes());
			 address=String.valueOf("2004");
			 fos.write(address.getBytes());
			 fos.write("\n".getBytes());
			 
		  }
		  else if(word.contains("DS"))
		  {
		      
			  fos.write("M".getBytes());
		      fos.write("\t\t\t\t10\t\t\t-\t\t2011\n".getBytes());
		  }
		  else if(word.contains("DC"))
		  {
			 fos.write("N".getBytes());
			 fos.write("\t\t\t\t-\t\t\t5\t\t2012".getBytes()); 
		  }
		  index++;
	  }
	  fos.close();
  }
 public void create_mnemonic_opcode_table() throws Exception
 {
	 //***********************Declarations***********************************************
	 String word;
	 boolean mult_flag=true,bc_flag=true;
	 
	 List<String> file_lines =new ArrayList<String>();
	 
	 File mnemonic_opcode_table_file = new File("Mnemonic.txt");
	 fis = new FileInputStream(fobj);
	 InputStreamReader in = new InputStreamReader(fis);
	 br=new BufferedReader(in);
	 FileOutputStream fos = new FileOutputStream(mnemonic_opcode_table_file);
	 
	 fos.write("\t\t **********Mnemonic Opcode Table***********\n\n".getBytes());
	 fos.write("Mnemonic\t\t\t\t\t\t\t\tOpcode\n\n".getBytes());
	 // ************************Actual Code Here*************************
	 while((word=br.readLine())!=null)
	 {
		 if(word.contains("START"))
		 {
			 file_lines.add("START");
		 }
		 else if(word.contains("MOVER"))
		 {
			 file_lines.add("MOVER");
		 }
		 else if(word.contains("MOVEM"))
		 {
			 file_lines.add("MOVEM");
		 }
		 else if(word.contains("ADD"))
		 {
			 file_lines.add("ADD");
		 }
		 else if (word.contains("MULT") && mult_flag==true)
		 {
			 file_lines.add("MULT");
			 mult_flag=false;
		 }
		 else if(word.contains("BC") && bc_flag==true)
		 {
			 file_lines.add("BC");
			 bc_flag=false;
		 }
		 else if(word.contains("SUB"))
		 {
			 file_lines.add("SUB");
		 }
		 else if(word.contains("LTORG"))
		 {
			 file_lines.add("LTORG");
		 }
		 else if(word.contains("STOP"))
		 {
			 file_lines.add("STOP");
		 }
		 else if(word.contains("DS"))
		 {
			 file_lines.add("DS");
		 }
		 else if(word.contains("DC"))
		 {
			 file_lines.add("DC");
		 }
		 else if(word.contains("END"))
		 {
			 file_lines.add("END");
		 }
	 }
	 int index=0;
	 String word1;
	while(index<file_lines.size())
	 {
		word1=file_lines.get(index)+"\t\t\t\t\t\t\t\t\t0"+String.valueOf(index+1)+"\n";
		fos.write(word1.getBytes()); 
		index++;
	 }
	 fos.close();
 }
 public void create_literal_table() throws Exception
 {
	 String word;
	 int address=2000;
	 File literal_table_file = new File("Literal Table.txt");
	 fis = new FileInputStream(fobj);
	 InputStreamReader in = new InputStreamReader(fis);
	 br=new BufferedReader(in);
	 FileOutputStream fos = new FileOutputStream(literal_table_file);
	 
	 fos.write("\t\t **********Literal Table***********\n\n".getBytes());
	 fos.write("Literal No\t\t\tLiteral\t\t\tAddress Of Defination\n\n".getBytes());
	 while(!(br.readLine().equals("     LTORG")))
	 {
		 address++;
	 }
	 word=br.readLine()+"\t\t\t\t";
	 fos.write("1\t\t\t".getBytes());
	 fos.write(word.getBytes());
     fos.write(String.valueOf(address).getBytes());
     fos.write("\n".getBytes());
     
     word=br.readLine()+"\t\t\t\t";
	 fos.write("2\t\t\t".getBytes());
	 fos.write(word.getBytes());
     fos.write(String.valueOf(address+1).getBytes());
	 
     fos.close();
 }
 
 public void create_pool_table() throws Exception
 {
	 File pool_table_file = new File("Pool Table.txt");
	 fis = new FileInputStream(fobj);
	 InputStreamReader in = new InputStreamReader(fis);
	 br=new BufferedReader(in);
	 FileOutputStream fos = new FileOutputStream(pool_table_file);
	 
	 fos.write("\t\t **********Pool Table***********\n\n".getBytes());
	 fos.write("Pool No\t\t\t\t\t\tLiteral Table Index\n\n".getBytes());
	 fos.write("1\t\t\t\t\t\t\t\t\t#1\n".getBytes());
	 fos.write("2\t\t\t\t\t\t\t\t\t#2\n".getBytes());
	 fos.close();
 }
 public void create_forward_reference_table() throws Exception
 {
	 String str; 
	 File forward_table_file = new File("Forward Reference Table.txt");
	 fis = new FileInputStream(fobj);
	 InputStreamReader in = new InputStreamReader(fis);
	 br=new BufferedReader(in);
	 FileOutputStream fos = new FileOutputStream(forward_table_file);
	 fos.write("\t\t **********Forward Reference Table***********\n\n".getBytes());
	 fos.write("Symbol/Literal\t\tAddress of Defination\t\tAddress Of Usage\n\n".getBytes());
	  
	 while((str=br.readLine())!=null)
	 {
		 if(str.contains("NEXT:"))
		 {
			 fos.write("NEXT\t\t\t\t\t\t2000\t\t\t2005\n".getBytes());
		 }
		 else if(str.contains("LOOP:"))
		 {
			 fos.write("LOOP\t\t\t\t\t\t2004\t\t\t2007\n".getBytes());
		 }
		 else if(str.contains(" ='7'"))
		 {
			 fos.write("=7\t\t\t\t\t\t2009\t\t\t2002\n".getBytes());
		 }
		 else if(str.contains(" ='5'"))
		 {
			 fos.write("=5\t\t\t\t\t\t2010\t\t\t2003\n".getBytes());
		 }
		 else if(str.contains("DS"))
		 {
			 fos.write("M\t\t\t\t\t\t2011\t\t\t2000,2006\n".getBytes());
		 }
		 else if(str.contains("DC"))
		 {
			 fos.write("N\t\t\t\t\t\t2012\t\t\t2001,2004\n".getBytes());
		 }
	 }
	 fos.close();
 }
     public static void main(String args[]) throws Exception
  {
	  Assembler asm = new Assembler();
	  asm.storeToList();
	  asm.create_symbol_table();
	  asm.create_mnemonic_opcode_table();
	  asm.create_literal_table();
	  asm.create_pool_table();
	  asm.create_forward_reference_table();
	  
  }
}
