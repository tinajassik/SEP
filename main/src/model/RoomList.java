package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A class containing a list of Room objects.
 * @author Dragos Cotaga
 * @version 1.0
 */

// Class: RoomList extends ArrayList and implements RoomListInterface and Serializable interfaces to store the list of rooms in the hotel
public class RoomList implements Serializable
    {
    private ArrayList<Room> rooms;

        /**
         * No-argument constructor initializing the RoomList
         */
    // Constructor: creates an empty list of rooms
    public RoomList() {
        rooms = new ArrayList<Room>();
    }

        /**
         * Adds a Room to the list.
         * @param room the room that will be added to the list
         */

    // Method: adds a room to the list of rooms if the room is not already in the list of rooms and returns true if the room is added successfully
    public void addRoom(Room room) {
        rooms.add(room);
    }

        /**
         * Removes a Room from the list.
         * @param room the room that will be removed
         */
    //Method: removes a room from the list of rooms
    public void removeRoom(Room room)
    {
        rooms.remove(room);
    }

    // Method: returns the list of rooms in the hotel as an array of rooms and returns null if the list of rooms is empty

        /**
         * Gets a Room object from the list.
         * @param index the position in the list of the Room object
         * @return the Room object at position index
         */
    public Room getRoom(int index) {
        return rooms.get(index);
    }

        /**
         * Returns how many Room objects are in the list.
         * @return the number of Room objects in the list
         */
    //Method: returns the size of the ArrayList rooms
    public int size()
    {
        return rooms.size();
    }

        /**
         * Returns a String representation of the RoomList.
         * @return a String containing information about all Room objects in the list- each Room object followed by a new line character
         */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < rooms.size(); i++) {
            str += rooms.get(i) + "\n";
        }
        return str;
    }
}
