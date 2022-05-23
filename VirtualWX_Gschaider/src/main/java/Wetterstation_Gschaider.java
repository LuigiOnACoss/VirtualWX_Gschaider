import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Wetterstation_Gschaider {

    public static void main(String[] args) {

        try (Socket sock = new Socket("rotate.aprs.net", 10152)){

            OutputStream sout = sock.getOutputStream();
            PrintStream psout = new PrintStream(sout);

            InputStream sin = sock.getInputStream();
            BufferedReader bisr = new BufferedReader(new InputStreamReader(sin));

            System.out.println(bisr.readLine());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            psout.println("user HTLLE pass -l vers VirtualWx-groeger");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            String line;
            List<WeatherMessage> messages = new ArrayList<>();
            while(true) {
                line = bisr.readLine();

                if(line.contains(":@")) {

                    try {
                        WeatherMessage wmsg = new WeatherMessage(line);
                        messages.add(wmsg);
                    }catch(Exception ex) {

                    }
                }

            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}