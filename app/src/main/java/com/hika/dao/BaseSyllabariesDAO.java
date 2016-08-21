package com.hika.dao;

import com.hika.model.Syllabarie;
import com.hika.model.SyllabaryType;

import java.util.List;

/**
 * @author <a href="https://github.com/Klauswk">Klaus Klein</a>
 * @version 1.0
 * @since 1.0
 */

public interface BaseSyllabariesDAO {

    public List<Syllabarie> getAllSyllabaries();
    public List<Syllabarie> getSyllabarieByType(SyllabaryType syllabaryType);


}
