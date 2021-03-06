package recommendation;

import java.util.ArrayList;
import java.util.List;
import db.DBConnection;
import db.DBConnectionFactory;
import entity.BaseStatus;
import entity.GeoLocation;
import external.GoogleAPI;
import external.Haversince;

public class ClosestBaseToRobot {
	public static String getAddress(String curLocation) {
		DBConnection conn = DBConnectionFactory.getConnection();
		
		// Pre-process address to fill empty spaces with '+' before passing to GoogleAPI
		String processedCurLocation = AddrAddPlus.convert(curLocation);
		
		GeoLocation encodedCurLocation = GoogleAPI.getGeoEncoding(processedCurLocation);
		
		List<BaseStatus> baseList = new ArrayList<>();
		baseList = conn.getBaseStatus();
		
		System.out.println("is baseList empty? " + baseList.get(0).getLat());
		
		List<GeoLocation> geoBaseList = new ArrayList<>();
		
		for(BaseStatus base : baseList) {
			GeoLocation tmp = new GeoLocation(Double.parseDouble(base.getLat()), Double.parseDouble(base.getLon()), base.getAddress());
			geoBaseList.add(tmp);
		}
		
		System.out.println("ClosestBaseToRobot: Can you reach me?");
		
		String baseAddress = null;
		double distance = 0.0;
		
		for(GeoLocation base : geoBaseList) {
			if(baseAddress == null) {			
				baseAddress = base.getAddress();			
				distance = Haversince.calculateDistance(base, encodedCurLocation);
				
			}else {			
				double res = Haversince.calculateDistance(base, encodedCurLocation);
				if(distance < res) {
					distance = res;
					baseAddress = base.getAddress();
				}
			}
		}
		System.out.println("ClosestBaseToRobot: " + baseAddress);
		return baseAddress;
	}
}
