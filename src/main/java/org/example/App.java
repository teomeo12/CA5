package org.example;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println( "Hello World!" );
        System.out.println("Something  fd");
        ArrayList<String> nameList =new ArrayList<>();
        initialize(nameList);

    }
    private void initialize(List list){
        list.add(new String("Ben"));
        list.add(new String("Jerry"));

    }
    public void display(){

    }
}
