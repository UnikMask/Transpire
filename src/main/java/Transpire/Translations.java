package Transpire;

// import org.json.simple.*;

import java.io.*;
import java.util.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class Translations {
	
	private String sLang;
	private String code;
	private String lastUdated;
	private String pLang;
	private String maxSupportedVersion;
	private Mapping mapping;

	public Translations(String sLang, String pLang, String rootPath) throws NotSupportedLanguage{
		this(sLang,pLang,rootPath,"master");
	}

	
	public Translations(String sLang, String pLang, String rootPath,String version) throws NotSupportedLanguage{
		File rootFolder = new File(rootPath);

		boolean foundSLang = false;
		boolean foundPLang = false;
		File loadFile = new File("");
		for(File sLangFolder: Arrays.asList(rootFolder.listFiles())){
			if(sLangFolder.getName().equals(sLang)){
				foundSLang = true;
				for(File pLangFile: Arrays.asList(sLangFolder.listFiles())){
					if(pLangFile.getName().split("/")[0].replace(".json","").equals(pLang)){
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
			JSONObject translationJSON = (JSONObject) obj;
			System.out.println(translationJSON);

			System.out.println("Parsing successful!");


			for(Object mapping: (JSONArray)translationJSON.get("mappings")){
				JSONObject mappingStructure = (JSONObject) mapping;
				if(((String)mappingStructure.get("mapping_name")).equals(version)){
					this.mapping = new Mapping(mappingStructure);
					break;
				}
			}

			System.out.println(this.mapping);
		}catch(Exception e){
			throw new NotSupportedLanguage("Sorry, the mapping file is corrupt. Please download again ");
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