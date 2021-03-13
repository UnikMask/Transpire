package Transpire;

import org.json.*;

import java.io.*;
import java.util.*;

public class Translations {
	
	public Translations(String languageToLoad, String rootPath) throws NotSupportedLanguage{
		File rootFolder = new File(rootPath);

		List<File> supportedLanguages = Arrays.asList(rootFolder.listFiles());
		boolean supportsLanguage = false;
		for(File folder: supportedLanguages){
			if(languageToLoad.equals(folder.getName())){
				supportsLanguage = true;
			}
		}
		if(!supportsLanguage){
			throw new NotSupportedLanguage("Unsupported language. Saweee");
		}
	}
}