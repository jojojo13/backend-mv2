package fa.group1.converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ObjectConverter {

	public String convertDate(String date) {
		Long timeStamp = Long.parseLong(date);
		DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		Date convert = new Date(timeStamp);
		return f.format(convert);
	}

	public String convertString(String string) {
		string = string.substring(1, string.length() - 1);
		return string;
	}

	public String[] convert(Object source) {
		try {
			String json = new ObjectMapper().writeValueAsString(source);
			json = json.substring(1, json.length() - 1);
			String[] data = json.split(",");
			System.out.println();
			return data;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
