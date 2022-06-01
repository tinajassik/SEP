package utilis;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class responsible for reading and writing files
 * @author Allan Henriksen
 */

public class MyFileHandler
{
  /**
   * Reads all lines from the file with the given file name and returns it as a String[]
   * @param fileName the name of the file that will be read
   * @return all lines from the file with the given file name as a String[]
   * @throws FileNotFoundException
   */
  public static String[] readArrayFromTextFile(String fileName)
      throws FileNotFoundException
  {
    Scanner readFromFile = null;
    ArrayList<String> strs = new ArrayList<String>();

    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new Scanner(fileInStream);

      while (readFromFile.hasNext())
      {
        strs.add(readFromFile.nextLine());
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        readFromFile.close();
      }
    }

    String[] strsArray = new String[strs.size()];
    return strs.toArray(strsArray);
  }

  /**
   * Writes the objects in the given array to a file with the given file name.
   * @param fileName the name of the file that will be written
   * @param objs the array of objects that will be written to a file
   * @throws FileNotFoundException
   * @throws IOException
   */
  public static void writeArrayToBinaryFile(String fileName, Object[] objs)
      throws FileNotFoundException, IOException
  {
    ObjectOutputStream writeToFile = null;

    try
    {
      FileOutputStream fileOutStream = new FileOutputStream(fileName);
      writeToFile = new ObjectOutputStream(fileOutStream);

      for (int i = 0; i < objs.length; i++)
      {
        writeToFile.writeObject(objs[i]);
      }
    }
    finally
    {
      if (writeToFile != null)
      {
        try
        {
          writeToFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }
  }

  /**
   * Reads all objects from the file with the given file name and returns it as an Object[].
   * @param fileName the name of the file that will be read
   * @return array of objects read from the file
   * @throws FileNotFoundException
   * @throws IOException
   * @throws ClassNotFoundException
   */

  public static Object[] readArrayFromBinaryFile(String fileName)
      throws FileNotFoundException, IOException, ClassNotFoundException
  {
    ArrayList<Object> objs = new ArrayList<Object>();

    ObjectInputStream readFromFile = null;
    try
    {
      FileInputStream fileInStream = new FileInputStream(fileName);
      readFromFile = new ObjectInputStream(fileInStream);
      while (true)
      {
        try
        {
          objs.add(readFromFile.readObject());
        }
        catch (EOFException eof)
        {
          break;
        }
      }
    }
    finally
    {
      if (readFromFile != null)
      {
        try
        {
          readFromFile.close();
        }
        catch (IOException e)
        {
          System.out.println("IO Error closing file " + fileName);
        }
      }
    }

    return objs.toArray();
  }
}
