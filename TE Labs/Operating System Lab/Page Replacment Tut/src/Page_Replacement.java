import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;


public class Page_Replacement {

	static int string_size,frame_size,page_number,hint;
	static int matrix[][]={};
	public static void main(String args[])
	{
		Scanner scan =new Scanner(new InputStreamReader(System.in));
		System.out.println("Enter the Size of Page String and No of Frames");
		string_size =Integer.parseInt(scan.next());
		frame_size=Integer.parseInt(scan.next());
		System.out.println("Enter the String Now...");
		String page_Frame = scan.next();
		System.out.println("Enter the Strategy Number\n1.FIFO\n2.LRU\n3.OPT");
		int choice=scan.nextInt();
		char temp[]=page_Frame.toCharArray();
		int col=0,number,hit=1,miss=1,victim;
		boolean found;
	    matrix=new int[frame_size+1][string_size];
	    for(int x=0;x<frame_size+1;x++)
	     {
	           for(int y =0;y<string_size;y++)
	           {
	        	   matrix[x][y]=-1;
	           }
	     }
		for(int i=0;i<string_size;i++)
		{
			matrix[0][i]=Integer.parseInt(String.valueOf(temp[i]));
			
		}
		while(col<string_size)
		{
			number=matrix[0][col];
			page_number=number;
			//System.out.println(number);
			found=check_in_column(col-1, number);
			
			if(found==true)
			{
				for(int j=1;j<=frame_size;j++)
				{
					matrix[j][col]=matrix[j][col-1];
				}
				hit++;
				
			}
			
			
			else if(found==false && hint==2)
			{
				col++;
				miss++;
				hint=0;
				continue;
			
			}
			else
			{
			     miss++;
				switch(choice)
				{
				case 1:
					victim=select_a_FIFO_Victim(col);
					create_new_Column(col-1,victim);
					break;
				case 2:
					victim=select_a_LRU_Victim(col);
					create_new_Column(col-1,victim);
					break;
				case 3:
					victim=select_a_OPT_Victim(col);
					create_new_Column(col-1,victim);
					break;
				}
			}
			
			col++;
		}
		for(int x=0;x<frame_size+1;x++)
	     {
	           for(int y =0;y<string_size;y++)
	           {
	        	  System.out.print(matrix[x][y]+"\t");
	           }
	           System.out.print("\n");
	     }
		
	}
	
	private static int select_a_OPT_Victim(int col) {
		int i=0,no,row=1,index=1,j=0,local_column=col,final_index=0;
		int page_column[] = new int[frame_size];
		int freq[]=new int[frame_size];
		int sorted_freq[] = new int[frame_size];
		while(i<(frame_size))
		{
			no=matrix[row][col-1];
			page_column[i]=no;
			while(local_column<string_size)
			{
				if(no==matrix[0][local_column])
				{
					freq[i]=index;
					break;
				}
				
			    index++;	
				local_column++;
			}
			
			index=1;
			local_column=col;
			row++;
			i++;
		}
		sorted_freq=Arrays.copyOf(freq, freq.length);
		Arrays.sort(sorted_freq);
		while(j<freq.length)
		{
            if(freq[j]==sorted_freq[(freq.length-1)])
            {
            	final_index=j;
            	break;
            }
			j++;
		}
		return page_column[final_index];

		
	}

	public static boolean check_in_column(int col ,int no)
	  {
		boolean flag=false;
           		 
		 for(int i=1;i<=frame_size;i++)
		{
		     if(col ==-1)
		     {
		    	 matrix[i][col+1]=page_number;
		    	 hint=2;
		    	 flag=false;
		    	 break;
		     }
		     else if(matrix[i][col]==-1)
		     {
		    	 for(int j=1;j<=frame_size;j++)
					{
						matrix[j][col+1]=matrix[j][col];
					} 
		    	 matrix[i][col+1]=page_number;
		    	 hint=2;
		    	 flag=false;
	             break;
		     }
			 else if(matrix[i][col]==no)
		      {
		    	  flag= true;
		    	  break;
		      }
		      else
		      {
		  		flag= false;
		  		continue;
		  		
		      }
		}
      return flag;
		
	 }
	public static void create_new_Column(int col,int victim)
	{
		int row=1;
		while(row<=frame_size)
		{
			if(matrix[row][col]==victim)
			{
				matrix[row][col+1]=page_number;
			}
			else
			{
				matrix[row][col+1]=matrix[row][col];
			}
			row++;
		}
	}
	public static int select_a_FIFO_Victim(int col)
	  {
		int row=1,number,column=col-1,freq=1;
		int freq_table[][]=new int[frame_size][2];
		int temp[]=new int[frame_size];
		int temp1[]=new int[frame_size];
		int temp2[]=new int[frame_size];
		while(row<=frame_size)//for each column entry
		{
			number=matrix[row][col-1];//getting 1st number of column
			freq_table[row-1][0]=number;
			temp[row-1]=number;
			while(column>=0)
			{
				if(matrix[row][column]==number)
				{
					freq++;
				}				
				column--;
			}
			freq_table[row-1][0]=freq;
			temp1[row-1]=freq;
			column=col-1;
			freq=1;
			row++;
		}
		temp2=Arrays.copyOf(temp1, temp1.length);
		Arrays.sort(temp2);//sort temp1
		int myindex=-1;
		for(int q=0;q<frame_size;q++)
		{
			if(temp2[frame_size-1]==temp1[q])
			{
				myindex=q;
				break;
			}
		}
		return temp[myindex];
		
	  }
	public static int select_a_LRU_Victim(int col)
	{
		int i=0,no,row=1,index=1,j=0,local_column=col,final_index=0;
		int page_column[] = new int[frame_size];
		int freq[]=new int[frame_size];
		int sorted_freq[] = new int[frame_size];
		while(i<(frame_size))
		{
			no=matrix[row][col-1];
			page_column[i]=no;
			while(local_column>=0)
			{
				if(no==matrix[0][local_column-1])
				{
						
					break;
				}
				index++;
				local_column--;
			}
			freq[i]=index;
			index=1;
			local_column=col;
			row++;
			i++;
		}
		sorted_freq=Arrays.copyOf(freq, freq.length);
		Arrays.sort(sorted_freq);
		while(j<freq.length)
		{
            if(freq[j]==sorted_freq[(freq.length-1)])
            {
            	final_index=j;
            	break;
            }
			j++;
		}
		return page_column[final_index];
	}
  }

  