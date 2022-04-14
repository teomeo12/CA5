package BusinessObjects;

import DTOs.Singer;
import JsonLocalDateAdapter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import static BusinessObjects.App.displayOneSingers;

public class Client {
    private static final Scanner kb = new Scanner(System.in);
    private static final Pattern PATTERN = Pattern.compile("^[0-9]{1,}$");
    private static final  Gson gsonParser = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    //    public void start() throws IOException {
//
//        try {
//            displayMainMenu();        // User Interface - Menu
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void start() throws IOException {


        Socket socket = new Socket("localhost", 8080);  // connect to server socket
        OutputStream os = socket.getOutputStream();
        PrintWriter socketWriter = new PrintWriter(os, true); ;   // true => auto flush buffers
        Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
        System.out.println("Client: Port# of this client : " + socket.getLocalPort());
        System.out.println("Client: Port# of Server :" + socket.getPort());

        System.out.println("Client message: The Client is running and has connected to the server");
        System.out.println("Please choose option from the main menu: ");

        final String MENU_ITEMS =
                "*------------------------------------------------*\n"
                        + "*         *** MAIN MENU OF OPTIONS ***          *\n"
                        + "*-----------------------------------------------*\n"
                        + "*  1. Display Singer by Id                      *\n"
                        + "*  2. Display all Singers                       *\n"
                        + "*  3. Display Singer with highest rate          *\n"
                        + "*  4. Add  Singer                               *\n"
                        + "*  5. Delete Singer by ID                       *\n"
                        + "*  -------------------------------------------  *\n"
                        + "*  6. Exit                                      *\n"
                        + "*-----------------------------------------------*\n"
                        + "*              Enter Option [1,5]               *\n"
                        + "*-----------------------------------------------*";


        final int DISPLAY_SINGER_BY_ID = 1;
        final int DISPLAY_ALL_SINGERS = 2;
        final int DISPLAY_SINGER_HIGH_RATE = 3;
        final int ADD_SINGER = 4;
        final int DELETE_BY_ID = 5;
        final int EXIT = 6;


        int option = 0;

        do {
            System.out.println("\n" + MENU_ITEMS);
            try {

                String usersInput = kb.nextLine();
                option = Integer.parseInt(usersInput);
                String command;
                Singer singer;
                String response=null;
                Singer[] singerArray;

              //  ObjectMapper om = new ObjectMapper();
                switch (option) {
                    // Feature-14
                    case DISPLAY_SINGER_BY_ID:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##       Display Singers by ID        ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        System.out.println("Please Enter Singer ID");
                        String id = kb.nextLine();

                       // command = "DisplaySingerByID";

                        if (isIDExist(id)) {
                           // command = command + " " + id;
                            socketWriter.println("DISPLAY_SINGERS_BY_ID "+ id);
                            response = socketReader.nextLine();
                            if (response == null)
                                System.out.println("Client message: There is and Error in response " );
                            else {
                                System.out.println("Client message: Response from server:\n Singer: " + response);
                                 singer = gsonParser.fromJson(response, Singer.class);

                                System.out.println("\nConvert and display a singer object by ID: "+id+" :\n ");
                                displayOneSingers(singer);
                                System.out.println("\nPress Enter to continue...");
                            }

                        } else
                            System.out.println("\nClient message: Invalid ID");

                        break;

                    // Feature 15
                    case DISPLAY_ALL_SINGERS:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##     Display all Singers   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        socketWriter.println("DISPLAY_ALL_SINGERS");

                        response = socketReader.nextLine();
                        System.out.println("Client message: Response from server: \"" + "JsonData:"+ "\"");
                        System.out.println(response);

                        singerArray = gsonParser.fromJson(response, Singer[].class);
                        System.out.println("\nConvert and display the JsonData to objects :\n ");
                        Singer.displayAllSingers(singerArray);


                        break;
                    // Feature 19
                    case DISPLAY_SINGER_HIGH_RATE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##     Display Singer with highest rate   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        socketWriter.println("DISPLAY_SINGER_HIGHEST_RATE");
                        response = socketReader.nextLine();
                        if (response == null)
                            System.out.println("Client message: There is and Error in response " );
                        else {
                            System.out.println("Client message: Response from server:\nSinger with highest rate: " + response);
                            singer = gsonParser.fromJson(response, Singer.class);

                            System.out.println("\nConvert and display a JSON string in to Singer Object : ");

                           displayOneSingers(singer);
                            System.out.println("\nPress Enter to continue...");
                        }

                        break;
                    // Feature 16
                    case ADD_SINGER:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##         Add Singer        ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("\nClient message: Please Enter new Singer fields");

                        singer = singerFields();

                        if(singer !=null){
                            System.out.println("Adding new singer...");
                           String singerJson = gsonParser.toJson(singer);

                           socketWriter.println("ADD_SINGER;" +singerJson);
                            response = socketReader.nextLine();
                            System.out.println("Client message: " + response);
                            System.out.println("\nPress Enter to continue...");
                        }else{
                            System.out.println("\nClient message: Invalid singer fields");
                        }

                        break;
                    // Feature 17
                    case DELETE_BY_ID:

                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##     Delete Singer by ID    ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        boolean isShow;
                        do{
                            socketWriter.println("DISPLAY_ALL_SINGERS");
                            isShow =false;
                            String response1 = socketReader.nextLine();
                            System.out.println("Client message: Response from server: \"" + "JsonData:"+ "\"");
                            System.out.println(response1);
                             singerArray = gsonParser.fromJson(response1, Singer[].class);
                            System.out.println("\nConvert and display the JsonData to objects :\n ");
                            Singer.displayAllSingers(singerArray);

                        }while(isShow ==true);


                        System.out.println("Please Enter Singer ID");
                        id = kb.nextLine();
                        command = "DELETE_BY_ID";

                        if (isIDExist(id)) {
                            command = command + " " + id;
                            socketWriter.println(command);
                            response = socketReader.nextLine();
                            System.out.println("Client message: Deleting singer...");
                            System.out.println("Client message: " + response);
                        } else
                            System.out.println("\nClient message: Invalid ID");
                        break;

                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }

                kb.nextLine();
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }

        } while (option != EXIT);

        socketWriter.close();
        socketReader.close();
        socket.close();
        System.out.println("\nExiting Main Menu, goodbye.");
    }

