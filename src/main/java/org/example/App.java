package org.example;

import java.time.LocalTime;
import java.util.*;
import java.time.LocalDate;

/**
 * Name: Teodor Donchev SD2a
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        App app = new App();
        app.start();


    }
    public void start() {
        treeMap();
        hashmap();

    }
        public static void hashmap()  // TREE Map (Student => Book )
        {
        ArrayList<Singer> singersList = new ArrayList<>();
        ArrayList<Venue> venuetList = new ArrayList<>();

        display(singersList);


        Singer s1 = new Singer(1,"Mark Lanegan", LocalDate.parse("2007-12-03"),2000.00,"rock");
        Singer s2 =(new Singer(2,"Kylie Minogue",LocalDate.parse("2007-12-03"),3000.00,"pop"));
        Singer s3 =(new Singer(3,"Declan Nerney",LocalDate.parse("2007-12-03"),4000.00,"country"));
        Singer s4 =(new Singer(4,"Alice in Chains ",LocalDate.parse("2007-12-03"),30000.00,"Grunge"));
        Singer s5 =(new Singer(5,"Mattiel",LocalDate.parse("2000-12-04"),14000.00,"pop"));
        Singer s6 =(new Singer(6,"King of Leon",LocalDate.parse("1999-12-05"),34000.00,"rock"));
        Singer s7 =(new Singer(7,"Royal Blood",LocalDate.parse("2007-12-03"),25000.00,"metal"));
        Singer s8 =(new Singer(8,"Last Hope",LocalDate.parse("2007-12-03"),5000.00,"hard-core"));
        Singer s9 =(new Singer(9,"Odd Crew",LocalDate.parse("2007-12-03"),10000.00,"metal"));
        Singer s10 =(new Singer(10,"PJ Harvey",LocalDate.parse("2007-12-03"),20000.00,"pop-rock"));

        singersList.add(s1);
        singersList.add(s2);
        singersList.add(s3);
        singersList.add(s4);
        singersList.add(s5);
        singersList.add(s6);
        singersList.add(s7);
        singersList.add(s8);
        singersList.add(s9);
        singersList.add(s10);

        Venue v1 = new Venue(1,"O2 arena","Dublin", LocalTime.parse("22:30:00"));
        Venue v2 =new Venue(2,"Live Music Hall","Germany", LocalTime.parse("20:00:00"));
        Venue v3 =new Venue(3,"O2 Forum Kentish Town","UK", LocalTime.parse("21:30:00"));
        Venue v4 =new Venue(4,"Wiener Stadthalle","Austria", LocalTime.parse("22:00:00"));
        Venue v5 =new Venue(5,"Arena Armeetz","Bulgaria", LocalTime.parse("23:00:00"));
        Venue v6 =new Venue(6,"Zenith Arena Lille ","France", LocalTime.parse("19:30:00"));
        Venue v7 =new Venue(7,"Arena Madrid","Spain", LocalTime.parse("20:30:00"));
        Venue v8 =new Venue(8,"Altice arena","Portugal", LocalTime.parse("21:00:00"));
        Venue v9 =new Venue(9,"Sydney Opera House","Australia", LocalTime.parse("22:00:00"));
        Venue v10 =new Venue(10,"Crescent Concert hall","Drogheda", LocalTime.parse("21:00:00"));

        venuetList.add(v1);
        venuetList.add(v2);
        venuetList.add(v3);
        venuetList.add(v4);
        venuetList.add(v5);
        venuetList.add(v6);
        venuetList.add(v7);
        venuetList.add(v8);
        venuetList.add(v9);
        venuetList.add(v10);

        Map<Singer, Venue> singerVenue = new HashMap<>();

        singerVenue.put(s1,v1);
        singerVenue.put(s2,v2);
            singerVenue.put(s3,v3);
            singerVenue.put(s4,v4);
            singerVenue.put(s5,v5);
            singerVenue.put(s6,v6);
            singerVenue.put(s7,v7);
            singerVenue.put(s8,v8);
            singerVenue.put(s9,v9);
            singerVenue.put(s10,v10);

        System.out.println("Map: [ Singer -> Venue ]");
        // for each Entry in the set of all entries
        for (Map.Entry<Singer, Venue> entry : singerVenue.entrySet()) {
            Singer singer = entry.getKey();
            Venue venue = entry.getValue();
            System.out.println("Singer: " + singer.getName() + ", sings at " + venue.getLocation() +" ,time: "+ venue.getTime());
        }


    }
    public static void display(List <Singer> singersList){
        // ,"Date of Birth","Rate","Genre"
        System.out.printf("%-5s %-20s %-20s %-10s %-20s","id","Name","Date of Birth","Rate","Genre");
        System.out.println("\n---------------------------------------------------------------");
        for (Singer singer : singersList ) {

            System.out.printf("\n%-5d %-20s %-20s %-10s %-20s",singer.getId(),singer.getName(),singer.getDob(), singer.getRate(),singer.getGenre() );
            //  System.out.println(singer.getDob());
        }
        System.out.println("\n---------------------------------------------------------------");
    }

    public static void treeMap()  // TREE Map (Student => Book )
    {
        System.out.println("Tree map");
        // Student class is used as the Key in this TreeMap,
        // so, Student must implement Comparable or
        // a Comparator must be supplied to the Map on construction
        Singer s1 = new Singer(1,"Mark Lanegan", LocalDate.parse("2007-12-03"),2000.00,"rock");
        Singer s2 =(new Singer(2,"Kylie Minogue",LocalDate.parse("2007-12-03"),3000.00,"pop"));
        Singer s3 =(new Singer(3,"Declan Nerney",LocalDate.parse("2007-12-03"),4000.00,"country"));
        Singer s4 =(new Singer(4,"Alice in Chains ",LocalDate.parse("2007-12-03"),30000.00,"Grunge"));
        Singer s5 =(new Singer(5,"Mattiel",LocalDate.parse("2000-12-04"),14000.00,"pop"));
        Singer s6 =(new Singer(6,"King of Leon",LocalDate.parse("1999-12-05"),34000.00,"rock"));
        Singer s7 =(new Singer(7,"Royal Blood",LocalDate.parse("2007-12-03"),25000.00,"metal"));
        Singer s8 =(new Singer(8,"Last Hope",LocalDate.parse("2007-12-03"),5000.00,"hard-core"));
        Singer s9 =(new Singer(9,"Odd Crew",LocalDate.parse("2007-12-03"),10000.00,"metal"));
        Singer s10 =(new Singer(10,"PJ Harvey",LocalDate.parse("2007-12-03"),20000.00,"pop-rock"));

        Venue v1 = new Venue(1,"O2 arena","Dublin", LocalTime.parse("22:30:00"));
        Venue v2 =new Venue(2,"Live Music Hall","Germany", LocalTime.parse("20:00:00"));
        Venue v3 =new Venue(3,"O2 Forum Kentish Town","UK", LocalTime.parse("21:30:00"));
        Venue v4 =new Venue(4,"Wiener Stadthalle","Austria", LocalTime.parse("22:00:00"));
        Venue v5 =new Venue(5,"Arena Armeetz","Bulgaria", LocalTime.parse("23:00:00"));
        Venue v6 =new Venue(6,"Zenith Arena Lille ","France", LocalTime.parse("19:30:00"));
        Venue v7 =new Venue(7,"Arena Madrid","Spain", LocalTime.parse("20:30:00"));
        Venue v8 =new Venue(8,"Altice arena","Portugal", LocalTime.parse("21:00:00"));
        Venue v9 =new Venue(9,"Sydney Opera House","Australia", LocalTime.parse("22:00:00"));
        Venue v10 =new Venue(10,"Crescent Concert hall","Drogheda", LocalTime.parse("21:00:00"));

      Map<Singer, Venue> singerVenue = new TreeMap<>(new ComparatorSingerName());
        singerVenue.put(s1,v1);
        singerVenue.put(s2,v2);
        singerVenue.put(s3,v3);
        singerVenue.put(s4,v4);
        singerVenue.put(s5,v5);
        singerVenue.put(s6,v6);
        singerVenue.put(s7,v7);
        singerVenue.put(s8,v8);
        singerVenue.put(s9,v9);
        singerVenue.put(s10,v10);

    }


}
