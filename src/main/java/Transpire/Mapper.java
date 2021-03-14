package Transpire;

import java.util.*;
import org.json.simple.*;

public class Mapper {
	
	String mappingName;
	
	Map<String,String> mapping = new HashMap<>();

	public Mapper(JSONObject translationStructure){	
		this.mappingName = (String)(translationStructure.get("mapping_name"));
		
		for (Object mapObj: (JSONArray)translationStructure.get("translations")){
			JSONObject mapJSON = (JSONObject)mapObj;
			mapping.put((String)(mapJSON.get("translation")),(String)(mapJSON.get("token")));
		}
	}

	public String translate(String sLang){
		return mapping.get(sLang);
	}

	public boolean checkIfKeyword(String sLang) {
		return mapping.get(sLang) != null;
	}

	public String getCommentRegex(){
	    return "";
    }

	@Override
	public String toString(){
		return mappingName;
	}
}
