package osok.network.client;


import application.Bank;
import application.Player;
import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Michael Sjögren on 2016-05-20.
 */
public class Client{
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
        super();
        this.bank = bank;
        this.player = bank.getPlayer();
        username = bank.getPlayer().getUsername();

        try {
           Socket socket = new Socket( bank.getPlayer().getIp() ,bank.getPlayer().getPort());

            if(socket.isClosed()){
                return;
            }else{
                player.setIsConnected(true);
                new ChatClient(55555 , bank.getPlayer().getIp() , bank);
            }
           this.socket = socket;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(" -- Client connected -- ");
        /* start write thread for client */
        write = new ClientWrite(socket , bank );
        writingThread = new Thread(write , " client-write-thread ");
        writingThread.start();

            /* start read thread for client */
        read = new ClientRead(socket,bank);
        readingThread = new Thread(read , "  client-read-thread ");
        readingThread.start();

    }

    /** terminates threads **/
    public void shutdown()  {
            read.isRunning(false);
            write.isRunning(false);
            try {
                if(readingThread != null && writingThread != null){
                    readingThread.join();
                    writingThread.join();
                    socket.close();
                    System.out.println("-- client all threads terminated --");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}

    /** reads from server **/
    class ClientRead implements Runnable{

            private Socket socket;
            private Bank bank;
            private volatile boolean running = true;
            private  ArrayList<String> oldPlayers;
            private ArrayList<Player> newPlayers = new ArrayList<Player>();

        public ClientRead(Socket socket, Bank bank){
                this.socket = socket;
                this.bank=bank;
                }

        @Override
        public void run(){
                    System.out.println(socket.isConnected());
               //      String condition = bank.getPlayer().getUsername() + " : " + "null";

                    try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                        Gson gson = new Gson();

                         while(running){
                             Thread.sleep(1);
                             newPlayers.clear();
                             oldPlayers = gson.fromJson( br.readLine() , ArrayList.class);

                             try{
                                 for(int i = 0; i < oldPlayers.size(); i++){
                                     newPlayers.add(gson.fromJson(oldPlayers.get(i), Player.class));
                                    }
                                    bank.getOpponents().setOpponentsList(newPlayers);
                             }catch (Exception e){
                                e.printStackTrace();
                             }
                         }
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
                private Bank bank;
                private volatile boolean running = true;
                private Gson gson;

            public ClientWrite(Socket socket , Bank bank){
                this.socket = socket;
               this.bank=bank;
            }

            @Override
            public void run(){
                try (PrintWriter pw = new PrintWriter(socket.getOutputStream())){
                    gson = new Gson();
                    while(running){
                        Thread.sleep(1);
                        pw.println(gson.toJson(bank.getPlayer()));
                        pw.flush();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            public void isRunning(boolean running) {
                this.running = running;
            }
    }



