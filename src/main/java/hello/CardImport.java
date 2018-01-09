package hello;


import java.io.IOException;
import java.net.URL;
import java.util.Scanner;



public class CardImport {
    public CardImport() throws IOException {
        zooi();
    }
    public void zooi() throws IOException {
        String s = "https://api.hearthstonejson.com/v1/18336/enUS/cards.collectible.json";

        URL url = new URL(s);
        Scanner scanner = new Scanner(url.openStream());

        //haalt json string op
        while(scanner.hasNext()){
            Scanner scan = new Scanner(url.openStream());
            String str = new String();
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();
        }
        //maakt json string object.

    }
}
