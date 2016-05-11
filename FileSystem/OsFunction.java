//============================================================================
// Name        : FileSystem
// Author      : Almgwary
// Date        : 2015
//============================================================================
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public abstract class OsFunction {
	

	public static Scanner input=new Scanner(System.in);
	public static Directory root ;
	public static Directory currentWorkinDirectory ;
	public static Directory currentWorkinDirectoryFather ;
	public static String path ;
	
	public static void systemReintilaz( int N)
	{
		HardDriver.creatHardDriver(N);
		root =new Directory("root", "root");
		currentWorkinDirectory = root ;
		
	}
	public static void systemLoud() throws IOException{
		
		HardDriver.emptyBlocks = new HashSet<Integer> ();
		HardDriver.blocks = new ArrayList<>() ;
		root =new Directory("root", "root");
		try
	      {
	         FileInputStream fileIn = new FileInputStream("employee.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         root = (Directory) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("DIRECTORY class not found");
	         c.printStackTrace();
	         return;
	      }
		
		
		
		
		//root = new Directory("root", "root");
		currentWorkinDirectory = root ;
		
		try
	      {
	         FileInputStream fileIn = new FileInputStream("HardArrayList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         
	         HardDriver.blocks = (ArrayList<ArrayList<Integer>>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("HardArrayList class not found");
	         c.printStackTrace();
	         return;
	      }		
		
		FileReader in = null;
	     try {
	         in = new FileReader("emptyPlocks.txt");
	        
	         
	         int c;
	         while ((c = in.read()) != -1) {
	        	 System.out.println("reading > "+ c);
	           HardDriver.emptyBlocks.add(c);
	         }
	      }finally {
	         if (in != null) {
	            in.close();
	         }
	      }
	}
	
	public static void systemSave() throws IOException{
		//Save HArd Disk
			//save Hard
		
			try
		      {
		         FileOutputStream fileOut =
		         new FileOutputStream("HardArrayList.ser");
		         ObjectOutputStream out1 = new ObjectOutputStream(fileOut);
		         out1.writeObject(HardDriver.blocks);
		         out1.close();
		         fileOut.close();
		         System.out.printf("Hard blocks saved in /HardArrayList.ser");
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
	    
			//Save Empty Blocks
			System.out.println("Saving empty blocks");
			  FileWriter out = null;
	
		      try {
		          
		         out = new FileWriter("emptyPlocks.txt");
		         
		         int c;
		         for (int i : HardDriver.emptyBlocks) {
		        	 System.out.println("writing > "+ i);
		        	 out.write(i);
		         }
		      }finally {
		         if (out != null) {
		            out.close();
		         }
		      }
		      
		      
		      
		    //Save File System Tree
				//Save Directorys
				//Save Files
		      try
		      {
		         FileOutputStream fileOut =
		         new FileOutputStream("employee.ser");
		         ObjectOutputStream out1 = new ObjectOutputStream(fileOut);
		         out1.writeObject(OsFunction.root);
		         out1.close();
		         fileOut.close();
		         System.out.printf("Serialized data is saved in /employee.ser");
		      }catch(IOException i)
		      {
		          i.printStackTrace();
		      }
		
		
	}
	
	public static boolean traversTree( ){
		
		
		
		//remove current from path
		int index = path.indexOf("/");
		System.out.println(index+ " "+ path.substring(0, index)+ " "+currentWorkinDirectory.getDirectoryName());
		if(path.substring(0, index).equals(currentWorkinDirectory.getDirectoryName())){
			
			path = path.substring(index,path.length()) ;
			System.out.println("NewPath = "+path);
			
			//check if there is another Traverse
			//end of valid path
			if(path.length()==1 && path.contains("/")){
				System.out.println("endOfValidpath");
				return true;
			}
			path= path.substring(1, path.length()) ;
			index = path.indexOf("/");
			if(index != -1){
				//getFileName
				String directoryeName =  path.substring(0, index) ;
				boolean  found = false ;
				directoryeName = directoryeName.substring(0, directoryeName.length()) ;
				System.out.println("weSerch for["+ directoryeName + "]directory");
				// point currnetWorkingDirectory To new directory
				for(Directory j : currentWorkinDirectory.getSubDirectorys()){
					if(j.getDirectoryName().equals(directoryeName)){
						currentWorkinDirectoryFather = currentWorkinDirectory ;
						currentWorkinDirectory = j ;
						found = true ;
						break ;
					}
				}
				if(found){
					return traversTree() ;
				}
				
			    
			}
			
		}
		
		
		System.out.println("INValiPath");
		return false;
		
	}
	
	public static void creatDirectory ( String newWath){
		
		//wrong path
		path =newWath ;
		if(!traversTree()){
			//check if there is no other othr subs directory you want in path
			int index = path.indexOf("/");
			if(index == -1){
				
				System.out.println("checkDublication for directory ="+ path);
				boolean  found = false ;
				// point currnetWorkingDirectory To new directory
				for(Directory j : currentWorkinDirectory.getSubDirectorys()){
					if(j.getDirectoryName().equals(path)){
						currentWorkinDirectory = j ;
						found = true ;
						break ;
					}
				}
				if(!found){
					System.out.println("newDirectoryName = "+ path);
					Directory directory = new Directory(path, currentWorkinDirectory.getDirectoryPath()+"/"+path);
					currentWorkinDirectory.addSubDirectory(directory);
				
				}
				else {
					System.out.println("Error Deuplicated ");
				}
				
			}else {
				System.out.println("NotFoundBath");
			}
 		}else{
 			System.out.println("INValiPath or dublication");
 		}
		
	}

	public static void deletDirectory ( String newWath){
		 //getDirectoryName
	   	 int indx = newWath.lastIndexOf("/");
	   	 String direName = newWath.substring(indx+1, newWath.length());
	   	 newWath = newWath.substring(0, indx+1);
	   	 System.out.println(" DireName = "+ direName + "  clearPath = "+ newWath);
	   	 
	     //checkPathhValidation
		  path =newWath ;
		 	if(traversTree())
		 	{
				System.out.println("Path is valid Curent Directory is "+ currentWorkinDirectory.getDirectoryName());
				//check  dir  validation
				boolean  found = false ;
			   System.out.println("weSerch for["+ direName + "]directory");
			
			   int count = 0 ;
				for(Directory j : currentWorkinDirectory.subDirectorys){
				if(j.getDirectoryName().equals(direName)){
					
					System.out.println(" Directory  Exist ");
					j.deletThisDirectory();
					currentWorkinDirectory.subDirectorys.remove(count);
					return ;
				 }
				++count ;
			   }
		 	}
		 	System.out.println("Path is Notvalid Curent Directory is "+ currentWorkinDirectory.getDirectoryName());
			
	   	 
	}
	
    public static void creatFile (String newWath){
		
    	 //getfileName
    	 int indx = newWath.lastIndexOf("/");
    	 String fileName = newWath.substring(indx+1, newWath.length());
    	 newWath = newWath.substring(0, indx+1);
    	 System.out.println(" FileName = "+ fileName + "  clearPath = "+ newWath);
    	 
    	 //checkPathhValidation
    	 path =newWath ;
 		if(traversTree()){
 			System.out.println("Path is valid Curent Directory is "+ currentWorkinDirectory.getDirectoryName());
 			//check  dublication Pathh validation
 			boolean  found = false ;
			//System.out.println("weSerch for["+ directoryeName + "]directory");
 			for(File j : currentWorkinDirectory.getSubFiles()){
				if(j.getFileName().equals(fileName)){
					System.out.println("Wrong File Exist ");
					return;
				}
			}
 			
 			System.out.print(" Enter Size Of File");
 			int size = input.nextInt() ;
 			System.out.print(" Enter type Of alocation [cont] | [indx]");
 			String type = input.next() ;
 			if(type.equals("cont")){
 				//null fail
 				ArrayList<Integer> index =HardDriver.contigousAllocation(size);
 				if(index != null) {
 					
 					//System.out.println("EnterFileName");
 					File newFile = new File (currentWorkinDirectory.getDirectoryPath(), fileName, "cont",index , size);
 					currentWorkinDirectory.addSubFile(newFile);
 				}else{
 					System.out.println("contFialed");
 				}
 			}else if(type.equals("indx")){
 				//-1 fail
 				int index =HardDriver.indexedAllocation(size);
 				if(index != -1) {
 					ArrayList<Integer> b = new ArrayList<>();
 					b.add(index);
 					//System.out.println("EnterFileName");
 					File newFile = new File (currentWorkinDirectory.getDirectoryPath()+"/", fileName, "indx",b , size);
 					currentWorkinDirectory.addSubFile(newFile);
 				}else{
 					System.out.println("indxFialed");
 				}
 			}
			
 		
 		
 		}
		
		
		
		
		
		
		
	}
    
    public static  void printTree (int level , String space ,Directory dir){
    	 if(level==0)return ;
    	 for(int i =0 ; i < dir.getDirectoryName().length();i++)
    	 space+=" ";
    	 for(File i : dir.getSubFiles()){
    		 System.out.println(space +"<F>"+ i.getFileName() +"  [size]"+ i.getSize()+"  [path] "+ i.getFilePath()+"  [allocationType]"+ i.getAllocationType()+"  [address]"+ i.indexsAllocation);
    	 }
    	 
    	 for(Directory i : dir.getSubDirectorys()){
    		 System.out.println(space +"<D>"+ i.getDirectoryName());
    		 printTree (level-- , space ,i);
    	 }
    	 
     }
     
	public static void deletFile (String newWath){
		 
		 //getfileName
   	 int indx = newWath.lastIndexOf("/");
   	 String fileName = newWath.substring(indx+1, newWath.length());
   	 newWath = newWath.substring(0, indx+1);
   	 System.out.println(" FileName = "+ fileName + "  clearPath = "+ newWath);
   	 
   	  //checkPathhValidation
	  path =newWath ;
	 	if(traversTree())
	 	{
			System.out.println("Path is valid Curent Directory is "+ currentWorkinDirectory.getDirectoryName());
			//check  file Path validation
			boolean  found = false ;
		    //System.out.println("weSerch for["+ directoryeName + "]directory");
		
			int count = 0 ;
			for(File j : currentWorkinDirectory.subFiles){
				if(j.getFileName().equals(fileName)){
				System.out.println(" File Exist ");
				j.deleteFile();
				 currentWorkinDirectory.subFiles.remove(count);
				return ;
			 }
				++count ;
		    }
			
			
   	 
		    /*if(found){
				System.out.println("looping on them to Delet");
				for(File i : currentWorkinDirectory.getSubFiles() ){
					System.out.println("->"+i.getFileName());
					if(i.getFileName().equals(fileName)){
						System.out.println("deleting file");
						i.deleteFile();
						System.out.println("FileDeleted");
						
					}
				}
		     }*/
		
	  }
		System.out.println("TraversinFaild in Deleting");

	
	}

	public static void printHardBlocks (){
		HardDriver.printHardBlocks();
	}
	
	
	public static void printEmptyBlocks (){
		HardDriver.printEmptyBlocks();
	}
}
