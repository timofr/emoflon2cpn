package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;




public class FileBuilder {
	private String emptyCpn;
	private String path = "src/main/emptynet.cpn";
	private int startIndex;
	private int endIndex;

	public void scan() throws FileNotFoundException, IOException {
		try(Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter("\\A")) {     
			emptyCpn = scanner.hasNext() ? scanner.next() : "";
		}
		
		
	}
	
	public void findStartEndPoint() {
		startIndex = emptyCpn.indexOf("<page id=" + '"' + "ID6" + '"' + ">");
		endIndex = emptyCpn.indexOf("</page>");
		System.out.println(startIndex + " " + endIndex);
	}
	
	
}
