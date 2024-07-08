package udemyselenium.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FileUtils;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		
		//Read Json to String
		String jsonContent = FileUtils.readFileToString(new File("D:\\Eclipse-Workspace\\SeleniumFrameworkDesign\\src\\test\\java\\udemyselenium\\data\\PurchaseOrder.json"),
				StandardCharsets.UTF_8);
		
		//String to HashMap (Jackson Databind)
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){		
		});
		return data;
		
		//{map, map}
	}
}

