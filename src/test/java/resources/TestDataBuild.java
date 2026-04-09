package resources;

import java.util.ArrayList;

import pojo.Location;
import pojo.Places;

public class TestDataBuild {

	public Places addPlacePayLoad(int accuracy, String address, String lang) {

		Places plc = new Places();
		plc.setAccuracy(accuracy);
		plc.setAddress(address);
		plc.setLanguage(lang);
		plc.setPhone_number("9999999999");
		plc.setWebsite("rahulshettyacademy.com");
		plc.setName("Hampi Phoenix");
		ArrayList<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		plc.setTypes(myList);
		Location loc = new Location();
		loc.setLat(-79.383494);
		loc.setLng(-9.383494);
		plc.setLocation(loc);
		return plc;

	}

	public String deletePlacePayLoad(String placeid) {

		return "{\n" + "    \"place_id\":\"" + placeid + "\"\n" + "}";

	}

}
