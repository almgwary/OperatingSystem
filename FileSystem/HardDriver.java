//============================================================================
// Name        : FileSystem
// Author      : Almgwary
// Date        : 2015
//============================================================================
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public abstract class HardDriver implements java.io.Serializable  {
	
    //hardDrive && blocks must be same length dont remove any elemnt from them
	//public static ArrayList<Integer> hardDrive ;
	//[blockIndex][if it have avalue]
	public static ArrayList < ArrayList < Integer > > blocks   = new ArrayList<>() ;
	public static Set<Integer> emptyBlocks = new HashSet<Integer> ();
	
	//all partions
	public  static ArrayList< ArrayList < ArrayList < Integer > > > allHardDisk =  new ArrayList<>() ;
	//all partions
	public  static ArrayList< Set<Integer> > allEmptyBlocks =  new ArrayList<>() ;
		
	public  HardDriver() {
		// Itilaiz evry thing from file
		
		
	}
	
	/**
	 * @param N number of blocks
	 * */
	
	public static void creatHardDriver( int N) {
		// Intilize your own data all free all to Zero
		emptyBlocks = new HashSet<Integer> ();
		blocks = new ArrayList<>() ;
		for(int i = 0 ; i < N ; ++i ){
			//System.out.println("--  " + i);
			emptyBlocks.add(i);
			//System.out.println("++");
			blocks.add(new ArrayList<Integer>());
			//System.out.println("''");
		}
		System.out.println("$HardDriveClass-> InitilaizationFinished");
    	
		/** add new disk*/
		allHardDisk.add(blocks);
		//add new empty blocks
		allEmptyBlocks.add(emptyBlocks);
	}

	
	public static void deletBlock ( int index)
	{
		System.out.println("$HardDriveClass-> deleting indeses "+ blocks.get(index));
		if(blocks.get(index).size()>1){
			System.out.println("$HardDriveClass-> oh this is indexed");
			for(Integer i : blocks.get(index)){
				System.out.println("indes-> " + i);

				ArrayList<Integer> b = new ArrayList<Integer>();
				blocks.set(i, b);
				emptyBlocks.add(i);
			}
			
		}
		blocks.set(index, new ArrayList < Integer >());
		emptyBlocks.add(index);
		
	}

	public static int getSize ()
	{
		return blocks.size();
	}
	
	public static int getFreeSize ()
	{
		return emptyBlocks.size();
	}
	
	public static int getAllocatedSize ()
	{
		return blocks.size()-emptyBlocks.size();
	}
	
    public static ArrayList<Integer> contigousAllocation (int size ){
		

		System.out.println("$HardDriveClass-> Wanted = "+ size +" , Avilable = "+ emptyBlocks.size() +" ");
		if(size <= emptyBlocks.size()){
			
			int brevous = -1 ,count = 1;
			boolean firsttime = true ;
			System.out.println("loping in hard to find conigus pf size = "+ size);
			for(int i : emptyBlocks )
			{
				if(firsttime ){
					System.out.println("first time");
					brevous = i ;
					firsttime = false ;
					//if want one and  found only one 
					if(size == 1 && emptyBlocks.size()>= 1){
						System.out.println("YouOnlyNeedFirstOne");
						//remove this indexs from empty blocks
						emptyBlocks.remove(i);
						//allocat them
						ArrayList<Integer> b = new ArrayList<Integer>();
					    b.add(-1);
					    blocks.set(i, b);
					    //return alocated in Arraylist
					    ArrayList<Integer> allocatedBlocks = new ArrayList<Integer> () ;
						i = brevous;
						for(int j = 0 ;j < size ; ++j ){
							allocatedBlocks.add(i);
						}
						return allocatedBlocks ;
					}
				}
				if(i == brevous +1){
					++count;
					brevous = i ;
					if(count == size){
						
						int lastIndexAllocation = brevous;
						//remove this indexs from empty blocks
						for(int j = 0 ;j < size ; ++j ){
							emptyBlocks.remove(lastIndexAllocation--);
						}
						//allocat them
						lastIndexAllocation = brevous;
						for(int j = 0 ;j < size ; ++j ){
							 ArrayList<Integer> b = new ArrayList<Integer>();
							 b.add(-1);
							blocks.set(lastIndexAllocation--, b);
						}
						//return alocated in Arraylist
						ArrayList<Integer> allocatedBlocks = new ArrayList<Integer> () ;
						lastIndexAllocation = brevous;
						for(int j = 0 ;j < size ; ++j ){
							allocatedBlocks.add(lastIndexAllocation--);
						}
						return allocatedBlocks ;
						
					}
				}else{
					brevous = i ;
					count = 1 ;
					continue;
				}
			}
			System.out.println("$HardDriveClass-> UnAvilabaleConigousBlocks");
		}
		
		System.out.println("$HardDriveClass-> LargSize");
		return null ;
	}
    
	public static void printEmptyBlocks  (){

		System.out.println("$HardDriveClass-> PrintEmptyBlocks");
    	int count = 1 ;
    	for( int i  : emptyBlocks ){
    		 System.out.print(i);
    		 if(i<100) System.out.print(" ");
    		 if(i<10)  System.out.print(" ");
    		++count;
    		if(count%10 == 0) System.out.println() ;
    	}
    	
	}
	
	
	public static void printHardBlocks  (){
    	System.out.println("$HardDriveClass-> printHardBlocks");
    	int count = 1 ;
    	for( ArrayList<Integer> i  : blocks ){
    		 if(i.size()==0){
    			 System.out.print("0 ");
    		 }else {
    			 System.out.print("1 ");
			}
    		++count;
    		if(count%10 == 0) System.out.println() ;
    	}
	}

    public static int indexedAllocation (int size ){
		

		System.out.println("$HardDriveClass-> Wanted = "+ size +" , Avilable = "+ emptyBlocks.size() +" ");
		if(size+1 <= emptyBlocks.size()){
			int baseIndex = 0 ;
			boolean getBaseIndex = true ;
			ArrayList<Integer> avilables = new ArrayList<Integer>();
			for(int i : emptyBlocks){
				
				
				if(getBaseIndex){
					baseIndex = i ;
					getBaseIndex = false ;
					continue ;
				}
				avilables.add(i);
				if(avilables.size() == size) {
					break ;
				}
			}
			
			
			
			
			//add array list to base index
			blocks.set(baseIndex, avilables);
			emptyBlocks.remove(baseIndex);
			//allocat indecs in array list
			for(int i : avilables){
				
				emptyBlocks.remove(i);
				//allocat them
				ArrayList<Integer> b = new ArrayList<Integer>();
			    b.add(-1);
				blocks.set(i, b);
			}
			
			return baseIndex ;
			
			
			
			 
		}
		
		System.out.println("$HardDriveClass-> LargSize");
		return -1 ;
	}

    public static void printAllHardDrivresInformation (){
    	
    	for(int i = 0 ; i < allHardDisk.size() ; ++i){
    		blocks =allHardDisk.get(i) ;
    		emptyBlocks = allEmptyBlocks.get(i);
    		System.out.println("------------------------\nHradDriver "+i+" : \n------------------------\nEmptyBlocks");
    		printEmptyBlocks();
    		System.out.println("hardBlocks");
    		printHardBlocks();
    	}
    }


    
}
