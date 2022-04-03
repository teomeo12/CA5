package BusinessObjects;

import DAOs.MySqlSingerDao;
import DAOs.SingerDaoInterface;
import Exceptions.DaoException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDate;
import java.util.PriorityQueue;
import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Name: Teodor Donchev SD2a
 * Hello world!
 */
public class App {
    private static final String INPUT_MIS_MATCH = "Input is not a Number - Please enter number";
    public static void main(String[] args) throws IOException, DaoException {
        App app = new App();
        app.start();



    }
    public void start() throws IOException {

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Main-Menu
    public void displayMainMenu() throws IOException {

        final String MENU_ITEMS =
                "*------------------------------------------------*\n"
                        + "*         *** MAIN MENU OF OPTIONS ***          *\n"
                        + "*-----------------------------------------------*\n"
                        + "*  1. Display all Singers                       *\n"
                        + "*  2. Hash Map Retrieve                         *\n"
                        + "*  3. Tree Map Retrieve                         *\n"
                        + "*  4. PriorityQueue Sequence Simulation         *\n"
                        + "*  5. PriorityQueue Two-Field Comparison Demo   *\n"
                        + "*  -------------------------------------------  *\n"
                        + "*        Features from SQL data base            *\n"
                        + "*  -------------------------------------------  *\n"
                        + "*  6. Find all Singers from database            *\n"
                        + "*  7. Find  Singers by id from database         *\n"
                        + "*  8. Delete  Singers by id from database       *\n"
                        + "*  9. Insert  Singer into database              *\n"
                        + "*  10. List singers using a filter              *\n"
                        + "*  11. Retrieve all Entities as JSON String     *\n"
                        + "*  12. Find a SINGER by Key as JSON             *\n"
                        + "*  -------------------------------------------  *\n"
                        + "*  13. Exit                                     *\n"
                        + "*-----------------------------------------------*\n"
                        + "*              Enter Option [1,13]              *\n"
                        + "*-----------------------------------------------*";


        final int SINGERSDISPLAY = 1;
        final int HASHMAPRETRIEVE = 2;
        final int TREEMAPRETRIEVE = 3;
        final int PRIORITYQUEUE = 4;
        final int PRIORITYQUEUETWOFIELD = 5;
        final int FIND_ALL_SINGERS_DATABASE = 6;
        final int FIND_SINGERS_BY_ID_DATABASE = 7;
        final int DELETE_SINGERS_BY_ID_DATABASE = 8;
        final int INSERT_SINGERS_INTO_DATABASE = 9;
        final int LIST_SINGER_USING_FILTER = 10;
        final int RETRIEVE_SINGERS_AS_JSON = 11;
        final int FIND_SINGERS_BY_KEY_JSON = 12;
        final int EXIT = 13;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);


                ArrayList<Singer> singersList = new ArrayList<>();
                instantiateSingers(singersList);
                ArrayList<Venue> venueList = new ArrayList<>();

                SingerDaoInterface ISingerDao = new MySqlSingerDao();

                List<Singer> singers ;

                switch (option) {
                    // Feature-1
                    case SINGERSDISPLAY:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Display all Singers option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        //Feature 1

                        displayAllSingers(singersList);

                        break;
                    // Feature-2
                    case HASHMAPRETRIEVE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Retrieve singer by genre  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        hashmap(singersList);

                        break;
                    // Feature-3
                    case TREEMAPRETRIEVE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Get Singer Venue based on Venue   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        treeMap(singersList, venueList);

                        break;
                    // Feature-4
                    case PRIORITYQUEUE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##   PriorityQueue Sequence Simulation    ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        priorityQueueSimulation(singersList);

                        break;
                   // Feature-5
                    case PRIORITYQUEUETWOFIELD:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  PriorityQueue Two-Field Comparison   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        twoFieldPriorityQueue(singersList);

                        break;
                    // Feature-7
                    case FIND_ALL_SINGERS_DATABASE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Find all Singers from database   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                       // SingerDaoInterface ISingerDao = new MySqlSingerDao();

                        System.out.println("\nCall findAllSingers()");
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers");
                        else {
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer : singers)
                                System.out.println(singer.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.println("Press enter to continue...");
                        }

                        break;
                    // Feature-8
                    case FIND_SINGERS_BY_ID_DATABASE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Find Singers by ID from database   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() ){
                            System.out.println("There are no Singers in the list");

                        }
                        else {
                            System.out.println("\nAll singers from the list");
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer : singers)
                                System.out.println(singer.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");

                        }

                        System.out.println("\nCall findSingerById()");
                        Scanner sc =new Scanner(System.in);
                        boolean isID = false;
                        do  {
                            try {
                                System.out.println("Please choose singer id from the list: ");

                                int id = sc.nextInt();
                                isID = true;
                                Singer singersbyID = ISingerDao.findSingerById(id);

                                if( singersbyID == null ){
                                    System.out.println("There are no Singers with id "+id);
                                    System.out.println("Press Enter to continue...");
                                }

                                else {

                                    System.out.println("\n-------------------------------------------------------------------");
                                    System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                                    System.out.println("\n-------------------------------------------------------------------");
                                    System.out.println( singersbyID.displayAllSingers());
                                    System.out.println("\n-------------------------------------------------------------------");
                                    //sc.nextLine();
                                    System.out.println("Press Enter to continue...");
                                }

                            } catch (InputMismatchException e) {
                                sc.nextLine();
                                System.out.println("Please enter a number for ID!!!");

                            }
                        }while((isID != true));

                        break;
                    case DELETE_SINGERS_BY_ID_DATABASE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Delete Singers by ID from database   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers in the list");
                        else {
                            System.out.println("\nAll singers from the list");
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer : singers)
                                System.out.println(singer.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");

                        }

                        System.out.println("\nCall deleteSingerById()");

                        Scanner sc1 =new Scanner(System.in);
                        boolean isID1 = false;
                        do  {
                            try {
                                System.out.println("Please choose singer ID to be deleted: ");
                                int id = sc1.nextInt();
                                isID1 = true;

                                Singer deleteSingerID =  ISingerDao.findSingerById(id);
                                if(deleteSingerID ==null){
                                    System.out.println("There are no Singers with id "+id);
                                }else{
                                    System.out.println("\nAre you sure you want to delete singer: ");
                                    System.out.println("\n-------------------------------------------------------------------");
                                    System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                                    System.out.println("\n-------------------------------------------------------------------");
                                    System.out.println(deleteSingerID.displayAllSingers());
                                    System.out.println("-------------------------------------------------------------------");
                                    System.out.println("Please choose 1 for YES or 2 for NO");

                                    final int YES = 1;
                                    final int NO = 2;
                                    int chose = 0;
                                    boolean isNum = false;
                                    do {

                                        try {
                                            String inputID = keyboard.nextLine();
                                            chose = Integer.parseInt(inputID);
                                            isNum = true;

                                            switch (chose) {

                                                case YES:
                                                    ISingerDao.deleteSingerById(id);
                                                    System.out.println("Singer deleted!!!");
                                                    break;
                                                case NO:
                                                    System.out.println("Singer is NOT deleted!!!");

                                                    break;
                                                default:
                                                    System.out.print("Invalid option - please enter number in range");
                                                    break;
                                            }
                                            // keyboard.nextLine();
                                        } catch (InputMismatchException | NumberFormatException e) {
                                            // sc1.nextLine();
                                            System.out.println("Please enter a number 1 for YES or 2 for NO!!!");
                                        }
                                    } while (isNum != true);
                                }


                            } catch (InputMismatchException e) {
                               // sc1.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }while((isID1 != true));
                        System.out.println("Press Enter to continue...");

                        break;
                    case  INSERT_SINGERS_INTO_DATABASE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##            Add new Singer             ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers");
                        else {
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer : singers)
                                System.out.println(singer.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");

                        }

                        System.out.println("\nEnter Singer name: ");
                        String singerName = keyboard.nextLine();

                        System.out.println("\nEnter Singer date of birth in format YYYY-MM-DD: ");

                        int year=0;
                        boolean isInt = false;
                        while (isInt != true) {
                            try {
                                System.out.println("Please Enter year");
                                year = keyboard.nextInt();
                                isInt = true;
                                keyboard.nextLine();
                            } catch (Exception e) {

                                keyboard.nextLine();
                                System.out.println("Please enter a number for Year!!!");
                            }
                        }
                        int month=0;
                        boolean isInt1 = false;
                        while (isInt1 != true) {
                            try {
                                System.out.println("Please Enter month");
                                month = keyboard.nextInt();
                                isInt1 = true;
                                keyboard.nextLine();
                            } catch (Exception e) {

                                keyboard.nextLine();
                                System.out.println("Please enter a number for Month!!!");
                            }
                        }
                        int day=0;
                        boolean isInt2 = false;
                        while (isInt2 != true) {
                            try {
                                System.out.println("Please Enter Day");
                                day = keyboard.nextInt();
                                isInt2 = true;
                                keyboard.nextLine();
                            } catch (Exception e) {

                                keyboard.nextLine();
                                System.out.println("Please enter a number for Day!!!");
                            }
                        }

                         LocalDate date = LocalDate.of(year,month,day);

                        double rate=0;
                        boolean isDouble = false;
                        while (isDouble != true) {
                            try {
                                System.out.println("\nEnter singer rate: ");
                                 rate = keyboard.nextDouble();
                                isDouble = true;
                                keyboard.nextLine();
                            } catch (Exception e) {

                                keyboard.nextLine();
                                System.out.println("Please enter a number for Rate!!!");
                            }
                        }

                        System.out.println("\nEnter Singer genre: ");
                        String genre = keyboard.nextLine();

                        Singer singer =ISingerDao.addSinger(singerName,date,rate,genre);
                        if(singer != null){
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.println("New Singer \n"+ singer.displayAllSingers()+"\n is added to the list!!");
                            System.out.println("\n-------------------------------------------------------------------");
                        }else{
                            System.out.println("Singer is not added to the list");
                        }
                        System.out.println("\nList after the new Singer is added");

                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers");
                        else {
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer1 : singers)
                                System.out.println(singer1.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.println("Press Enter to continue...");
                        }

                        break;
                    case LIST_SINGER_USING_FILTER:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Filter Singers from database    ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers");
                        else {
                            System.out.println("Original List");
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer1 : singers)
                                System.out.println(singer1.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");
                        }

                        System.out.println("\n Filter Singers by date of birth");
                         singers = ISingerDao.filterAllSingers();
                        ComparatorDateOfBirth yearComparator = new ComparatorDateOfBirth();
                        Collections.sort( singers, yearComparator );
                        displayAllSingersFilter((ArrayList<Singer>) singers);
                        System.out.println("Press Enter to continue...");

                        break;
                    case RETRIEVE_SINGERS_AS_JSON:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  - Retrieve all Entities as JSON String    ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        //call the singer list
                        String singersJSON = ISingerDao.findAllSingersJSON();
                        System.out.println("Return JSON string of the Singer list");
                        System.out.println(singersJSON);
                        System.out.println("Press Enter to continue...");

                        break;
                    case FIND_SINGERS_BY_KEY_JSON:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  - Retrieve Singers as JSON String by ID    ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        //call the singer list
                        singers = ISingerDao.findAllSingers();

                        if( singers.isEmpty() )
                            System.out.println("There are no Singers");
                        else {
                            System.out.println("Original List");
                            System.out.println("\n-------------------------------------------------------------------");
                            System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
                            System.out.println("\n-------------------------------------------------------------------");
                            for (Singer singer1 : singers)
                                System.out.println(singer1.displayAllSingers());
                            System.out.println("\n-------------------------------------------------------------------");

                        }

                        Scanner sc2 =new Scanner(System.in);
                        boolean isID2 = false;
                        do  {
                            try {
                                System.out.println("Please choose singer id ");

                                int id = sc2.nextInt();

                               // if(id)

                                isID2 = true;
                                String singersJSON1 = ISingerDao.findSingersByIDJSON(id);
                                if( singersJSON1 == null ){
                                    System.out.println("There are no Singers with id ");
                                   // System.out.println("Press Enter to continue...");
                                    break;
                                }

                                else {
                                    System.out.println("Return JSON String of the chosen singer");
                                    System.out.println(singersJSON1);
                                    //sc.nextLine();
                                   // System.out.println("Press Enter to continue...");
                                }

                            } catch (InputMismatchException e) {
                                sc2.nextLine();
                                System.out.println("Please enter a number for ID!!!");
                            }
                        }while((isID2 != true));

                        System.out.println("Press Enter to continue...");

                        break;
                    case EXIT:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Exit Menu option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    default:
                        System.out.print("Invalid option - please enter number in range");
                        break;
                }
                keyboard.nextLine();
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            } catch (DaoException e) {
                e.printStackTrace();
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");
    }

    public void instantiateSingers(ArrayList<Singer> singersList) {

        singersList.add(new Singer(1, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2000, "rock"));
        singersList.add(new Singer(2, "Kylie Minogue", LocalDate.parse("1965-12-03"), 3000, "pop"));
        singersList.add(new Singer(3, "Declan Nerney", LocalDate.parse("1975-12-03"), 4000, "country"));
        singersList.add(new Singer(4, "Alice in Chains ", LocalDate.parse("1993-12-03"), 11000, "grunge"));
        singersList.add(new Singer(5, "Mattiel", LocalDate.parse("2000-12-04"), 10000, "pop"));
        singersList.add(new Singer(6, "King of Leon", LocalDate.parse("1999-12-05"), 9000, "rock"));
        singersList.add(new Singer(7, "Royal Blood", LocalDate.parse("1983-12-03"), 8000, "metal"));
        singersList.add(new Singer(8, "Last Hope", LocalDate.parse("1986-12-03"), 5000, "hard-core"));
        singersList.add(new Singer(9, "Odd Crew", LocalDate.parse("1993-12-03"), 6000, "metal"));
        singersList.add(new Singer(10, "PJ Harvey", LocalDate.parse("1974-12-03"), 7000, "pop-rock"));
        singersList.add(new Singer(11, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2100, "rock"));
        singersList.add(new Singer(12, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2300, "rock"));

    }

    public void instantiateVenues(ArrayList<Venue> venueList) {

        venueList.add(new Venue(1, "O2 arena", "Dublin", LocalTime.parse("22:30:00")));
        venueList.add(new Venue(2, "Live Music Hall", "Germany", LocalTime.parse("20:00:00")));
        venueList.add(new Venue(3, "O2 Forum Kentish Town", "UK", LocalTime.parse("21:30:00")));
        venueList.add(new Venue(4, "Wiener Stadthalle", "Austria", LocalTime.parse("22:00:00")));
        venueList.add(new Venue(5, "Arena Armeetz", "Bulgaria", LocalTime.parse("23:00:00")));
        venueList.add(new Venue(6, "Zenith Arena Lille ", "France", LocalTime.parse("19:30:00")));
        venueList.add(new Venue(7, "Arena Madrid", "Spain", LocalTime.parse("20:30:00")));
        venueList.add(new Venue(8, "Altice arena", "Portugal", LocalTime.parse("21:00:00")));
        venueList.add(new Venue(9, "Sydney Opera House", "Australia", LocalTime.parse("22:00:00")));
        venueList.add(new Venue(10, "Crescent Concert hall", "Drogheda", LocalTime.parse("21:00:00")));

    }

    //Feature 1
    public static ArrayList<Singer> displayAllSingers(ArrayList<Singer> singersList) {
        // ,"Date of Birth","Rate","Genre"
        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
        System.out.println("\n-------------------------------------------------------------------");
        for (Singer singer : singersList) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre());
        }
        System.out.println("\n-------------------------------------------------------------------");
        System.out.println("Press Enter to continue...");
        return singersList;
    }
    public static ArrayList<Singer> displayAllSingersFilter(ArrayList<Singer> singersList) {
        // ,"Date of Birth","Rate","Genre"
        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
        System.out.println("\n-------------------------------------------------------------------");
        for (Singer singer : singersList) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre());
        }
        System.out.println("\n-------------------------------------------------------------------");
        return singersList;
    }

    public static Singer displayOneSingers(Singer singer) {
        // ,"Date of Birth","Rate","Genre"
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.printf("%s %-5s %-20s %-20s %-10s %-8s %s","|", "Id", "Name", "Date of Birth", "Rate", "Genre","|");
        System.out.println("\n-----------------------------------------------------------------------");

        System.out.printf("%s %-5d %-20s %-20s %-10s %-8s %s\n","|", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre(),"|");

        System.out.println("-----------------------------------------------------------------------");
        return singer;
    }
    public static void hashmap(ArrayList<Singer> singersList)  // Hash Map (Singer => Book )
    {
        Scanner sc = new Scanner(System.in);
        Map<String, Singer> singerGenre = new HashMap<>();

        for (Singer s : singersList) {
            singerGenre.put(s.getGenre(), s);
        }
        Set<String> keySet = singerGenre.keySet();
        System.out.println("Choose Singer genre:");
        System.out.println("-------------------");
        System.out.println("|      Genres     |");
        System.out.println("-------------------");
        for (String genre : keySet) {

            System.out.printf("%-2s %-2s %-10s %-5s \n","|","•",genre," |" );
        }
        System.out.println("-------------------");


        try {
            String key = sc.next();
            if (singerGenre.containsKey(key)) {
                System.out.println("*****************************");
                System.out.println("Singer who plays " + key );
                displayOneSingers(singerGenre.get(key));
                System.out.println("Press Enter to continue...");
            } else {
                System.out.println("Singer who plays " + key + " does not exist.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Genre entered is not valid");
        }
    }

    public static void treeMap(ArrayList<Singer> singersList, ArrayList<Venue> venueList)  // TREE Map (Singer => Venue )
    {
        Singer s1 = new Singer(1, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2000.00, "Rock");
        Singer s2 = (new Singer(2, "Kylie Minogue", LocalDate.parse("1965-12-03"), 3000.00, "Pop"));
        Singer s3 = (new Singer(3, "Declan Nerney", LocalDate.parse("1975-12-03"), 4000.00, "Country"));
        Singer s4 = (new Singer(4, "Alice in Chains ", LocalDate.parse("1993-12-03"), 30000.00, "Grunge"));
        Singer s5 = (new Singer(5, "Mattiel", LocalDate.parse("2000-12-04"), 14000.00, "Pop"));
        Singer s6 = (new Singer(6, "King of Leon", LocalDate.parse("1999-12-05"), 34000.00, "Rock"));
        Singer s7 = (new Singer(7, "Royal Blood", LocalDate.parse("1983-12-03"), 25000.00, "Metal"));
        Singer s8 = (new Singer(8, "Last Hope", LocalDate.parse("1986-12-03"), 5000.00, "Hard-core"));
        Singer s9 = (new Singer(9, "Odd Crew", LocalDate.parse("1993-12-03"), 10000.00, "Metal"));
        Singer s10 = (new Singer(10, "PJ Harvey", LocalDate.parse("1974-12-03"), 20000.00, "Pop-rock"));

        Venue v1 = new Venue(1, "O2 arena", "Dublin", LocalTime.parse("22:30:00"));
        Venue v2 = new Venue(2, "Live Music Hall", "Germany", LocalTime.parse("20:00:00"));
        Venue v3 = new Venue(3, "O2 Forum Kentish Town", "UK", LocalTime.parse("21:30:00"));
        Venue v4 = new Venue(4, "Wiener Stadthalle", "Austria", LocalTime.parse("22:00:00"));
        Venue v5 = new Venue(5, "Arena Armeetz", "Bulgaria", LocalTime.parse("23:00:00"));
        Venue v6 = new Venue(6, "Zenith Arena Lille ", "France", LocalTime.parse("19:30:00"));
        Venue v7 = new Venue(7, "Arena Madrid", "Spain", LocalTime.parse("20:30:00"));
        Venue v8 = new Venue(8, "Altice arena", "Portugal", LocalTime.parse("21:00:00"));
        Venue v9 = new Venue(9, "Sydney Opera House", "Australia", LocalTime.parse("22:00:00"));
        Venue v10 = new Venue(10, "Crescent Concert hall", "Drogheda", LocalTime.parse("21:00:00"));

        Map<Singer, Venue> singerVenue = new TreeMap<>(new ComparatorSingerName());

//        for (Singer s : singersList) {
//            for(Venue v:venueList ){
//                singerVenue.put(s,v1);
//                singerVenue.put(s,v2);
//            }
//        }

        singerVenue.put(s1, v1);
        singerVenue.put(s2, v2);
        singerVenue.put(s3, v3);
        singerVenue.put(s4, v4);
        singerVenue.put(s5, v5);
        singerVenue.put(s6, v6);
        singerVenue.put(s7, v7);
        singerVenue.put(s8, v8);
        singerVenue.put(s9, v9);
        singerVenue.put(s10, v10);


        System.out.println("\nTree Map: [ Singer -> Venue ]");
        // for each Entry in the set of all entries
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.printf("%s %-8s %-20s %-25s %-10s %s %s","|", "Singer", "Name", "Venue", "Location", "Start Time","|");
        System.out.println("\n---------------------------------------------------------------------------------");
        for (Map.Entry<Singer, Venue> entry : singerVenue.entrySet()) {
            Singer singer = entry.getKey();
            Venue venue = entry.getValue();
            System.out.printf("%-3s%-8s %-20s %-25s %-10s %s %-3s %s\n","|", singer.getId() , singer.getName() , venue.getName(),venue.getLocation(),venue.getTime(),"pm","|");

           // System.out.println(singer.getId()  + singer.getName() + ", sings at:  " + "\"" + venue.getName() + "\"" + " in " + venue.getLocation() + " ,time: " + venue.getTime());
        }
        System.out.println("---------------------------------------------------------------------------------");

        System.out.println("Press Enter to continue...");

    }
    public void displayPriorityQueue(PriorityQueue<Singer> queue) {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.printf("%s %-5s %-20s %-20s %-10s %-8s %s","|", "Id", "Name", "Date of Birth", "Rate", "Genre","|");
        System.out.println("\n-----------------------------------------------------------------------");
        for (Singer singer : queue) {
            System.out.printf("%s %-5d %-20s %-20s %-10s %-8s %s\n","|", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre(),"|");
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Press Enter to continue...");
    }

    public void priorityQueueSimulation( ArrayList<Singer> singersList){
        Scanner keyboard = new Scanner(System.in);
        PriorityQueue<Singer> queue = new PriorityQueue<>(new ComparatorSingerRate(SortType.Ascending));

        //add two third-priority elements
        System.out.println("\n*******************************************");
        System.out.println("Add two third-priority elements to the queue");
        System.out.println("*********************************************");

        queue.add(singersList.get(2));
        queue.add(singersList.get(3));
        System.out.println("Display queue after add two third-priority elements");
        displayPriorityQueue(queue);


        // add two second-priority level items
        System.out.println("\n*******************************************");
        System.out.println("Add two second-priority elements to the queue");
        System.out.println("*********************************************");

        queue.add(singersList.get(4));
        queue.add(singersList.get(5));

        System.out.println("Display queue after add two second-priority elements");
        displayPriorityQueue(queue);

        //remove and display one element
        System.out.println("\n***********************************");
        System.out.println("Remove one element form the queue");
        System.out.println("************************************");
        System.out.println("Please choose the ID of the singer you want to delete: ");

        boolean isNum1 = false;
        while (isNum1 != true) {
            try {
                int id = keyboard.nextInt();
                isNum1 = true;
                System.out.println("You choose singer with id "+id);

                if(queue.contains(singersList.get(id-1))){
                    displayOneSingers(singersList.get(id-1));
                    queue.remove(singersList.get(id-1));

                }else{
                    System.out.println("There is no such a Singer in the queue ");
                }

                break;
            } catch (InputMismatchException e) {
               // keyboard.nextLine();
                System.out.println("Please enter a number for ID!!!");
            }
        }

        System.out.println("\n******************************");
        System.out.println("Priority queue after deletion");
        System.out.println("******************************");
        displayPriorityQueue(queue);
        //add one top-priority element

        System.out.println("\n***********************************");
        System.out.println("Add one top element to the queue");
        System.out.println("************************************");
       // new Singer(11, "Panican whyasker", LocalDate.parse("1984-08-03"), 1500, "rock")
        queue.offer(singersList.get(1));

        System.out.println("The new element added to the Priority Queue is ");
        displayOneSingers(singersList.get(1));

        System.out.println("\n******************************");
        System.out.println("Priority queue after insertion");
        System.out.println("********************************");

        displayPriorityQueue(queue);

        // remove and display all elements in priority order
        System.out.println("\n**********************************************");
        System.out.println("Remove the element by rate order from the queue");
        System.out.println("************************************************\n");
        int remove=0;

        System.out.println("------------------------------------------------------------------------------------------------");
        while (!queue.isEmpty()) {
            remove++;
            System.out.println("Remove "+ remove+" : "+ queue.remove());
        }
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.println("Press Enter to continue...");

    }
    public void displayQueue(PriorityQueue<Singer> queue)
    {
        // ,"Date of Birth","Rate","Genre"
        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
        System.out.println("\n-------------------------------------------------------------------");
        for (Singer singer : queue) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre());
        }
        System.out.println("\n-------------------------------------------------------------------");


    }
    public void twoFieldPriorityQueue(ArrayList<Singer> singerList) {

      //  PriorityQueue<Singer> twoFieldPriorityQueue = new PriorityQueue<>(new ComparatorNameRate(SortType.Ascending));
       PriorityQueue<Singer> twoFieldPriorityQueue = new PriorityQueue<>(new ComparatorSingerName());
       // PriorityQueue<Singer> twoFieldPriorityQueue = new PriorityQueue<>(new ComparatorDateOfBirth());

        System.out.println("\nDisplay singerList arraylist");

        displayAllSingers(singerList);

        System.out.println("\nAdd elements to priority queue");
        for (Singer s : singerList) {
            twoFieldPriorityQueue.add(s);
        }

        System.out.println("\nDisplay twoFieldPriority queue");

        displayPriorityQueue(twoFieldPriorityQueue);


    }

}


