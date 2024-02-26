package org.example;

import org.example.service.BattleshipServer;
import org.example.service.BattleshipServerService;
import org.example.service.IntArray;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Client2 {

    static final int FIELD_SIZE = 10;
    static char[][] firstPlayerTable = new char[FIELD_SIZE][FIELD_SIZE];
    static char[][] secondPlayerTable = new char[FIELD_SIZE][FIELD_SIZE];

    static List<Integer> MYTables = new ArrayList<Integer>();
    static List<Integer> ENEMYTables = new ArrayList<Integer>();

    static char CF_ship ='H';
    static char CF_water ='~';
    static char CF_dmg ='X';
    static char CF_miss ='O';
    static char fieldCell;
    static int numberOfPlayer = 0;

    static int status = 0;
    static Scanner scanner = new Scanner(System.in);
    static BattleshipServer game;
    static int last = -1;

    public static void main(String[] args) throws RemoteException, NotBoundException {



        BattleshipServerService battleshipServer = new BattleshipServerService();
        game = battleshipServer.getBattleshipServerPort();

        numberOfPlayer = game.connectPlayer();

        if(numberOfPlayer==1) {
           MYTables = game.getFirstTable();
           for(int i=0;i<10;i++){
               for(int j=0;j<10;j++){
                   if(MYTables.get(i*10+j)==1){
                       firstPlayerTable[i][j]=CF_ship;
                   }
                   else{
                       firstPlayerTable[i][j]=CF_water;
                   }
               }
           }
           ENEMYTables = game.getSecondTable();
           for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(ENEMYTables.get(i*10+j)==1){
                        secondPlayerTable[i][j]=CF_ship;
                    }
                    else{
                        secondPlayerTable[i][j]=CF_water;
                    }
                }
            }

        }
        else{
           MYTables = game.getSecondTable();
           for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(MYTables.get(i*10+j)==1){
                        firstPlayerTable[i][j]=CF_ship;
                    }
                    else{
                        firstPlayerTable[i][j]=CF_water;
                    }
                }
            }
           ENEMYTables = game.getFirstTable();
           for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(ENEMYTables.get(i*10+j)==1){
                        secondPlayerTable[i][j]=CF_ship;
                    }
                    else{
                        secondPlayerTable[i][j]=CF_water;
                    }
                }
           }
        }


//        if(numberOfPlayer==1) {
//            firstPlayerTable = BattleShipMaps.createTablesFirstPlayer();
//            secondPlayerTable = BattleShipMaps.createTablesSecondPlayer();
//        }
//        else{
//            firstPlayerTable = BattleShipMaps.createTablesSecondPlayer();
//            secondPlayerTable = BattleShipMaps.createTablesFirstPlayer();
//        }
        int x,y;

        PrintTables(firstPlayerTable, secondPlayerTable, numberOfPlayer);

        while(true) {

            status = game.getCur();

            int coordinates = game.getLastMove();

            if (last != coordinates){
                System.out.println(coordinates);
                x = coordinates / 10-1;
                y = coordinates % 10-1;

                fieldCell = firstPlayerTable[x][y];
                if (fieldCell != CF_dmg && fieldCell != CF_miss){

                    if (fieldCell == CF_ship)
                    {
                        firstPlayerTable[x][y] = CF_dmg;
                    } else{
                        firstPlayerTable[x][y] = CF_miss;
                    }
                }
            }


            if (status == numberOfPlayer)
            {
                PrintTables(firstPlayerTable, secondPlayerTable,numberOfPlayer);
                System.out.println("your turn");
                List<String> input_coordinates = List.of(scanner.nextLine().split(" "));
                coordinates = Integer.parseInt(input_coordinates.get(0))*10+Integer.parseInt(input_coordinates.get(1));
                game.makeMove(coordinates, numberOfPlayer);
                last = coordinates;

                x = Integer.parseInt(input_coordinates.get(0))-1;
                y = Integer.parseInt(input_coordinates.get(1))-1;

                fieldCell = secondPlayerTable[x][y];
                if (fieldCell != CF_dmg || fieldCell != CF_miss){

                    if (fieldCell == CF_ship)
                    {
                        secondPlayerTable[x][y] = CF_dmg;

                        boolean lost = true;
                        for (int i = 0; i < FIELD_SIZE; i++){
                            for (int k = 0; k < FIELD_SIZE; k++){
                                if (secondPlayerTable[i][k] == CF_ship)
                                    lost = false;
                            }
                        }

                        if (lost)
                        {
                            if (numberOfPlayer == 1){
                                game.setCur(3);
                            } else if (numberOfPlayer == 2) {
                                game.setCur(4);
                            }
                        }
                    } else{
                        secondPlayerTable[x][y] = CF_miss;
                    }
                    if (numberOfPlayer == 1){
                        game.setCur(2);
                    } else if (numberOfPlayer == 2) {
                        game.setCur(1);
                    }

                }
                System.out.println("after move");
                PrintTables(firstPlayerTable, secondPlayerTable, numberOfPlayer);
            } else if (status == 3 || status == 4) {

                if (status == 3 && numberOfPlayer == 1 || status == 4 && numberOfPlayer == 2) {
                    System.out.println("you win");
                    return;
                }
                if (status == 3 && numberOfPlayer == 2 || status == 4 && numberOfPlayer == 1) {
                    System.out.println("you defeat");
                    return;
                }

            }

        }
    }

    public static void PrintTables(char[][] firtsPlayerTables, char[][] secondPlayerTables, int numberOfPlayer)
    {
        char CF_ship ='H';
        char CF_water ='~';
        char CF_dmg ='X';
        char CF_miss ='O';

        StringBuilder Upper = new StringBuilder();
        System.out.println(Upper);

        for(int i=0;i<FIELD_SIZE;i++)
        {
            StringBuilder str = new StringBuilder();
            {
                for(int j=0;j<FIELD_SIZE;j++)
                    str.append(firtsPlayerTables[i][j]).append(" ");
            }
            str.append("   ");
            for(int j=0;j<FIELD_SIZE;j++)
            {
                if (secondPlayerTables[i][j] == CF_ship)
                    str.append(CF_ship).append(" ");
                else
                    str.append(secondPlayerTables[i][j]).append(" ");
            }
            System.out.println(str);
        }
    }
}
