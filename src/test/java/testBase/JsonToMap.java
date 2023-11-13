package testBase;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToMap {
	public List<HashMap<String, String>> getDataFromJson(String path)
			throws StreamReadException, DatabindException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		File jsonFile = new File(path);
		List<HashMap<String, String>> map = mapper.readValue(jsonFile,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return map;
	}

}
