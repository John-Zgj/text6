package cn.edu.jnu.text6.data;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaver {
    public void Save(Context context , ArrayList<ShopItem> data)
    {
        try {
            FileOutputStream dataStream = context.openFileOutput("mydata.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out = null;
            try {
                out = new ObjectOutputStream(dataStream);
                out.writeObject(data);
                out.close();
                dataStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ShopItem> Load(Context context) {
        ArrayList<ShopItem> data = new ArrayList<>();
        FileInputStream fileIn = null;

        try {
            FileInputStream filein = context.openFileInput("mydata.dat");
            ObjectInputStream in = new ObjectInputStream(filein);
            data = (ArrayList<ShopItem>) in.readObject();
            in.close();
            filein.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return data;
        }

    }