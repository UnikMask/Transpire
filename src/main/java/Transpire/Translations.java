package Transpire;

// import org.json.simple.*;

import java.io.*;
import java.util.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Translations {	
	
	public Translations(String sLang, String pLang, String rootPath) throws NotSupportedLanguage{
		File rootFolder = new File(rootPath);

		boolean foundSLang = false;
		boolean foundPLang = false;
		File loadFile = new File("");
		for(File sLangFolder: Arrays.asList(rootFolder.listFiles())){
			if(sLangFolder.getName().equals(sLang)){
				foundSLang = true;
				for(File pLangFile: Arrays.asList(sLangFolder.listFiles())){
					System.out.println(pLangFile);
					// System.out.println( printList(Arrays.asList(pLangFile.getName().split("/"))));
					// System.out.println(printList(Arrays.asList(pLangFile.getName().split("/"))));
					System.out.println(pLangFile.getName().split("/")[0]);
					if(pLangFile.getName().split("/")[2].replace(".json","").equals(pLang)){
						foundPLang = true;
						loadFile = pLangFile;
					}
				}
			}
		}
		if(!foundSLang){
			throw new NotSupportedLanguage("Sorry the spoken human langauge is not supported. 🙇‍♂️");
		}
		if(!foundPLang){
			throw new NotSupportedLanguage("Sorry the written programming language is not supported. 😬");
		}

		JSONParser parser = new JSONParser();

		try{
			Object obj = parser.parse(new FileReader(loadFile.getPath()));
			JSONObject jsonObject = (JSONObject) obj;
			System.out.println("Parsing successful!");
		}catch(Exception e){
			//
		}

		// InputStream inputStream = ReadJsonArra

	}


	public String getMapping(String languageToken){
		if(languageToken.equals("sinon")){
			return "else";
		}else{
			return "if";
		}
	}
}