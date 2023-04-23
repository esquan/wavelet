import java.net.URI;
import java.io.IOException;

class StringHandler implements URLHandler {
    String outputted = "";

    public String handleRequest(URI url) {
        if (url.getPath().contains("/add-message")) {
            String[] param = url.getQuery().split("=");
            outputted += param[1] + "\n";

            return outputted;
        }
        if (outputted.length() != 0) {
            return outputted;
        }
        return "404 Not Found!";
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number, try a number between 1024 and 49151!");
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}

