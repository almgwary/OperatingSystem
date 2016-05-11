//============================================================================
// Name        : FileSystem
// Author      : Almgwary
// Date        : 2015
//============================================================================
import java.util.ArrayList;


public class File implements java.io.Serializable {
	
	private String filePath ;
	private String fileName ;
	private String allocationType ;
	public ArrayList<Integer> indexsAllocation ;
	private int size ;
	
	//creat file the file must be created and confirmed
	File (String filePath , String fileName ,  String allocationType ,ArrayList<Integer> indexsAllocation , int size ){
		this.filePath = filePath ;
		this.fileName = fileName ;
		this.allocationType = allocationType ;
		this.indexsAllocation = indexsAllocation ;
	    this.size = size ; 
	}
	
	//workingWithHardDrive
	public void deleteFile (){
		for(int index : indexsAllocation){
			HardDriver.deletBlock(index);
		}
	}
	
	public String getFilePath (){
		return filePath ;
	}
	
	public String getFileName (){
		return fileName ;
	}
	
	public String getAllocationType (){
		return allocationType ;
	}
	
	public ArrayList<Integer> getIndexsAllocation (){
		return indexsAllocation ;
	}
	
	public int getSize (){
		return size ;
	}


	
	

}
