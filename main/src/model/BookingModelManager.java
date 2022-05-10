package model;

import utilis.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager
{
  private String fileName;

  public BookingModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  public BookingList getAllStudents()
  {
    BookingList allBookings = new BookingList();

    try
    {
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allBookings;
  }

}
