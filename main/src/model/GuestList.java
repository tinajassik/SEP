package model;
import java.io.Serializable;
import java.util.ArrayList;

public class GuestList implements Serializable
{
  //creating fields/attributes for class GuestList
  private ArrayList<Guest> guests;

  //1 argument constructor
  public GuestList()
  {
    guests=new ArrayList<Guest>();
  }

  //addGuest() method that adds a new guest to the ArrayList
  public void addGuest(Guest guest)
  {
    guests.add(guest);
  }

  //getFirstGuest() method that returns the first guest in the ArrayList(the person that booked the room)
  public Guest getFirstGuest()
  {
    return guests.get(0);
  }

  public Guest getGuest(int i) {
    return guests.get(i);
  }
  public int size() {
    return guests.size();
  }
  //getGuestByName() method that returns the guest with the matching name
  public Guest getGuestByName(String firstName, String lastName)
  {
    for (int i = 0; i < guests.size() - 1; i++)
    {
      if (firstName.equals(guests.get(i).getFirstName()) && lastName.equals(
          guests.get(i).getLastName()))
        return guests.get(i);
    }
    return null;
  }

  //getAllGuests() method that returns the ArrayList with all the guests
  public ArrayList getAllGuests()
  {
    return guests;
  }

  //toString() method used to convert the ArrayList guests into a string
  public String toString()
  {
    String s="";
    for (int i = 0; i < guests.size(); i++)
    {
      s+= "Guest: " + guests.get(i) + "\n";
    }
    return s;
  }

  //equals() method for checking if obj is the same object as GuestList
  public boolean equals(Object obj)
  {
    if (!(obj instanceof GuestList)) //check if obj is the same type
    {
      return false;
    }
    GuestList other = (GuestList) obj; //cast obj to this type

    //check if the fields are the same
    return guests.equals(other.guests);
  }

}
