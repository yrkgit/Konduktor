package pl.pesa.konduktor;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunication extends AsyncTask {

    private Socket socket;
    private PrintWriter writer;
    private String message = "TEST komuniakcji";

    @Override
    protected Object doInBackground(Object[] objects) {

        try {
            socket = new Socket("10.1.0.189", 7800);
            writer = new PrintWriter(socket.getOutputStream());
            writer.write(message);
            writer.flush();
            writer.close();
            socket.close();
            System.out.println(message + " send to server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
