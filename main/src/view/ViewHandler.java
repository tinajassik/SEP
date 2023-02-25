package view;

import model.BookingModelManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A class responsible for opening and changing views/windows.
 *
 * @author Allan Henriksen, Kristina Jassova
 * @version 1.0
 */
public class ViewHandler {
    private Scene scene;
    private Stage window;
    private MainViewController mainViewController;
    private CreateBookingController createBookingController;
    private CheckInController checkInController;
    private ManageBookingController manageBookingController;
    private RoomsController roomsController;
    private GuestsController guestsController;
    private CheckOutController checkOutController;
    private EditBookingController editBookingController;

    private BookingModelManager modelManager;

    /**
     * One-argument constructor for ViewHandler object.
     *
     * @param modelManager object of the BookingModelManager class
     */
    public ViewHandler(BookingModelManager modelManager) {
        this.modelManager = modelManager;
        scene = new Scene(new Region());
    }

    /**
     * Initializes the application.
     *
     * @param window the first window to be open when starting the GUI
     */
    public void start(Stage window) {
        this.window = window;
        window.setResizable(false);
        openView("MainView");
        loadViewSearchBooking();
        loadViewCheckOut();
        loadViewEditBooking();
        loadViewCheckIn();
        loadViewRooms();
        loadViewGuests();
        loadViewCreateBooking();
    }


    /**
     * Opens the window based on the id given by the parameter.
     *
     * @param id is a specific id for each of the created windows
     */
    public void openView(String id) {
        Region root = null;
        switch (id) {
            case "MainView":
                root = loadViewMain();
                break;
            case "CreateBooking":
                root = loadViewCreateBooking();
                break;
            case "CheckIn":
                root = loadViewCheckIn();
                break;
            case "SearchBooking":
                root = loadViewSearchBooking();
                break;
            case "Rooms":
                root = loadViewRooms();
                break;
            case "Guests":
                root = loadViewGuests();
                break;
            case "CheckOut":
                root = loadViewCheckOut();
                break;
            case "EditBooking":
                root = loadViewEditBooking();
                break;
        }
        scene.setRoot(root);
        String title = "";

        if (root.getUserData() != null) {
            title += root.getUserData();
        }

        window.setTitle(title);
        window.setScene(scene);
        window.setWidth(root.getPrefWidth());
        window.setHeight(root.getPrefHeight());
        window.show();
    }

    private Region loadViewMain() {
        if (mainViewController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("mainview.fxml"));
                Region root = loader.load();
                mainViewController = loader.getController();
                mainViewController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            mainViewController.reset();
        }

        return mainViewController.getRoot();
    }

    private Region loadViewCreateBooking() {
        if (createBookingController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("createbooking.fxml"));
                Region root = loader.load();
                createBookingController = loader.getController();
                createBookingController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createBookingController.reset();
        }

        return createBookingController.getRoot();
    }

    private Region loadViewCheckIn() {
        if (checkInController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("checkin.fxml"));
                Region root = loader.load();
                checkInController = loader.getController();
                checkInController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            checkInController.reset();
        }

        return checkInController.getRoot();
    }

    private Region loadViewCheckOut() {
        if (checkOutController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("checkout.fxml"));
                Region root = loader.load();
                checkOutController = loader.getController();
                checkOutController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            checkOutController.reset();
        }

        return checkOutController.getRoot();
    }

    private Region loadViewSearchBooking() {
        if (manageBookingController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("searchbooking.fxml"));
                Region root = loader.load();
                manageBookingController = loader.getController();
                manageBookingController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            manageBookingController.reset();
        }

        return manageBookingController.getRoot();
    }

    private Region loadViewRooms() {
        if (roomsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("roomsview.fxml"));
                Region root = loader.load();
                roomsController = loader.getController();
                roomsController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            roomsController.reset();
        }

        return roomsController.getRoot();
    }

    private Region loadViewGuests() {
        if (guestsController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("guestsview.fxml"));
                Region root = loader.load();
                guestsController = loader.getController();
                guestsController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            guestsController.reset();
        }

        return guestsController.getRoot();
    }

    private Region loadViewEditBooking() {
        if (editBookingController == null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("editbooking.fxml"));
                Region root = loader.load();
                editBookingController = loader.getController();
                editBookingController.init(this, modelManager, root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            editBookingController.reset();
        }

        return editBookingController.getRoot();
    }

    /**
     * Gets ManageBooking controller.
     *
     * @return ManageBooking controller
     */
    public ManageBookingController getManageBookingController() {
        return manageBookingController;
    }

}
