import connection.MenaData;

import models.Program;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        MenaData md = new MenaData();
        md.start();
        System.out.println(md.getNumberOfRegisters() + " registers");
        ArrayList<Program> games = md.searchByName("LIGHTFORCE");
        for (Program i : games) {
            System.out.println(i.serialize());
        }

        ArrayList<Program> gamesByTape = md.searchByTape("");
        for (Program i : gamesByTape) {
            System.out.println(i.serialize());
        }

        //long startTime = System.currentTimeMillis();
        //for (int i = 0; i < 10000; i++) {
        //    ocr.getStringFromImage(gameImage);
        //}
        //long stopTime = System.currentTimeMillis();
        //long elapsedTime = stopTime - startTime;
        //System.out.println(elapsedTime);


        md.close();
    }
}
