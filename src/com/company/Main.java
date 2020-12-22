package com.company;

import java.util.Scanner;

public class Main {

    private static Messages gameMessages = new Messages();
    private static BotAlgorithm botAlgorithm = new BotAlgorithm();
    private static Scanner consoleReader = new Scanner(System.in);
    private static int matches = 20;
    private static Boolean botPlay = false;
    private static String[] players = {"Игрок1", "Игрок2"};
    private static int selector = 0;


    public static void main(String[] args) {
        selectGameMode();
    }

    public static void selectGameMode() {
        System.out.println("*********\n" +
                "Игра 20 спичек\n" +
                "Выберите игровой мод:\n" +
                "1) Игрок против игрока\n" +
                "2) Компьютер против игрока\n");

        while (!selectMode()) {
            System.out.println("Такого варианта нет\n" +
                    "Попробуйте снова: ");
        }


        System.out.println(gameMessages.gameStarted());
        gameStep();
    }

    public static boolean selectMode() {
        switch (consoleReader.nextInt()) {
            case 1:
                botPlay = false;
                break;
            case 2:
                botPlay = true;
                break;
            default:
                return false;
        }
        return true;
    }

    public static void updateSelector() {
        if (matches < 2) {
            printPartWinner();
        }

        if (selector == 0)
            selector++;
        else
            selector--;

        System.out.println(gameMessages.countMatches(matches));
        gameStep();

    }

    public static void gameStep() {
        int step = 0;

        if (botPlay && selector == 0) {
            step = botAlgorithm.stepBot(matches);
            System.out.println(gameMessages.botMove(step));
        } else {
            while (step < 1 || step > 3) {
                if (botPlay) System.out.print(gameMessages.playerMove(players[0]));
                else System.out.print(gameMessages.playerMove(players[selector]));
                step = consoleReader.nextInt();
            }
        }

        matches -= step;
        updateSelector();
    }

    public static void printPartWinner() {
        if (botPlay && matches == 1) {
            System.out.println("\n\n************\n" + gameMessages.playerWin("Бот "));
            System.out.print(gameMessages.oneMatch(players[0]) + "\n************\n\n");
            restartGame();
        }


        if (matches == 1){
            System.out.println("\n\n************\n" + gameMessages.playerWin(players[selector]));
            if (selector == 1) {
                System.out.print(gameMessages.oneMatch(players[--selector]) + "\n************\n\n");
            } else {
                System.out.print(gameMessages.oneMatch(players[++selector]) + "\n************\n\n");
            }
        }

        if (matches < 1) {
            if (selector == 1) {
                System.out.println("\n\n************\n" + gameMessages.playerWin(players[--selector]));
                System.out.print(gameMessages.zeroMatches(players[++selector]) + "\n************\n\n");
            } else {
                System.out.println("\n\n************\n" + gameMessages.playerWin(players[++selector]));
                System.out.print(gameMessages.zeroMatches(players[--selector]) + "\n************\n\n");
            }
        }

        restartGame();
    }

    public static void restartGame() {
        selector = 0;
        matches = 20;
        botPlay = false;
        selectGameMode();
    }
}
