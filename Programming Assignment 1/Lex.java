import java.io.*;

/*
 * Created by Jessica Pan on 10/1/2018
 * CruzID: jeypan 
 * PA1
 * CMPS101
 */

public class Lex {

	//Preconditions: there must be 2 arguments
	public static void main(String[] args) throws IOException {		//opens input file then calls functions and writes output file
		
		//checking for two command line arguments
		if (args.length != 2) {
			System.err.println("You need an input file and output file name. You can only pass two command line arguements.");
		}
		
		List L;
		String [] contentinFile;
		String inputFileName = String.format("%s", args[0]);
		String outputFileName = String.format("%s", args[1]);
		
		contentinFile = readFile(inputFileName);
		
		L = buildList(contentinFile);
		exportFile(outputFileName, contentinFile, L);
	}
	
	

	//Preconditions: program fails to write file to computer
	public static void exportFile (String name, String[] contentinFile, List L) {		//writes the output file to disk
		
		try {
			PrintWriter out = new PrintWriter(name);
			L.moveFront();
			
			for (int i = 0; i < contentinFile.length; ++i) {
				out.println(contentinFile[L.get()]);
				L.moveNext();
			}
			
			out.close();			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Preconditions: none
	public static List buildList (String[] contentinFile) {
		List L = new List();
		L.append(0);
		
		for (int i = 1; i < contentinFile.length; ++i) {
			L.moveFront();
			
			while (L.index() >= 0) {
				int x = L.get();
				
				if (contentinFile[i].compareTo(contentinFile[x]) < 0) {
					if (L.index() == 0) {
						L.prepend(i);
							break;
					}
					L.insertBefore(i);
					break;
				}
				L.moveNext();
			}
			if (L.index() < 0) {
				L.append(i);
			}
		}
		return L;
	}
	
	//Preconditions: program fails to find the file to open
	private static String[] readFile(String inputFileName) throws IOException {
		File file = new File(inputFileName);
		RandomAccessFile reader = null;
		
		reader = new RandomAccessFile(file, "r");
		int lines = 0;
		while (reader.readLine() != null)
			lines++;
		String[] contentinFile = new String[lines];
		reader.seek(0);
		
		try{ 
			String text = null;
			
			for (int i = 0; i < lines; i++) {
				contentinFile[i] = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}
		}
		
		return contentinFile;
	}
	

}
