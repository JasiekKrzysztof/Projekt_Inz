package com.krzysztof.app.pattern;

import com.vaadin.flow.component.combobox.ComboBox;

import java.util.ArrayList;
import java.util.List;

public class Iterator {
    private int index=0;
    private List<String> list ;


    public Iterator(List<String> comboBoxList) {
        this.list = comboBoxList;
    }

    public String first() {
        return list.get(0);
    }

    public boolean hasNext() {
        if (index < list.size()) {
            return true;
        }
        return false;
    }

    public String Next() {
        if (hasNext()) {
            return list.get(index++);
        }
        return null;
    }

    public String currentItem(){
        return list.get(index-1);
    }

    public boolean isDone(){
        if (index >= list.size()){
            return true;
        }
        return false;
    }

}