    private Singer singerFields() {
        try
        {
            System.out.println("\nEnter Singer name: ");
            String singerName = kb.nextLine();

            System.out.println("\nEnter Singer date of birth in format YYYY-MM-DD: ");

            int year=0;
            boolean isInt = false;
            while (isInt != true) {
                try {
                    System.out.println("Please Enter year");
                    year = kb.nextInt();
                    isInt = true;
                    kb.nextLine();
                } catch (Exception e) {

                    kb.nextLine();
                    System.out.println("Please enter a number for Year!!!");
                }
            }
            int month=0;
            boolean isInt1 = false;
            while (isInt1 != true) {
                try {
                    System.out.println("Please Enter Day");

                    month = kb.nextInt();
                    isInt1 = true;
                    kb.nextLine();
                } catch (Exception e) {

                    kb.nextLine();
                    System.out.println("Please enter a number for Day!!!");

                }
            }
            int day=0;
            boolean isInt2 = false;
            while (isInt2 != true) {
                try {
                    System.out.println("Please Enter month 1 for January till 12 for December");
                    day = kb.nextInt();
                    isInt2 = true;
                    kb.nextLine();
                } catch (InputMismatchException | NumberFormatException e) {
                    kb.nextLine();
                    System.out.println("Please enter a number for Month!!!");

                }
            }

            LocalDate date = LocalDate.of(year,month,day);

            double rate=0;
            boolean isDouble = false;
            while (isDouble != true) {
                try {
                    System.out.println("\nEnter singer rate: ");
                    rate = kb.nextDouble();
                    isDouble = true;
                    kb.nextLine();
                } catch (InputMismatchException | NumberFormatException e) {

                    kb.nextLine();
                    System.out.println("Please enter a number for Rate!!!");
                }
            }

            System.out.println("\nEnter Singer genre: ");
            String genre = kb.nextLine();
            return new Singer(singerName,date,rate,genre);
        }catch(InputMismatchException | NumberFormatException e){
            System.out.print("Invalid input");
        }

       return null;
    }

    private boolean isIDExist(String id) {
        return PATTERN.matcher(id).find();
    }
}


       /*Feature 18 – “Communications Protocol Sequence Diagram”
        Detail all of the messages that pass between the client and server to implement all of the
        features of this CA. Draw this out in a diagram showing all of the data that is passed in each
        direction in the appropriate format.

        Feature 19 – “Open ended, your choice”
        Add an additional option of your choice to the client-side menu. This should result in input
        from the client, triggering a JSON formatted request to the server. The server will access the
        database using the relevant DAO and will respond to the client with the data in JSON
        format. For example you could add a menu item “Get summary data” which might return
        information such as the number of entities, their average CA mark and the standard
        deviation of the CA marks. This is just to give you an idea. You are free to implement any
        additional feature or features that make sense for your application */
