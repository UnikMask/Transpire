package Transpire;

// import org.json.simple.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	private Mapper mapper;
	JSONParser parser;
	JSONObject translationJSON;

	public Translations(String sLang, String pLang, String rootPath) throws NotSupportedLanguage{
		this(sLang,pLang,rootPath,"master");
	}
	public Translations(String sLang, String pLang) throws NotSupportedLanguage{
		updateTranslations(sLang, pLang);
	}

	public void updateTranslations(String sLang, String pLang) throws NotSupportedLanguage{
		try{
			URL url = new URL("http://unikbase.space/translations/" + sLang + "/" + pLang + ".json");
			System.out.println(url);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);

			int status = connection.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while((inputLine = in.readLine()) != null){
				content.append(inputLine);
			}
			in.close();
			connection.disconnect();
			BufferedWriter writer = new BufferedWriter(new FileWriter("./translations/" + sLang + "/" + pLang + ".json" ));
			writer.write(content.toString());
			writer.close();

			// System.out.println(content.toString());
			// System.out.println(status);

		}catch(MalformedURLException e){
			throw new NotSupportedLanguage("Sowee, the given language is nto currently supported. Maybe you can contribute? 😢");
		}catch(IOException e){
			throw new NotSupportedLanguage("No internet connection. Plz pay your bills");
		}
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
			throw new NotSupportedLanguage("Sorry the spoken human language is not supported.");
		}
		if(!foundPLang){
			throw new NotSupportedLanguage("Sorry the written programming language is not supported.");
		}

		parser = new JSONParser();

		try{
			Object obj = parser.parse(new FileReader(loadFile.getPath()));
			JSONObject translationJSON = (JSONObject) obj;
			// System.out.println(translationJSON);

			// System.out.println("Parsing successful!");


			for(Object mapping: (JSONArray)translationJSON.get("mappings")){
				JSONObject mappingStructure = (JSONObject) mapping;
				if(((String)mappingStructure.get("mapping_name")).equals(version)){
					this.mapper= new Mapper(mappingStructure);
					break;
				}
			}

			System.out.println(this.mapper);
		}catch(Exception e){
			throw new NotSupportedLanguage("Sorry, the mapping file is corrupt. Please download again ");
		}

		// InputStream inputStream = ReadJsonArra

	}


	// public String getMapping(String languageToken){
	// 	if(languageToken.equals("sinon")){
	// 		return "else";
	// 	}else{
	// 		return "if";
	// 	}
	// }

	public String getCommentRegex(){
		return translationJSON.get("commentRegex").toString();
	}


	public Mapper getMapper(){
		return this.mapper;
	}
}