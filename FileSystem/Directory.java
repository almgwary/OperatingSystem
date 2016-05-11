//============================================================================
// Name        : FileSystem
// Author      : Almgwary
// Date        : 2015
//============================================================================
import java.util.ArrayList;


public class Directory  implements java.io.Serializable{

	private String directoryName;
	private String directoryPath ;
	public ArrayList <Directory>  subDirectorys ;
	public ArrayList <File> subFiles ;

	public Directory(String directoryName , String directoryPath ) {
		// TODO Auto-generated constructor stub
		this.directoryName = directoryName ;
		this.directoryPath = directoryPath ;
		subFiles = new   ArrayList <File> ();
		subDirectorys = new ArrayList <Directory> ();
	}
	
	public void deletThisDirectory (){
		
		for(File i : subFiles){
			i.deleteFile();
		}
		
		subFiles = new   ArrayList <File> () ;
		
		for(Directory i : subDirectorys){
			i.deletThisDirectory();	
		}
		
		subDirectorys =  new ArrayList <Directory> () ;
		
	}
	public void addSubDirectory(Directory newDirectorys) {
		//add directory
		//Directory opject must be created and confirmid
		subDirectorys.add(newDirectorys);
	}
	
	public void addSubFile(File newFile) {
		//add directory
		//File opject must be created and confirmid
		subFiles.add(newFile);
	}
	
	public String getDirectoryName() {
		return directoryName;
	}

	public  ArrayList <Directory> getSubDirectorys() {
		return subDirectorys;
	}
	
	public void setSubDirectorys(  ArrayList <Directory>  x) {
		 subDirectorys =x;
	}
	
	public  ArrayList <File> getSubFiles() {
		return subFiles;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}
	
}
