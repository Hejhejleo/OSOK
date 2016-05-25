package osok.network.client;


import application.Bank;
import application.Player;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */
public class Client extends Player{
    private int port;
    private String ip;
    private ClientRead read;
    private ClientWrite write;
    private Thread writingThread;
    private Thread readingThread;
    private Socket socket;
    private String username;
    private Bank bank;
    private Player player;


    /* konstruktor */
    public Client(Bank bank){
     super(bank);
        this.player = bank.getPlayer();


        try {
           Socket socket = new Socket(player.getIp(), player.getPort());
            if(socket.isClosed()){
                return;
            }else{
                player.setIsConnected(true);
            }

           this.socket = socket;
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println(" -- Client connected -- ");

                    /* start write thread for client */
        write = new ClientWrite(socket , player );
        writingThread = new Thread(write , "-- client-write-thread -- ");
        writingThread.start();

            /* start read thread for client */
        read = new ClientRead(socket);
        readingThread = new Thread(read , " -- client-read-thread --");
        readingThread.start();


    }




    /** terminates threads **/
    public void shutdown()  {
        read.isRunning(false);
        write.isRunning(false);
        try {
            readingThread.join();
            writingThread.join();
            System.out.println("-- client all threads terminated --");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/** reads from server **/
class ClientRead implements Runnable{

    private Socket socket;
    private boolean running = true;

    public ClientRead(Socket socket){
            this.socket = socket;
            }

    @Override
    public void run(){
            System.out.println(socket.isConnected());
            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            while(running){
                Thread.sleep(1000);
                String s = (String) ois.readObject();
                System.out.println(s);
            }
                System.out.println(" -- client isRunning read : " + running + " --");
            }catch (Exception e){
            e.printStackTrace();
            }
    }

            public void isRunning(boolean running) {
            this.running = running;
            }
 }
    /** writes to server **/
class ClientWrite implements Runnable{

        private Socket socket;
        private Player player;
        private boolean running = true;

    public ClientWrite(Socket socket , Player player){
        this.socket = socket;
        this.player = player;
    }

    @Override
    public void run(){

        try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())){
            System.out.println("CLIENT WRITE START");
            while(running){
                Thread.sleep(1000);
                String s = "CLIENT :: Client writing to server";
                oos.writeObject(s);

            }
            System.out.println(" -- client isRunning write : " + running + " -- ");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void isRunning(boolean running) {
        this.running = running;
    }
}
