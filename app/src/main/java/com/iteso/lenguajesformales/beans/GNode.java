package com.iteso.lenguajesformales.beans;

import java.util.ArrayList;

/**
 * Created by Palaf on 18/11/2017.
 */

public class GNode {
    ArrayList<GNode> list;
    boolean ok;
    Integer word;
    Character c;

    public GNode(Character c, boolean b, Integer i) {
        this.ok = b;
        this.c = c;
        this.word = i;
        this.list = new ArrayList<>();
    }

    void insert(Character key) {
        if (list.size() == 0) {
            list.add(new GNode(key, false, null));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            GNode g = list.get(i);
            if (g.c == key) {
                break;
            } else if (i == list.size() - 1) {
                list.add(new GNode(key, false, null));
                break;
            }
        }
    }

    GNode getNode(Character key) {
        for (int i = 0; i < list.size(); i++) {
            GNode g = list.get(i);
            if (g.c == key) {
                return g;
            }
        }
        return null;
    }
}
