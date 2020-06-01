package com.krzysztof.app.pattern;

import com.vaadin.flow.component.combobox.ComboBox;

import java.util.ArrayList;
import java.util.List;

/**
 * klasa wykorzystująca wzorzec projektowy iterator zarządzający elementami w liście
 */
public class Iterator {
    private int index=0;
    private List<String> list ;

    /**
     * konstruktor
     * @param comboBoxList przyjmuje liste
     */
    public Iterator(List<String> comboBoxList) {
        this.list = comboBoxList;
    }

    /**
     * metoda szukająca pierwszego elementu listy
     * @return pierwszy element listy
     */
    public String first() {
        return list.get(0);
    }

    /**
     *  metoda sprawdzająca czy istnieje kolejny element listy
     * @return zwraca wartość logiczną true jeżeli istnieje lub false jeżeli nie istnieje
     */
    public boolean hasNext() {
        if (index < list.size()) {
            return true;
        }
        return false;
    }

    /**
     * metoda przechodząca do kolejnego elementu listy
     * @return zwraca kolejny elemnet listy
     */
    public String Next() {
        if (hasNext()) {
            return list.get(index++);
        }
        return null;
    }

    /**
     * metoda pobiera obecny element w liście
     * @return zwraca element listy
     */
    public String currentItem(){
        return list.get(index-1);
    }

    /**
     * metoda sprawdzająca czy jest już koniec listy
     * @return zwraca wartość logiczną
     */
    public boolean isDone(){
        if (index >= list.size()){
            return true;
        }
        return false;
    }

}
