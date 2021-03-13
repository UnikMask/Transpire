package Transpire;

import org.json.*;

import java.io.*;
import java.util.*;

public class Translations {
	
	public Translations(String languageToLoad, String rootPath){
		File rootFolder = new File(rootPath);

		File[] languageFolders = rootFolder.listFiles();
		for(int i = 0; i < languageFolders.length; i++){
			System.out.println(languageFolders[i]);
		}
	}
}