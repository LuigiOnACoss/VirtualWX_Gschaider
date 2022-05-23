import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherMessage {
    String stationid;
    Instant empfangzeitpunkt;
    Double temperatur_c;
    Location location;
    String lat;
    String lon;

    private Pattern ptrn_stationId = Pattern.compile("([A-Z-0-9]*)>");
    private Pattern ptrn_temperatur_f = Pattern.compile("t(\\d{3})");
    private Pattern ptrn_localtion_lat = Pattern.compile("([0-9]{4}\\.[0-9]{2}[NS])");
    private Pattern ptrn_localtion_lon = Pattern.compile("([0-9]{4,5}\\.[0-9]{2}[EW])");

    public WeatherMessage(String aprsMessage) {
        this.stationid = this.findPattern(aprsMessage,ptrn_stationId);
        String temperatur_f = this.findPattern(aprsMessage,ptrn_temperatur_f);
        this.temperatur_c = (Double.parseDouble(temperatur_f)-32.0) * (5.0/9.0);
        this.lat = this.findPattern(aprsMessage, ptrn_localtion_lat);
        this.lon = this.findPattern(aprsMessage, ptrn_localtion_lon);
        this.location = Location.fromLoranPosition(lat, lon);
        System.out.println("StationID: " + stationid + ", Temperatur: " + temperatur_c + "°C" + ", Location " + location.lat+ "/" + location.lon);
        System.out.println("");
    }

    String findPattern(String haystack, Pattern pattern)
    {
        Matcher matcher = pattern.matcher(haystack);
        while (matcher.find())
        {
            return matcher.group(1);
        }
        return null;
    }
}