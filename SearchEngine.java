import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class SearchEngine implements URLHandler {
    ArrayList<String> listofstrings = new ArrayList<>();
    String output = "";
    String searched_output = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return output;
        }
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            listofstrings.add(parameters[1]);
            output += parameters[1] + " ";
            return output;
        }
        else if (url.getPath().contains("/search")) {
            if (listofstrings.size() == 1) {
                System.out.println("No strings have been added yet.");
            }
            String[] parameters2 = url.getQuery().split("=");
            for (String s: listofstrings) {
                if (s.contains(parameters2[1])) {
                    searched_output += s + " ";
                }
            }
            return searched_output;
        }
        searched_output = "";
        return "404 Not Found!";
    }
}

class SearchServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Port number missing! Try a number between 1024 to 49151.");
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngine());
    }
}