package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * RoomList class is a list of rooms.
 *
 * @author Dragos Cotaga
 * @version 1.0
 * @see Room
 * @see RoomList
 * @since 1.0
 */

// Class: RoomList extends ArrayList and implements RoomListInterface and Serializable interfaces to store the list of rooms in the hotel
public class RoomList implements Serializable
    {
    private ArrayList<Room> rooms;

    // Constructor: creates an empty list of rooms
    public RoomList() {
        rooms = new ArrayList<Room>();
    }

    // Method: adds a room to the list of rooms if the room is not already in the list of rooms and returns true if the room is added successfully
    public void addRoom(Room room) {
        rooms.add(room);
    }

    //Method: removes a room from the list of rooms
    public void removeRoom(Room room)
    {
        rooms.remove(room);
    }

    // Method: returns the list of rooms in the hotel as an array of rooms and returns null if the list of rooms is empty
    public Room getRoom(int index) {
        return rooms.get(index);
    }

    //Method: returns the size of the ArrayList rooms
    public int size()
    {
        return rooms.size();
    }
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < rooms.size(); i++) {
            str += rooms.get(i) + "\n";
        }
        return str;
    }
}
