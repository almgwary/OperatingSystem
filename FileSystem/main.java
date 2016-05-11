//============================================================================
// Name        : FileSystem
// Author      : Almgwary
// Date        : 2015
//============================================================================
import java.io.IOException;
import java.util.Scanner;

import com.sun.xml.internal.ws.api.pipe.NextAction;

/*time : 8:45 h of work*/

public class main {

	public static void main(String[] args) throws IOException {
 		// TODO Auto-generated method stub
		
		//.0OsFunction.systemLoud();
		
		Scanner input=new Scanner(System.in);
		
		
		System.out.print("$System?-> ");
		String code = input.next() ;
		
		
		while(!code.equals("Q")){
	    	
			//reintilazi System
			  if (code.equals(".0")){
				System.out.print("$reintilaziSystem:");
				System.out.println("Enter size disks");
				OsFunction.systemReintilaz(input.nextInt());
			}
			//reintilazi System
		    else if (code.equals(".00")){
				System.out.print("$reintilaziSystem:");
				System.out.println("Enter N of available virtual disks");
				int N = input.nextInt();
				int totalSize = 0;
				while(N-- != 0){
//					System.out.println("Enter name");
//					input.next();
					System.out.println("Enter size");
					OsFunction.systemReintilaz(input.nextInt());
				}
				
			}
			//LoudFrom Memory  
			else if (code.equals(".01")){
				System.out.print("$loudFromMemory:");
				OsFunction.systemLoud();
			}
	    	//printEmptyBlocks 
			else if(code.equals("1")){
				System.out.print("$printEmptyBlocks:");
				OsFunction.printEmptyBlocks();
			}
			//printHardBlocks
			else if (code.equals("2")){
				System.out.print("$printHardBlocks:");
				OsFunction.printHardBlocks();
			}
			
			//FileAllocation
			else if (code.equals("3")){
				System.out.print("$creatFileEnterPath:");
				OsFunction.creatFile(input.next());
			}
			
			
			//deletFile
			else if (code.equals("4")){
				System.out.print("$deletFileEnterPath:");
				OsFunction.deletFile(input.next());
			}
			
			else if(code.equals("5")){
				System.out.print("$creatDirectoryEnterPath:");
				OsFunction.creatDirectory(input.next());
			}
			else if(code.equals("6")){
				System.out.print("$FileSystemTree\n");
				System.out.println("/root");
				OsFunction.printTree(100, "", OsFunction.root);
			}
			
			else if(code.equals("7")){
				System.out.print("$deletDirectoryEnterPath:");
				OsFunction.deletDirectory(input.next());
			}
			
			//reIntialize
			OsFunction.currentWorkinDirectory=OsFunction.root;
	    	System.out.println("\n\n\n- - - - - - - - - - - - - - - -");
	    	System.out.print("$System?-> ");
			code = input.next() ;
		}
		
		OsFunction.systemSave();
		
		
		

	}

}
