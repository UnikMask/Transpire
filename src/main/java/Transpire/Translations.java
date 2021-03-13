package Transpire;

import org.json.*;

import java.io.*;
import java.util.*;

public class Translations {
	
	public Translations(String sLang, String pLang, String rootPath) throws NotSupportedLanguage{
		File rootFolder = new File(rootPath);

		// List<File> supportedSLangs = Arrays.asList(rootFolder.listFiles());
		// boolean foundSLang = false;
		// boolean foundPLang = false;

		// for(File sLangFolderName: supportedSLangs){
		// 	if(sLangFolderName.equals(sLangFolderName.getName())){
		// 		foundPLang = true;
				
		// 		File l
		// 	}
		// }
		// if(!supportsLanguage){
		// 	throw new NotSupportedLanguage("Unsupported language. Saweee");
		// }
	}

	public String getMapping(String languageToken){
		if(languageToken.equals("sinon")){
			return "else";
		}else{
			return "if";
		}
	}
}