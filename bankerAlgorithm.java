//============================================================================
// Name        : bankerAlgorithm
// Author      : Almgwary
// Date        : 2015
//============================================================================
package bankerAlgorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

 /**
  *Java testBanker file.txt 10 5 7
  * RQ 1 3 3 3
  * RQ 1 1 1 1
  */
 
public class bankerAlgorithm {

	/**
	 * readFromFile
	 * D:\fci\3rd\OS\assigments\ass1\java\file.txt
	 */  
	
	 
	 
     public static List<String> readLines( String filePath) throws Exception {
	      
		 File file = new File(filePath);
		    if (!file.exists()) {
		      System.out.println(filePath + " does not exist.");
		      return null;
		    }
		    if (!(file.isFile() && file.canRead())) {
		      System.out.println(file.getName() + " cannot be read from.");
		      return null;
		    }
	      BufferedReader reader = new BufferedReader(new FileReader(file));
	      List<String> results = new ArrayList<String>();
	      String line = reader.readLine();
	      while (line != null) {
	    	  
	          results.add(line);
	          line = reader.readLine();
	      }
	      return results;
	  }

     
     
     public static  boolean checkDedLook(int process  ,int r1  ,int r2 ,int r3 ,int [] avilable ,int [][] maxNeed,int [][] Need ,int [][] allocatoin  )
     {
    	 
    	 
    	 //copying
    	 int [] avilableTest = new int [3];
    	 avilableTest[0]=avilable[0];
    	 avilableTest[1]=avilable[1];
    	 avilableTest[2]=avilable[2];
    	 
    	 int [][] maxNeedTest= new int [allocatoin.length][3];
    	 //System.out.print("Copying ..\nmax ");
    	 for(int i=0;i<allocatoin.length;i++)
    	 {
    		 maxNeedTest[i][0]=maxNeed[i][0];
    		 maxNeedTest[i][1]=maxNeed[i][1];
    		 maxNeedTest[i][2]=maxNeed[i][2];
    	 }
    	
    	// System.out.print("Copying ..\nNeedTest ");
    	 int [][] NeedTest = new int [allocatoin.length][3];
    	 for(int i=0;i<allocatoin.length;i++)
    	 {
    		 NeedTest[i][0]=Need[i][0];
    		 NeedTest[i][1]=Need[i][1];
    		 NeedTest[i][2]=Need[i][2];
    	 }
    	
    	// System.out.print("Copying ..\nallocatoinTest ");
    	 int [][] allocatoinTest= new int [allocatoin.length][3] ;
    	 for(int i=0;i<allocatoin.length;i++)
    	 {
    		 allocatoinTest[i][0]=allocatoin[i][0];
    		 allocatoinTest[i][1]=allocatoin[i][1];
    		 allocatoinTest[i][2]=allocatoin[i][2];
    	 }
    	
    	 boolean x=false;
    	 /**
    	  * newVariable for tese 
    	  * put new values  for avilable and allocation
    	  * 
    	  * calculat need
     	  * 		check safe state sequence
     	  * 		if done ok
    	  */
    	  
    	  avilableTest[0]-=r1;
    	  avilableTest[1]-=r2;
    	  avilableTest[2]-=r3;
    	  
    	  allocatoinTest[process][0]+=r1;
    	  allocatoinTest[process][1]+=r2;
    	  allocatoinTest[process][2]+=r3;
    	  
    	  
    	  System.out.println("Testing...\navilable="+avilableTest[0]+","+avilableTest[1]+","+avilableTest[2]);
    	 
    	   System.out.println("calculatingNeed....");
    	  for(int i=0;i<maxNeedTest.length;i++)
    	  {
    		  NeedTest[i][0]=maxNeedTest[i][0]-allocatoinTest[i][0];
    		  NeedTest[i][1]=maxNeedTest[i][1]-allocatoinTest[i][1];
    		  NeedTest[i][2]=maxNeedTest[i][2]-allocatoinTest[i][2];
    		  System.out.println(NeedTest[i][0]+","+NeedTest[i][1]+","+NeedTest[i][2]);
        	  
    	  }
    	  
    	  System.out.print("testing process...");
    	 
    	  boolean loopAgain=false; 
    	  /**for finished process*/
    	  Set<Integer> finishedProcc =new HashSet();
    	  for(int i=0;i<maxNeedTest.length;i++){
    		 /**chec if need < avilable and process is not of finiched list*/
    		  System.out.print("\n\nProcess: " +i);
    		 if(NeedTest[i][0]<=avilableTest[0] &&
    				  NeedTest[i][1]<=avilableTest[1]&&
    				  NeedTest[i][2]<=avilableTest[2]&& !finishedProcc.contains(i))
    		 {
    			 System.out.print("\n>NENTER" );
    			 finishedProcc.add(i);
    			 
    			 avilableTest[0]+=allocatoinTest[i][0];
    	    	 avilableTest[1]+=allocatoinTest[i][1];
    	    	 avilableTest[2]+=allocatoinTest[i][2];
    	    	 
    	    	 System.out.print("\ncurrntProccAloocationTest: " +allocatoinTest[i][0]+" "+allocatoinTest[i][1]+" "+allocatoinTest[i][2]+" ");
    	    	 allocatoinTest[i][0]++;
    	    	 System.out.print("\nSystemAvilableAfter: " +avilableTest[0]+" "+avilableTest[1]+" "+avilableTest[2]+" ");
    	    	 
    	    	 
    	    	 
    	    	 loopAgain=true;
    		  }else{
    			  System.out.print("\n>Out " );
    		  }
    		 
    		 if(loopAgain && (i+1)==maxNeedTest.length)
    		 {
    			 System.out.print("\n\nenterAgain:");
    			 i=-1;
    			 loopAgain=false;
    		 }
    	  }
    	  if(finishedProcc.size()==allocatoinTest.length)
    	  {
    		  System.out.print("\nSAVEsTATE");
    		  x=true;
    	  }
    	 
		return x;
    	 
     }

