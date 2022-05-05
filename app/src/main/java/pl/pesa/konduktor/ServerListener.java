package pl.pesa.konduktor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener implements Runnable{
    Socket socket;
    ServerSocket serverSocket;
    InputStreamReader inputStreamReader;
    BufferedReader bufferedReader;
    String message;

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(7801);
            while (true){
                socket=serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                bufferedReader= new BufferedReader(inputStreamReader);
                message= bufferedReader.readLine();
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
