package BusinessObjects;


import DAOs.MySqlSingerDao;
import DAOs.SingerDaoInterface;
import DTOs.Singer;
import Exceptions.DaoException;
import JsonLocalDateAdapter.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Server {
    private static final Scanner kb = new Scanner(System.in);
    private static final Gson gsonParser = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {

        SingerDaoInterface ISingerDao = new MySqlSingerDao();
        try {

            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

//                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
//                t.start();                                                  // and run it in its own thread

                Thread t1 = new Thread(new ClientHandler(socket, clientNumber, ISingerDao)); // create a new ClientHandler for the client,
                t1.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;
        SingerDaoInterface ISingerDao;

        public ClientHandler(Socket clientSocket, int clientNumber, SingerDaoInterface ISingerDao) {
            this.ISingerDao = ISingerDao;
            try {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();
                this.socketWriter = new PrintWriter(os, true); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            String[] tokens ;
            try {
                while ((message = socketReader.readLine()) != null) {
                    System.out.println("Server: (ClientHandler): Read command from client " + clientNumber + ": " + message);

                    if (message.startsWith("DISPLAY_SINGERS_BY_ID")) {
                         tokens = message.split(" ");
                        try {

                            int id = Integer.parseInt(tokens[1]);
                            String singersJSON1 = ISingerDao.findSingersByIDJSONServer(id);
                            System.out.println("Singer: " + singersJSON1);
                            socketWriter.println(singersJSON1);

                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number for ID!!!");
                        }
                    } else if (message.startsWith("DISPLAY_ALL_SINGERS")) {

                        String allSingers = ISingerDao.findAllSingersJSONServer();
                        System.out.println("Return JSON string of the Singer list");
                        socketWriter.println(allSingers);

                    }else if (message.startsWith("DISPLAY_SINGER_HIGHEST_RATE")) {

                        String singerHighestRate = (ISingerDao.findSingersHighestRateJSONServer());
                        System.out.println("Return JSON string of the Singer ");
                        socketWriter.println(singerHighestRate);

                    }else if (message.startsWith("DELETE_BY_ID")) {

                        try {
                             tokens = message.split(" ");
                            int id = Integer.parseInt(tokens[1]);
                            if(ISingerDao.findSingerById(id) !=null) {
                                ISingerDao.deleteSingerById(id);
                                socketWriter.println("Singer with ID "+"\""+ id +"\""+ " is deleted successfully!!!");
                            }
                            else{
                                socketWriter.println("Singer with id " +"\""+id+ "\""+" do not exist.");
                            }
                        }
                        catch( DaoException e )
                        {
                            e.printStackTrace();
                        }

                    } else if (message.startsWith("ADD_SINGER")) {
                         tokens = message.split(";");


                        Singer singer = gsonParser.fromJson(tokens[1], Singer.class);
                        ISingerDao.addSinger(singer);
                        socketWriter.println("Singer " +"\""+ singer.getName() +"\""+ " added successfully...");
                    } else {
                        socketWriter.println("Invalid request :(");
                    }
                }

                socket.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (DaoException e) {
                e.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }
    }

}
