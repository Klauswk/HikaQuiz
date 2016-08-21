package com.hika.service;

import java.util.Random;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */
public class QuestionnaraireServiceImpl implements QuestionnaraireService {


    public QuestionnaraireServiceImpl() {
    }

    @Override
    public String getRandomQuestion(int randomNumber) {
        return "";
    }

    @Override
    public int randomNumber(int min, int max) {
        Random random = new Random();
        if (min > max) {
            while(min>max)
            {
                min--;
            }
        }
        long extensao = (long)max - (long)min + 1;
        long fraction = (long)(extensao * random.nextDouble());
        int generated =  (int)(fraction + min);
        return generated;
    }
}
