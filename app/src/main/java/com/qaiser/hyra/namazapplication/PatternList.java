package com.qaiser.hyra.namazapplication;

import java.util.ArrayList;

/**
 * Created by SyedFurqanShah on 9/21/2015.
 */

public class PatternList {

    ArrayList arrayList2;
    String takbeer="2000,1000,2000";
    String ruku_start="300,150,300";
    String ruku_stop="300,150,300";
    String first_sajda_start="300,150,300,150,300,150,300";
    String first_sajda_stop="300,150,300,150,300,150,300";
    String second_sajda_start="300,150,300,150,300,150,300";
    String second_sajda_stop="300,150,300,150,300,150,300";
    String atahiyat1= "0, 800, 150, 800, 150";
    String qaada="600";
    String salam="800";

    public ArrayList getArrayList2() {
        arrayList2 = new ArrayList();
        arrayList2.add(takbeer);
        arrayList2.add(ruku_start);
        arrayList2.add(ruku_stop);
        arrayList2.add(first_sajda_start);
        arrayList2.add(first_sajda_stop);
        arrayList2.add(second_sajda_start);
        arrayList2.add(second_sajda_stop);
        arrayList2.add(ruku_start);
        arrayList2.add(ruku_stop);
        arrayList2.add(first_sajda_start);
        arrayList2.add(first_sajda_stop);
        arrayList2.add(second_sajda_start);
        arrayList2.add(second_sajda_stop);
        arrayList2.add(atahiyat1);
        arrayList2.add(salam);
        arrayList2.add(salam);

    return arrayList2;
    }
}
