package com.company;

public class BotAlgorithm {
    private int[] loseCount = {1, 5, 9, 13, 17};

    int stepBot(int countMatches) {
        int result = 0;

        for (int num : loseCount) {
            result = countMatches - num;
            if (result <= 3 && result > 0)
                break;
            if (result <= 0) {
                result = 1;
                break;
            }
        }

        return result;

        /*
        как вариант можно сделать такой алгоритм
         первый ход бота 3 спички
         дальше передаем шаги игрока

         switch(playerStep){
         case 1:
            botStep = 3;
            break;
         case 2:
            botStep = 2;
         case 3:
            botStep = 1;
            break;
         default:
            //ошибка программы
            break;
            }
        */
    }
}
