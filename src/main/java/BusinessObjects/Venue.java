package BusinessObjects;

import java.time.LocalTime;

public class Venue {
   private int id;
   private String name;
   private String location;
   private LocalTime time;

   public Venue(int id, String name, String location, LocalTime time) {
      this.id = id;
      this.name = name;
      this.location = location;
      this.time = time;
   }

   @Override
   public String toString() {
      return "Venue{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", location='" + location + '\'' +
              ", time=" + time +
              '}';
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getLocation() {
      return location;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public LocalTime getTime() {
      return time;
   }

   public void setTime(LocalTime time) {
      this.time = time;
   }
}
