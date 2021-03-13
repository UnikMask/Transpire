package Transpire;

import java.util.*;
import org.json.simple.*;

public class Mapping {
	
	String mappingName;
	
	Map<String,String> mapping = new HashMap<>();

	public Mapping(JSONObject translationStructure){	
		this.mappingName = (String)(translationStructure.get("mapping_name"));
		
		for (Object mapObj: (JSONArray)translationStructure.get("translations")){
			JSONObject mapJSON = (JSONObject)mapObj;
			mapping.put((String)(mapJSON.get("translation")),(String)(mapJSON.get("token")));
		}
	}

	@Override
	public String toString(){
		return mappingName;
	}
}
