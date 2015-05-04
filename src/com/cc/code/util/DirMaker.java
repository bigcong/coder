package com.cc.code.util;

import java.io.File;
import java.io.IOException;

public class DirMaker {
	
	public static void main(String[] args) {
		String filePath = "D:/temp/a/b/c/d/e/f/g.txt";
		File file = new File(filePath);
		try {
			System.out.println("file.exists()? " + file.exists());
			
			boolean created = createFile(file);
			
			System.out.println(created ? "File created"
					: "File exists, not created.");
			System.out.println("file.exists()? " + file.exists());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		return file.createNewFile();
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

}
