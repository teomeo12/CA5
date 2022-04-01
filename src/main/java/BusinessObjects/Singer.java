package BusinessObjects;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Singer implements Comparable<Singer>{
    private int id;
     String name;
    private LocalDate dob;
    private  double rate;
    private String genre;


    public Singer(int id, String name, LocalDate dob, double rate, String genre) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.rate = rate;
        this.genre = genre;
    }
    public Singer( String name, LocalDate dob, double rate, String genre) {

        this.name = name;
        this.dob = dob;
        this.rate = rate;
        this.genre = genre;
    }


    public int getId() {        return id;    }
    public void setId(int id) {        this.id = id;    }
    public String getName() {        return name;    }
    public void setName(String name) {        this.name = name;    }
    public LocalDate getDob() {     return dob;    }
    public void setDob(LocalDate dob) {        this.dob = dob;    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Singer singer = (Singer) o;
        return id == singer.id && Double.compare(singer.rate, rate) == 0 && name.equals(singer.name) && dob.equals(singer.dob) && genre.equals(singer.genre);
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", rate=" + rate +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dob, rate, genre);
    }

    @Override
    public int compareTo(Singer o)
    {
        boolean name =
                this.getName().equalsIgnoreCase(o.getName());

        if(name) // Both first and last names are the same
        {
            //so, compare based on date of birth
            return this.getDob().compareTo(o.getDob());
        }

        return (int) (this.getRate() - o.getRate());
    }

    public String displayAllSingers() {

        return String.format("%-5d %-20s %-20s %-10s %-20s", getId(), getName(), getDob(), getRate(), getGenre());


    }
}
