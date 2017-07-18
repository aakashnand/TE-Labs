package mypackage;

public class PCB 
{
     int TTL,TTC,TLL,TLC,PTR,IC,PID,TSC,program_card_from,program_card_to,data_card_from,data_card_to;               
     
  void getCounters(String line)
   {

		TTL = Integer.parseInt(line.substring(8, 12));
		TLL = Integer.parseInt(line.substring(12, 16));
		
   }
}
  
