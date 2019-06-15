package demo;

import java.util.HashMap;

import com.google.gson.Gson;

public class TripInf {
	public String taxiType;
	private String vendorId;
	private String pickupDateTime;
	private String dropOffDatetime;
	private String pickupLocationId="";
	private String dropOffLocationId ;
	private String type;
    static long fhvCounter = 0;
	static long greenCounter = 0;
	static long yellowCounter = 0;
	static HashMap<String, Integer> dates = new HashMap<>();
	static HashMap<String, Integer> vehicles = new HashMap<>();
	static double fhvMinutes = 0;
	static double yellowMinutes = 0;
	static double greenMinutes = 0;
	static int noDropFhv=0;
	static int noDropyellow=0;
	static int noDropGreen=0;
	static int fhvPickUp=0;
	static int yellowPickUp=0;
	static int greenPickup=0;


	public void addTrip(TripInf trip, String tripInformation) {
		
		

		Gson jsonObj = new Gson();
		trip = jsonObj.fromJson(tripInformation, TripInf.class);
		
		String[] pickUpDateAndTime = trip.pickupDateTime.split(" ");
		String [] dropOffDateAndTime = trip.dropOffDatetime.split(" ");
		
//		System.err.println(trip.pickupLocationId);
	
		
		if (trip.taxiType.equals("yellow")) {
			yellowCounter++;
			yellowMinutes+=tripMin(pickUpDateAndTime[1],dropOffDateAndTime[1]);
			 if(trip.dropOffLocationId.trim().equals(""))
				 noDropyellow++;
			 if(trip.pickupLocationId.contains("149"))//“Madison,Brooklyn
				 yellowPickUp++;
			}
		
		if (trip.taxiType.equals("green")) {
			greenCounter++;
			greenMinutes+=tripMin(pickUpDateAndTime[1],dropOffDateAndTime[1]);
			 if(trip.dropOffLocationId.trim().equals(""))
				 noDropGreen++;
			 if(trip.pickupLocationId.contains("149"))
				 greenPickup++;
			}
		if (trip.taxiType.equalsIgnoreCase("fhv")) {
			fhvCounter++;
			fhvMinutes+=tripMin(pickUpDateAndTime[1],dropOffDateAndTime[1]);
			System.out.println(fhvCounter);
			 if(trip.dropOffLocationId.trim().equals(""))
				 noDropFhv++;
			 if(trip.pickupLocationId.contains("149"))
				 fhvPickUp++;
		}

		
		
		if (!dates.containsKey(pickUpDateAndTime[0]))
			dates.put(pickUpDateAndTime[0], 1);
		

//		if (!vehicles.containsKey(trip.vendorId))
//			vehicles.put(trip.vendorId, 1);

	}

	private double tripMin(String start, String end) {
		String[] s = start.split(":");
		String[] e = end.split(":");
		for (int i = 0; i < 3; i++) {
			if (s[i].startsWith("0"))
				s[i] = s[i].substring(1);
			if (e[i].startsWith("0"))
				e[i] = e[i].substring(1);
		}
		double min = 0.0;
		
		int hours = Integer.parseInt(e[0])-(Integer.parseInt(s[0]));
		if(hours<0)
			hours+=24;
		
		min+=hours*60;
		int minn=Integer.parseInt(e[1])-Integer.parseInt(s[1]);
		if(minn<0)
			minn+=60;
		
		min+=minn;
			double sec=Integer.parseInt(e[2])-Integer.parseInt(s[2]);
			
			if(sec<0)
				sec+=60;
			sec/=60;
			
			min+=sec;
		
		return min;

	}
	public long getFhvCounter() {
		return fhvCounter;
	}

}
