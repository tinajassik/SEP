package model;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class containing a list of Guest objects
 * @author Andreea Asimine
 * @version 1.1
 */
public class GuestList implements Serializable
{
  //creating fields/attributes for class GuestList
  private ArrayList<Guest> guests;

  /**
   * No-argument constructor initializing the GuestList class.
   */
  //1 argument constructor
  public GuestList()
  {
    guests=new ArrayList<Guest>();
  }

  /**
   * Adds a Guest to the list.
   * @param guest the guest to add to the list
   */
  //addGuest() method that adds a new guest to the ArrayList
  public void addGuest(Guest guest)
  {
    guests.add(guest);
  }

  /**
   * Gets the guest who made the booking
   * @return the first guest on the list
   */
  //getFirstGuest() method that returns the first guest in the ArrayList(the person that booked the room)
  public Guest getFirstGuest()
  {
    return guests.get(0);
  }

  /**
   * Gets a Guest objects from position index from the list.
   * @param i the position of the Guest object in the list
   * @return  the Guest object at position index of one exists, else null
   */
  public Guest getGuest(int i) {
    if(i<guests.size())
    {
      return guests.get(i);
    }
    else
    {
      return null;
    }

  }

  /**
   * Gets how many Guest objects are in the list.
   * @return the number of the Guest objects in the list
   */
  public int size() {
    return guests.size();
  }

  /**
   * Gets a Guest object with the given first name and last name form the list.
   * @param firstName the first name of the Guest object
   * @param lastName the last name of the Guest object
   * @return the Guest object with the given first name and last name if one exists, else null
   */
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

  /**
   * Gets a list with all Guest objects
   * @return a list with Guest objects
   */
  //getAllGuests() method that returns the ArrayList with all the guests
  public ArrayList getAllGuests()
  {
    return guests;
  }

  /**
   * Returns a string representation of the GuestList
   * @return a String containing information about all Guest objects in the list - each Guest starting with "Guest: " and followed by a new line character
   */
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

  /**
   * Compares two
   * @param obj
   * @return
   */
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
