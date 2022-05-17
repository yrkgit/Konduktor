package pl.pesa.konduktor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import pl.pesa.konduktor.frames.Frame;
import pl.pesa.konduktor.frames.JsonDeserializer;

public class CommunicationFromHubListener extends SocketListener implements Runnable {
    private Socket socket;
    private ServerSocket serverSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private String content;
    private boolean isServerRunning;
    private LogonActivity logonActivity;
    private JsonDeserializer deserializer;
    private Frame frame;


    public CommunicationFromHubListener(LogonActivity logonActivity) {
        this.logonActivity = logonActivity;
        isServerRunning = true;
    }

    public void stopServer() {
        isServerRunning = false;
    }

    public void startServer() {
        isServerRunning = true;
    }

    @Override
    public void run() {

        startSocketListener();
        logonActivity.log();
//        deserializer = new JsonDeserializer();
//        try {
//            content = startSocketListener();
//            frame = deserializer.deserializeJsonToFrameObject(content);
//            System.out.println("DOSTep udzielony " +frame.toString());
//            if (frame.getAppVersion().equals("1.0")){
//                logonActivity.log();
//            }
//
//        } catch (Exception e) {
//
//        }

    }
}
