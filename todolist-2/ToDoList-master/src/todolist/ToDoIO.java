package todolist;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class ToDoIO {

    private int length;
    private int rows;
    private ArrayList<Object[]> tableList = new ArrayList<>(); //list of each row

    public ToDoIO(int l) {
        length = l;
        rows = 0;
    }

    public void add(Object[] obj) {
        tableList.add(obj);
        rows++;
    }


    public void export(String filename) throws FileNotFoundException, IOException {
        FileOutputStream fos;
        fos = new FileOutputStream(filename + ".ToDoIO");
        ObjectOutputStream objStream = new ObjectOutputStream(fos);

        //ArrayList Iterator
        for (Object[] objOutArray : tableList) {
            //get to each individual object
            for (Object objOut : objOutArray) {
                objStream.writeObject(objOut);
            }
        }
        //null for EOF
        objStream.writeObject(null);
        objStream.close();
    }

    public void importFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename + ".ToDoIO");
        ObjectInputStream objStream = new ObjectInputStream(fis);
        rows = 0;
        int currRow = 0;
        tableList.clear();
        int counter = 0;
        Object ObjTemp = objStream.readObject();
        while (ObjTemp != null) {
            Object[] objArray = new Object[length];
            objArray[0] = ObjTemp;
            if (length > 1) {
                for (int i = 1; i < length; i++) {
                    objArray[i] = objStream.readObject();
                }
            }
            tableList.add(objArray);
            rows++;
            ObjTemp = objStream.readObject();

        }
        objStream.close();

    }

    public void importFile(File file) throws FileNotFoundException, IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream objStream = new ObjectInputStream(fis);
        rows = 0;
        int currRow = 0;
        tableList.clear();
        int counter = 0;
        Object ObjTemp = objStream.readObject();
        while (ObjTemp != null) {
            Object[] objArray = new Object[length];
            objArray[0] = ObjTemp;
            if (length > 1) {
                for (int i = 1; i < length; i++) {
                    objArray[i] = objStream.readObject();
                }
            }
            tableList.add(objArray);
            rows++;
            ObjTemp = objStream.readObject();

        }
        objStream.close();

    }

    public Object[] getRow(int i) {
        if (i < rows) {
            return tableList.get(i);
        } else {
            return null;
        }
    }

    public int getRowCount() {
        return rows;
    }
}