	 public static  void RQ(int process  ,int r1  ,int r2 ,int r3 ,int [] avilable ,int [][] maxNeed,int [][] Need ,int [][] allocatoin ){
     	/**
     	 * process reques
     	 * check <= max && <= avialble
     	 * check in save state
     	 * */
		 if(r1>avilable[0]||r2>avilable[1]||r3>avilable[2]){
			 System.out.print("ACECC Denid\nProcess:"+process+" is large than avilable\n");
			 
		 }else if( r1>(maxNeed[process][0]+allocatoin[process][0])
				 ||r2>(maxNeed[process][1]+allocatoin[process][1])
				 ||r3>(maxNeed[process][2]+allocatoin[process][2])){
			 System.out.print("ACECC Denid\nProcess:"+process+" is large than Max\n");
		 }else 
		 {
			 System.out.print("this request  granted  banker’s algorithm:\n");
			 
			 
			 /**
			  * if it not leed to dead look
			  * concum from avilable 
			  * but into allocation
			  * concum reqst from need  
			  * */
			if( checkDedLook(process, r1, r2, r3, avilable,  maxNeed, Need ,  allocatoin)){
				  avilable[0]-=r1;
		    	  avilable[1]-=r2;
		    	  avilable[2]-=r3;
		    	  
		    	  
		    	  allocatoin[process][0]+=r1;
		    	  allocatoin[process][1]+=r2;
		    	  allocatoin[process][2]+=r3;
		    	  
		    	  System.out.print("\nthis request Acepted \n");
		    	  
			}else{
				System.out.print("\nthis request Not Acepted \n");
			}
		 }
		 
		 
	 }
	

	 
     public static  void RL(int process  ,int r1  ,int r2 ,int r3 ,int [] avilable ,int [][] maxNeed,int [][] Need ,int [][] allocatoin ){
		if(allocatoin[process][0]>=r1&&allocatoin[process][1]>=r2&&allocatoin[process][2]>=r3) 
		{
			 avilable[0]+=r1;
	   	     avilable[1]+=r2;
	   	     avilable[2]+=r3;
	   	     System.out.print("\nUpdating avilable..");
	         System.out.print("\nSystemAvilable: " +avilable[0]+" "+avilable[1]+" "+avilable[2]+" ");
	         
	         allocatoin[process][0]-=r1;
	         allocatoin[process][1]-=r2;
	         allocatoin[process][2]-=r3;
	         System.out.print("\nUpdating process:"+process+"allocation ..");
	         System.out.print("\nprocess:"+process+"allocation :" +allocatoin[process][0]+" "+allocatoin[process][1]+" "+allocatoin[process][2]+" ");
	          
		}else{
			
			System.out.print("\n cannot Relese");
	          
		}
		 
	 }
    
