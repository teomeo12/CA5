package org.example;

import java.util.ArrayList;
import java.util.List;
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
    public void start(){
        //Singer singer1 = new Singer();
        ArrayList<Singer> singersList = new ArrayList<>();
        initialize(singersList);
        display(singersList);

    }
    private void initialize(List list){
        list.add(new Singer("Mark Lanegan", LocalDate.parse("2007-12-03"),2000.00,"rock"));
        list.add(new Singer("Kylie Minogue",LocalDate.parse("2007-12-03"),3000.00,"pop"));
        list.add(new Singer("Declan Nerney",LocalDate.parse("2007-12-03"),4000.00,"country"));
        list.add(new Singer("Alice in Chains ",LocalDate.parse("2007-12-03"),30000.00,"Grunge"));
        list.add(new Singer("Mattiel",LocalDate.parse("2000-12-04"),14000.00,"pop"));
        list.add(new Singer("King of Leon",LocalDate.parse("1999-12-05"),34000.00,"rock"));
        list.add(new Singer("Royal Blood",LocalDate.parse("2007-12-03"),25000.00,"metal"));
        list.add(new Singer("Last Hope",LocalDate.parse("2007-12-03"),5000.00,"hard-core"));
        list.add(new Singer("Odd Crew",LocalDate.parse("2007-12-03"),10000.00,"metal"));
        list.add(new Singer("PJ Harvey",LocalDate.parse("2007-12-03"),20000.00,"pop-rock"));

    }
    public void display(List <Singer> singersList){
       // ,"Date of Birth","Rate","Genre"
        System.out.printf("%-20s %-20s %-10s %-20s","Name","Date of Birth","Rate","Genre");
        System.out.println("\n---------------------------------------------------------------");
        for (Singer singer : singersList ) {

            System.out.printf("\n%-20s %-20s %-10s %-20s",singer.getName(),singer.getDob(), singer.getRate(),singer.getGenre() );
          //  System.out.println(singer.getDob());
        }
        System.out.println("\n---------------------------------------------------------------");
    }
}
