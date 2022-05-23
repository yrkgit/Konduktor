package pl.pesa.konduktor;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class StringToServerSender extends AsyncTask {

    private Socket socket;
    private PrintWriter writer;
    private String content;

    public StringToServerSender(String content) {
        this.content = content;
    }

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            //TODO get destination ip from config file
            socket = new Socket("192.168.0.11", 7800);
            writer = new PrintWriter(socket.getOutputStream());
            writer.write(content);
            writer.flush();
            writer.close();
            socket.close();
            System.out.println(content + " send to server");
        } catch(ConnectException connectException){
            System.out.println("Can't send frame to device");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
