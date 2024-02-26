package org.example;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@WebService
public class BattleshipServer {

    static final List<Integer> cur = new ArrayList<>();
    static int numberOfPlayers = 0;
    static int lastMove = -1;
    static int lastPlayerMove = -1;
    static int FIELD_SIZE = 100;
    static int[] firstPlayerTables = new int[FIELD_SIZE];
    static int[] secondPlayerTables = new int[FIELD_SIZE];

    public BattleshipServer() {

    }

    @WebMethod
    public int ConnectPlayer(){
        numberOfPlayers += 1;
        return numberOfPlayers;
    }
    @WebMethod
    public int[] getFirstTable(){ return firstPlayerTables;}
    @WebMethod
    public int[] getSecondTable(){
        return secondPlayerTables;
    }



    @WebMethod
    public int getCur(){
        return cur.get(0);
    }

    @WebMethod
    public void setCur(int temp){
        cur.set(0, temp);
    }

    @WebMethod
    public void makeMove(int move, int player){
        lastMove = move;
        lastPlayerMove = player;
        System.out.println("" + lastMove + "   " + lastPlayerMove);
    }

    @WebMethod
    public int getLastMove(){
        return lastMove;
    }

    @WebMethod
    public int getLastPlayerMove(){
        return lastPlayerMove;
    }



    private static void createTables (int[] PlayersTables){
        int CF_ship =1;
        int CF_water =0;
        Random r = new Random();
        for(int i=0;i<FIELD_SIZE;i++){
                if(r.nextInt(5) == 1 ){
                    PlayersTables[i]=CF_ship;
                }
                else{
                    PlayersTables[i]=CF_water;
                }
        }
    }

    public static void sPrintTables(int[] firtsPlayerTables, int[] secondPlayerTables)
    {
        char CF_ship ='H';
        char CF_water ='~';
        char CF_dmg ='X';
        char CF_miss ='O';

        StringBuilder Upper = new StringBuilder();
        System.out.println(Upper);

        for(int i=0;i<10;i++)
        {
            StringBuilder str = new StringBuilder();
            {
                for(int j=i*10;j<i*10+10;j++)
                    str.append(firtsPlayerTables[j]).append(" ");
            }
            str.append("   ");
            for(int j=i*10;j<i*10+10;j++)
            {
                if (secondPlayerTables[j] == CF_ship)
                    str.append(CF_ship).append(" ");
                else
                    str.append(secondPlayerTables[j]).append(" ");
            }
            System.out.println(str);
        }
    }

    public static void main(String[] args) {

        cur.add(1);

        char CF_ship ='H';
        char CF_water ='~';
        char CF_dmg ='X';
        char CF_miss ='O';


        final int FIELD_SIZE = 10;



        createTables(firstPlayerTables);

        createTables(secondPlayerTables);

        sPrintTables(firstPlayerTables, secondPlayerTables);

        BattleshipServer virusWarServerApp = new BattleshipServer();
        Endpoint.publish("http://localhost:8080/battleship", virusWarServerApp);
    }





}


