package com.hika.service;


/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public interface QuestionnaraireService {

    public int randomNumber(int min, int max);
    public String getRandomQuestion(int randomNumber);

}
