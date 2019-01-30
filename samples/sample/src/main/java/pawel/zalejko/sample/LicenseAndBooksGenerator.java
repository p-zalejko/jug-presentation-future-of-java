package pawel.zalejko.sample;

import java.util.Random;

public class LicenseAndBooksGenerator {

    public static void main(String[] args) {
        // https://docs.google.com/spreadsheets/d/11h5FtIXlTrq79PwuJVitiLLorX6bGO_8ERGXd77O8mU/edit#gid=785691776
        int number = new Random().nextInt(15) + 1;
        System.out.println("the winner is: " + number);
    }
}
