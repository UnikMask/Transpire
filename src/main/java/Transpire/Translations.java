package Transpire;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class Translations {
	
	private Mapper mapper;
	JSONParser parser;
	JSONObject translationJSON;

	public Translations(String sLang, String pLang, String rootPath) throws NotSupportedLanguage{
		this(sLang,pLang,rootPath,"master");
	}

	public static void updateTranslations(String sLang, String pLang, Boolean updateFlag) throws NotSupportedLanguage{
		try{
			// Check if file already exists
			App.verboseLog("Checking for translation...");
			File translationFile = new File("translations/" + sLang + "/" + pLang + ".json");
			if (updateFlag || !translationFile.exists()) {
				URL url = new URL("http://unikbase.space/translations/" + sLang + "/" + pLang + ".json");
				App.verboseLog("Translation not present! Downloading it from " + url);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");

				connection.setReadTimeout(5000);
				connection.setConnectTimeout(5000);

				int status = connection.getResponseCode();
				App.verboseLog("Getting response code " + status);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuilder content = new StringBuilder();
				while((inputLine = in.readLine()) != null){
					content.append(inputLine);
				}
				in.close();
				App.verboseLog("File written!");
				connection.disconnect();
				App.verboseLog("Disconnected from server");

				ObjectMapper mapper = new ObjectMapper();
				Object jsonObject = mapper.readValue(content.toString(), Object.class);
				String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);

			File file = new File("./translations/" + sLang + "/");

			if(!file.exists()) file.mkdir();
				BufferedWriter writer = new BufferedWriter(new FileWriter("./translations/" + sLang + "/" + pLang + ".json" ));
				writer.write(prettyJson);
				writer.close();
			}

		} catch(IOException ignored){

		}
	}

	
	public Translations(String sLang, String pLang, String rootPath,String version) throws NotSupportedLanguage{
		File rootFolder = new File(rootPath);

		boolean foundSLang = false;
		boolean foundPLang = false;
		File loadFile = new File("");
		try {
            for(File sLangFolder: rootFolder.listFiles()){
                if(sLangFolder.getName().equals(sLang)){
                    foundSLang = true;
                    for(File pLangFile: sLangFolder.listFiles()){
						App.verboseLog("Translating " + pLang
									+ " program at " + pLangFile + " to " + sLang);
                        if(pLangFile.getName().split("/")[0].replace(".json","").equals(pLang)){
                            foundPLang = true;
                            loadFile = pLangFile;
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            System.out.println("File does not exist");
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
			translationJSON = (JSONObject) obj;


			for(Object mapping: (JSONArray)translationJSON.get("mappings")){
				JSONObject mappingStructure = (JSONObject) mapping;
				if((mappingStructure.get("mapping_name")).equals(version)){
					this.mapper= new Mapper(mappingStructure);
					break;
				}
			}
		}catch(Exception e){
			throw new NotSupportedLanguage("Sorry, the mapping file is corrupt. Please download again ");
		}
	}

	public String getCommentRegex(){
		return translationJSON.get("commentRegex").toString();
	}


	public Mapper getMapper(){
		return this.mapper;
	}
}
