package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.BookingModelManager;
import model.RoomList;

public class RoomsController
{
  private Region root;
  private BookingModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button buttonBack;
  @FXML private TextArea allRoomsArea;
  @FXML private TextArea availableRoomsArea;

  public void init(ViewHandler viewHandler, BookingModelManager modelManager, Region root)
  {
    this.modelManager = modelManager;
    this.root = root;
    this.viewHandler = viewHandler;
    //allRoomsArea = new TextArea();
    reset();
  }

  public void reset() {
    RoomList rooms = modelManager.getAllRooms();
    String str = "";

    for (int i = 0; i < rooms.size(); i++) {
      str += rooms.getRoom(i).toString() + "\n --------------\n " ;
    }
    allRoomsArea.setText(str);
  }

  public Region getRoot()
  {
    return root;
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == buttonBack) {
      viewHandler.openView("MainView");
    }
  }


}
