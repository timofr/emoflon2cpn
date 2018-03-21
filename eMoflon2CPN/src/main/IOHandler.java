package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;




public class IOHandler {
	public void write(File file, String content) throws IOException {
		Files.write(file.toPath(), content.getBytes(StandardCharsets.ISO_8859_1));
	}
	
	public String read(File file) throws IOException {
		return new String(Files.readAllBytes(file.toPath()));
	}
	
}
