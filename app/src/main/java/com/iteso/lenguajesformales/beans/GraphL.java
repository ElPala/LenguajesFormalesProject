package com.iteso.lenguajesformales.beans;

import java.util.ArrayList;

/**
 * Created by Palaf on 18/11/2017.
 */

public class GraphL {
    GNode root;
    ArrayList<Integer> inc;
    ArrayList<Integer> pos;
    int match[];

    public int[] getMatch() {
        return match;
    }

    public GraphL(String s[]){
        inc = new ArrayList<>();
        pos = new ArrayList<>();
        root = new GNode(null, false,null);
        match = new int[s.length];
        for(int i=0;i<s.length;i++){
            insert(s[i],i);
        }
    }
    private void insert(String s,Integer i){
        GNode aux = root;
        for (int x = 0; x < s.length(); x++) {
            Character c = s.charAt(x);
            aux.insert(c);
            aux = aux.getNode(c);
            if (x == s.length() - 1) {
                aux.ok = true;
                aux.word=i;
            }
        }
    }
    public void check(String w) {
        GNode aux = root;
        inc.clear();
        pos.clear();
        for (int x = 0; x < w.length(); x++) {
            Character c = w.charAt(x);
            boolean b =false;
            for (int i =0;i<aux.list.size();i++){
                GNode g = aux.list.get(i);
                if(g.c==c){
                    aux=g;
                    break;
                }else if (i==aux.list.size()-1){
                    b=true;
                }
            }

            if (b){
                if(!pos.isEmpty()){
                    x = pos.remove(pos.size() - 1);
                    match[inc.remove(inc.size()-1)]++;
                    inc.clear();
                    pos.clear();
                }
                aux=root;
                continue;
            }else {
                if(aux.ok && aux.list.isEmpty()){
                    inc.clear();
                    pos.clear();
                    match[aux.word]++;
                    aux=root;
                    continue;
                }else if(aux.ok){
                    if(x==w.length()-1){
                        match[aux.word]++;
                    }
                    else {
                        inc.add(aux.word); //
                        pos.add(x);
                    }
                }else if(x==w.length()-1 && !pos.isEmpty()){
                    match[inc.remove(inc.size()-1)]++;
                }
            }
        }
    }
}