     public static  void print( int [] avilable ,int [][] maxNeed,int [][] Need ,int [][] allocatoin ){
	 

     System.err.print("\n------------------------\nSystemAvilable: " +avilable[0]+" "+avilable[1]+" "+avilable[2]+" ");
     
     
      System.err.print("\nmax \n");
	 
	  for(int i=0;i<allocatoin.length;i++)
	 {
		 System.err.print(maxNeed[i][0]+" ");
		 System.err.print(maxNeed[i][1]+" ");
		 System.err.print(maxNeed[i][2]+" \n");
	 } 
	 System.err.print("\nNeed \n");
	   for(int i=0;i<allocatoin.length;i++)
	 {
		 System.err.print(Need[i][0]+" ");
		 System.err.print(Need[i][1]+" ");
		 System.err.print(Need[i][2]+" \n");
	 }
	  
	 
	 System.err.print("\nallocatoin \n");
	   for(int i=0;i<allocatoin.length;i++)
	 {
		 System.err.print( allocatoin[i][0]+" ");
		 System.err.print(allocatoin[i][1]+" ");
		 System.err.print(allocatoin[i][2]+" \n");
	 } 
	 
 }
	
     
     public static void main(String[] args) throws Exception {
		   int [] avilable = new int [3] ;
		   int [][] maxNeed   ;  //done
		   int [][] Need ;
		   int [][] allocatoin ;
		Scanner input =new Scanner (System.in) ;
		System.err.print("assume that processes are independent: \n$Code> ");
		 String inputCode =input.nextLine();
		 
		 /**  take intial avilable values*/
		 avilable[0]=Integer.parseInt( (inputCode.substring( inputCode.length()-6,  inputCode.length()-4)));
		 avilable[1]=Integer.parseInt( (inputCode.substring( inputCode.length()-3,  inputCode.length()-2)));
		 avilable[2]=Integer.parseInt( (inputCode.substring( inputCode.length()-1)));
		 /**delete Last intgers from code*/
		 inputCode=inputCode.substring(16, inputCode.length()-7);
		 System.out.println(inputCode);
		 
		 /**  get max need [][]data from file in array List*/
		 ArrayList<String> myData =new ArrayList<String>();
		
		 myData=(ArrayList<String>) readLines(inputCode );
		 //System.out.println(myData);
		 
		 /** reIntilize maxNed with n of prossess and put values in array*/
		 int pNum=myData.size();
		 maxNeed = new int [pNum][3] ;
		 Need = new int [pNum][3] ;
		 allocatoin = new int [pNum][3]  ;
         for(int i=0;i<pNum;i++)
         {
        	 maxNeed[i][0]=Integer.parseInt(myData.get(i).substring(0,1));
        	 maxNeed[i][1]=Integer.parseInt(myData.get(i).substring(2,3));
        	 maxNeed[i][2]=Integer.parseInt(myData.get(i).substring(4));
         }
         /** get input for run system*/
         
         inputCode="AA";
         while(!inputCode.equals("Q"))
         {
        	 System.err.print("\n--------------------\n$Code>  ");
        	 inputCode =input.next();
        	 if(inputCode.equals("*")){
        		 print(avilable, maxNeed, Need, allocatoin);
        	 }else if(inputCode.equals("RL")){
        		 int process=input.nextInt() ,r1 =input.nextInt(), r2=input.nextInt(), r3=input.nextInt(); 
        		 RL(process, r1, r2, r3, avilable, maxNeed, Need, allocatoin);

        	 }else if(inputCode.equals("RQ")){
        		 int process=input.nextInt() ,r1 =input.nextInt(), r2=input.nextInt(), r3=input.nextInt(); 
        		
        		 RQ(process, r1, r2, r3, avilable,  maxNeed, Need ,  allocatoin);
        		  
        	 
        	 }else{
        		 System.err.print("Error");
        	 }
        	 
        	 
              
         }
        
         System.err.print("--------------------\nSYSTEM OFF");
	}



 
 

}