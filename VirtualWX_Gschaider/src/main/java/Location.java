public class Location {
    Double lat;
    Double lon;
    private Location(){}

 public static Location fromLoranPosition(String loranLat, String loranLon) {
 Location loc = new Location();

 int graddigits = (loranLat.indexOf(".") == 5) ? 3 : 2;
 double grad, minuten, sekunden;
 int vz = (loranLat.endsWith("S") || loranLat.endsWith("W")) ? -1 : 1;
 grad = Integer.parseInt(loranLat.substring(0, graddigits));
 minuten = Integer.parseInt(loranLat.substring(graddigits, graddigits + 2));
 sekunden = Integer.parseInt(loranLat.substring(loranLat.indexOf(".") + 1, loranLat.length() - 1));
 loc.lat = vz*(grad + ((minuten + (sekunden / 100d)) / 60d));

 int graddigits2 = (loranLon.indexOf(".") == 5) ? 3 : 2;
 double grad2, minuten2, sekunden2;
 int vz2 = (loranLon.endsWith("S") || loranLon.endsWith("W")) ? -1 : 1;
 grad2 = Integer.parseInt(loranLon.substring(0, graddigits));
 minuten2 = Integer.parseInt(loranLon.substring(graddigits, graddigits + 2));
 sekunden2 = Integer.parseInt(loranLon.substring(loranLon.indexOf(".") + 1, loranLon.length() - 1));
 loc.lon = vz2 * (grad2 + ((minuten2 + (sekunden2 / 100d)) / 60d));

 return loc;
 }
 }