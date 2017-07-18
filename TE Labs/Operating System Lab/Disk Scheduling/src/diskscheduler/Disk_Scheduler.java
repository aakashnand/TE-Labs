package diskscheduler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Disk_Scheduler {

	static ArrayList<Integer> list = new ArrayList<>();
	static ArrayList<Integer> trackList = new ArrayList<>();
	static int no_of_tracks,count=0,newtrack=0,newTrackTraversed,currentTrack,totalTrackTraversed=0;
	static int choice,i=0,flag1=0,found=0,j,set=0,k=0;
	static int given_Tracks[],sorted_tracks[];
	static BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception{
	
		System.out.println("Enter the no of tracks");
        no_of_tracks=Integer.parseInt(br.readLine());
        given_Tracks = new int[no_of_tracks];
       sorted_tracks= new  int [no_of_tracks];
        while(count!=no_of_tracks)
		{
			list.add(Integer.parseInt(br.readLine()));
			given_Tracks[count]=list.get(count);
			count++;
		}
       
       System.out.println("Enter the Current track from");
       currentTrack=Integer.parseInt(br.readLine());
       System.out.println("Enter the Choice of Strategy\n1.FIFO\n2.SSTF\n3.CSCAN\n4.SCAN");
       choice =Integer.parseInt(br.readLine());
       count=0;
       
       while(count!=no_of_tracks)
       {
    	  
    	   newtrack=givenewTrack(); 
    	   newTrackTraversed=Math.abs(currentTrack-newtrack);
    	   totalTrackTraversed+=newTrackTraversed;
    	   currentTrack=newtrack;
    	   count++;
       }
       System.out.println("Total Track Traversed:"+totalTrackTraversed+"\nAverage Seek Length:"+(totalTrackTraversed/(float)no_of_tracks));
       
	}
	public static int givenewTrack() {
		int track=0,sub1,sub2,flag=0;
	  try
	  {
		switch(choice)
 	   {
 	   case 1:
 		   track=given_Tracks[count];
 		   break;
 	   case 2:
 		  sorted_tracks=Arrays.copyOf(given_Tracks, given_Tracks.length);
 		  Arrays.sort(sorted_tracks);
 		  while(sorted_tracks[i]<=currentTrack)
 		  {
 			  i++;
 		  }
 		 
 		  sub2=Math.abs(currentTrack-sorted_tracks[i]);
 		  sub1=Math.abs(currentTrack-sorted_tracks[i-1]);
 		   if(sub1==0)
 		   {
 			   sub1=Math.abs(currentTrack-sorted_tracks[i-2]);
 			   flag=1;
 		   }
 		   //sub3=Math.abs(currentTrack-sorted_tracks[i-1]);
 		   k=Math.min(sub1, sub2);
 		   if(k==sub1 && flag==0)
 		   {
 			   track=sorted_tracks[i-1];
 			  if(trackList.contains(track))
			   {
				   track=sorted_tracks[i];
			   }
 		   }
 		   else if(k==sub1 && flag==1)
 		   {
 			  track=sorted_tracks[i-2];
 			  flag=0;
 		   }
 		   else if(k==sub2)
 		   {
 			   track=sorted_tracks[i];
 			  if(trackList.contains(track))
			   {
				   track=sorted_tracks[i-2];
			   }
 		   }
 		   break;
 	   case 3:
 		  sorted_tracks=Arrays.copyOf(given_Tracks, given_Tracks.length);
 		  Arrays.sort(sorted_tracks);
 		  while(i<sorted_tracks.length)
 		  {
 			  if(currentTrack<sorted_tracks[i])
 			  {
 				  break;
 			  }
 			  i++;
 			  j=i;
 		  }
 		 if(j>sorted_tracks.length || set==1)
		  {
			  while(k<i)
			  {
				  track=sorted_tracks[k];
				  k++;
				  break;
			  }
		  }
		
 		  while(j<sorted_tracks.length)
 		   {
 			  track=sorted_tracks[j];
 			 j++;
 			 if(j==sorted_tracks.length)
 			 {
 				set=1;	 
 			 }
 			 
 			 break;
 		   }
 		    break;
 	   case 4:
 		  sorted_tracks=Arrays.copyOf(given_Tracks, given_Tracks.length);
 		  Arrays.sort(sorted_tracks);
 		  while(i<sorted_tracks.length && found!=1)
 		  {
 			  if(currentTrack<sorted_tracks[i])
 			  {
 				  break;
 			  }
 			  i++;
 			 j=i;  
 		  }
 		  
 		 if(j>sorted_tracks.length || set==1)
		  {
			set=1;
			 while(k>=0)
			  {
				  track=sorted_tracks[k];
				  k--;
				  break;
			  }
		  
		 }
 		  while(j<=sorted_tracks.length && set ==0)
 			  {
 				 found=1; 
 				  track=sorted_tracks[j];
 				  if(j==(sorted_tracks.length-1))
 				  {
 					  set=1;
 				  }
 				 j++;
 				k=i-1;
 				  break;
 			  }
 		 
 	
 		   break;
 	   }
	  }
	  catch(ArrayIndexOutOfBoundsException e)
	  {
		  track=trackList.get(trackList.size()-1);
		  
	     if(track==sorted_tracks[0])
		  {
			  track=sorted_tracks[trackList.size()];
			  trackList.add(track);
			  flag1=1;
			  return  track;
		  }
		  
	  }
	  if(flag1==1)
	  {
		  track=sorted_tracks[trackList.size()];
		 // trackList.add(track);
	  }
	trackList.add(track);
	return track;
	}

}
 