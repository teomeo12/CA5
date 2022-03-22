package BusinessObjects;

import java.io.IOException;
import java.time.LocalTime;
import java.util.*;
import java.time.LocalDate;
import java.util.PriorityQueue;

/**
 * Name: Teodor Donchev SD2a
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start();

    }

    public void start() throws IOException {

        singerRateComparator();

        try {
            displayMainMenu();        // User Interface - Menu
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void displayMainMenu() throws IOException {

        final String MENU_ITEMS =
                "*----------------------------------*\n"
                        + "*   *** MAIN MENU OF OPTIONS ***   *\n"
                        + "*----------------------------------*\n"
                        + "*  1. Display all Singers           *\n"
                        + "*  2. Hash Map Retrieve             *\n"
                        + "*  3. Tree Map Retrieve             *\n"
                        + "*  4. Exit                         *\n"
                        + "*----------------------------------*\n"
                        + "*        Enter Option [1,4]        *\n"
                        + "*----------------------------------*";


        final int SINGERSDISPLAY = 1;
        final int HASHMAPRETRIEVE = 2;
        final int TREEMAPRETRIEVE = 3;
        final int EXIT = 4;

        Scanner keyboard = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("\n" + MENU_ITEMS);
            try {
                String usersInput = keyboard.nextLine();
                option = Integer.parseInt(usersInput);
                switch (option) {
                    case SINGERSDISPLAY:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Display all Singers option chosen  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        //Feature 1
                        ArrayList<Singer> singersList = new ArrayList<Singer>();

                        instantiateSingers(singersList);
                        displayAllSingers(singersList);
                        break;
                    case HASHMAPRETRIEVE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ## Retrieve singer by genre  ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        ArrayList<Singer> singersList1 = new ArrayList<>();
                        instantiateSingers(singersList1);
                        hashmap(singersList1);

                        break;
                    case TREEMAPRETRIEVE:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("~ ##  Get Singer Venue   ##~");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        ArrayList<Singer> singersList2 = new ArrayList<>();
                        ArrayList<Venue> venueList = new ArrayList<>();
                        instantiateSingers(singersList2);
                        instantiateVenues(venueList);
                        treeMap(singersList2,venueList);

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

            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("Invalid option - please enter number in range");
            }
        } while (option != EXIT);

        System.out.println("\nExiting Main Menu, goodbye.");

    }

    public void instantiateSingers(ArrayList<Singer> singersList) {

        singersList.add(new Singer(1, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2000.00, "rock"));
        singersList.add(new Singer(2, "Kylie Minogue", LocalDate.parse("1965-12-03"), 3000.00, "pop"));
        singersList.add(new Singer(3, "Declan Nerney", LocalDate.parse("1975-12-03"), 4000.00, "country"));
        singersList.add(new Singer(4, "Alice in Chains ", LocalDate.parse("1993-12-03"), 30000.00, "grunge"));
        singersList.add(new Singer(5, "Mattiel", LocalDate.parse("2000-12-04"), 14000.00, "pop"));
        singersList.add(new Singer(6, "King of Leon", LocalDate.parse("1999-12-05"), 34000.00, "rock"));
        singersList.add(new Singer(7, "Royal Blood", LocalDate.parse("1983-12-03"), 25000.00, "metal"));
        singersList.add(new Singer(8, "Last Hope", LocalDate.parse("1986-12-03"), 5000.00, "hard-core"));
        singersList.add(new Singer(9, "Odd Crew", LocalDate.parse("1993-12-03"), 10000.00, "metal"));
        singersList.add(new Singer(10, "PJ Harvey", LocalDate.parse("1974-12-03"), 20000.00, "pop-rock"));
    }
    public void instantiateVenues(ArrayList<Venue> venueList) {

        venueList.add (new Venue(1, "O2 arena", "Dublin", LocalTime.parse("22:30:00")));
        venueList.add ( new Venue(2, "Live Music Hall", "Germany", LocalTime.parse("20:00:00")));
        venueList.add (new Venue(3, "O2 Forum Kentish Town", "UK", LocalTime.parse("21:30:00")));
        venueList.add (new Venue(4, "Wiener Stadthalle", "Austria", LocalTime.parse("22:00:00")));
        venueList.add (new Venue(5, "Arena Armeetz", "Bulgaria", LocalTime.parse("23:00:00")));
        venueList.add ( new Venue(6, "Zenith Arena Lille ", "France", LocalTime.parse("19:30:00")));
        venueList.add ( new Venue(7, "Arena Madrid", "Spain", LocalTime.parse("20:30:00")));
        venueList.add ( new Venue(8, "Altice arena", "Portugal", LocalTime.parse("21:00:00")));
        venueList.add (new Venue(9, "Sydney Opera House", "Australia", LocalTime.parse("22:00:00")));
        venueList.add ( new Venue(10, "Crescent Concert hall", "Drogheda", LocalTime.parse("21:00:00")));

    }

    //Feature 1
    public void displayAllSingers(ArrayList<Singer> singersList) {
        // ,"Date of Birth","Rate","Genre"
        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
        System.out.println("\n-------------------------------------------------------------------");
        for (Singer singer : singersList) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre());
        }
        System.out.println("\n-------------------------------------------------------------------");
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
        for (String genre : keySet) {
            System.out.print(genre + ", ");
        }
        try {
            String key = sc.next();
            if (singerGenre.containsKey(key)) {
                System.out.println("Singer who plays " + key + " - " + singerGenre.get(key));
            } else {
                System.out.println("Singer who plays " + key + " does not exist.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Genre entered is not valid");
        }
    }
    public static void treeMap(ArrayList<Singer> singersList,ArrayList<Venue> venueList)  // TREE Map (Student => Book )
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

        System.out.println("\n-------------------------------------------------------------------");
        System.out.printf("%-5s %-20s %-20s %-10s %-20s", "Id", "Name", "Date of Birth", "Rate", "Genre");
        System.out.println("\n-------------------------------------------------------------------");
        for (Singer singer : singersList) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s", singer.getId(), singer.getName(), singer.getDob(), singer.getRate(), singer.getGenre());
        }
        System.out.println("\n-------------------------------------------------------------------");


        System.out.println("\nTree Map: [ Singer -> Venue ]");
        // for each Entry in the set of all entries
        System.out.println("=============================================");
        for (Map.Entry<Singer, Venue> entry : singerVenue.entrySet()) {
            Singer singer = entry.getKey();
            Venue venue = entry.getValue();
            System.out.println(singer.getId()+" Singer: " + singer.getName() + ", sings at:  "+"\""+venue.getName()+"\""+" in " + venue.getLocation() + " ,time: " + venue.getTime());
        }
        System.out.println("=============================================");

    }

    public static void singerRateComparator() {
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

        PriorityQueue<Singer> queue = new PriorityQueue<>(new ComparatorSingerRate(SortType.Ascending));
        queue.add(s1);
        queue.add(s2);
        queue.add(s3);
        queue.add(s4);
        queue.add(s5);
        queue.add(s6);
        queue.add(s7);
        queue.add(s8);
        queue.add(s9);
        queue.add(s10);

        System.out.println("\nThe first element form the queue");
        System.out.println(queue.peek());
        System.out.println("\nRemove one element form the queue");
        System.out.println("Remove :" + s4 + " from the queue: " + queue.remove(s4));

        // queue.add();

        System.out.println("\nRemove all element in order of Rate Priority:");
        // remove
        System.out.println("=============================================");

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());
        }
        System.out.println("=============================================");

        if (!queue.isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            System.out.println(queue);
        }


    }

}


//Hash map


// ArrayList<Singer> singersList = new ArrayList<>();
// ArrayList<Venue> venuetList = new ArrayList<>();

//
//        Singer s1 = new Singer(1, "Mark Lanegan", LocalDate.parse("1954-12-03"), 2000.00, "Rock");
//        Singer s2 = (new Singer(2, "Kylie Minogue", LocalDate.parse("1965-12-03"), 3000.00, "Pop"));
//        Singer s3 = (new Singer(3, "Declan Nerney", LocalDate.parse("1975-12-03"), 4000.00, "Country"));
//        Singer s4 = (new Singer(4, "Alice in Chains ", LocalDate.parse("1993-12-03"), 30000.00, "Grunge"));
//        Singer s5 = (new Singer(5, "Mattiel", LocalDate.parse("2000-12-04"), 14000.00, "Pop"));
//        Singer s6 = (new Singer(6, "King of Leon", LocalDate.parse("1999-12-05"), 34000.00, "Rock"));
//        Singer s7 = (new Singer(7, "Royal Blood", LocalDate.parse("1983-12-03"), 25000.00, "Metal"));
//        Singer s8 = (new Singer(8, "Last Hope", LocalDate.parse("1986-12-03"), 5000.00, "Hard-core"));
//        Singer s9 = (new Singer(9, "Odd Crew", LocalDate.parse("1993-12-03"), 10000.00, "Metal"));
//        Singer s10 = (new Singer(10, "PJ Harvey", LocalDate.parse("1974-12-03"), 20000.00, "Pop-rock"));
//
//        singersList.add(s1);
//        singersList.add(s2);
//        singersList.add(s3);
//        singersList.add(s4);
//        singersList.add(s5);
//        singersList.add(s6);
//        singersList.add(s7);
//        singersList.add(s8);
//        singersList.add(s9);
//        singersList.add(s10);

//  display(singersList);

//        Venue v1 = new Venue(1, "O2 arena", "Dublin", LocalTime.parse("22:30:00"));
//        Venue v2 = new Venue(2, "Live Music Hall", "Germany", LocalTime.parse("20:00:00"));
//        Venue v3 = new Venue(3, "O2 Forum Kentish Town", "UK", LocalTime.parse("21:30:00"));
//        Venue v4 = new Venue(4, "Wiener Stadthalle", "Austria", LocalTime.parse("22:00:00"));
//        Venue v5 = new Venue(5, "Arena Armeetz", "Bulgaria", LocalTime.parse("23:00:00"));
//        Venue v6 = new Venue(6, "Zenith Arena Lille ", "France", LocalTime.parse("19:30:00"));
//        Venue v7 = new Venue(7, "Arena Madrid", "Spain", LocalTime.parse("20:30:00"));
//        Venue v8 = new Venue(8, "Altice arena", "Portugal", LocalTime.parse("21:00:00"));
//        Venue v9 = new Venue(9, "Sydney Opera House", "Australia", LocalTime.parse("22:00:00"));
//        Venue v10 = new Venue(10, "Crescent Concert hall", "Drogheda", LocalTime.parse("21:00:00"));
//
//        venuetList.add(v1);
//        venuetList.add(v2);
//        venuetList.add(v3);
//        venuetList.add(v4);
//        venuetList.add(v5);
//        venuetList.add(v6);
//        venuetList.add(v7);
//        venuetList.add(v8);
//        venuetList.add(v9);
//        venuetList.add(v10);

//Map<Singer, Venue> singerVenue = new HashMap<>();
//
//        singerVenue.put(s1, v1);
//        singerVenue.put(s2, v2);
//        singerVenue.put(s3, v3);
//        singerVenue.put(s4, v4);
//        singerVenue.put(s5, v5);
//        singerVenue.put(s6, v6);
//        singerVenue.put(s7, v7);
//        singerVenue.put(s8, v8);
//        singerVenue.put(s9, v9);
//        singerVenue.put(s10, v10);

//        System.out.println("Hash map: [ Singer -> Venue ]");
//                // for each Entry in the set of all entries
//                System.out.println("=============================================");
//                for (Map.Entry<Singer, Venue> entry : singerVenue.entrySet()) {
//        Singer singer = entry.getKey();
//        Venue venue = entry.getValue();
//        System.out.println("Singer: " + singer.getName() + ", sings at " + venue.getLocation() + " ,time: " + venue.getTime());
//        }

