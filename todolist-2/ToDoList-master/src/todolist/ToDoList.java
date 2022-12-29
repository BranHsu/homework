package todolist;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ToDoList {

    public static void main(String[] args) throws IOException, FileNotFoundException, ClassNotFoundException {
        // TODO code application logic here
        ToDoGUI gui = new ToDoGUI();
        gui.setVisible(true);
        //AboutPage about = new AboutPage();
        //about.setVisible(true);
    }

}
