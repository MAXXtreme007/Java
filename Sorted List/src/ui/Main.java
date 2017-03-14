package ui;

import bp.SortedList;

public class Main {

    public static void main(String[] args) {
        SortedList sl = new SortedList();

        //allowing dups
        sl.setDuplicatesAllowed(true);
        sl.initializeWithRandomData(5);
        System.out.println(sl);
        sl.insert(10);
        sl.insert(10);
        System.out.println(sl);
        sl.deleteAll(10);
        System.out.println(sl);
        sl.clear();
        System.out.println(sl);
        sl.delete(5);
        System.out.println(sl);
        
        //No dups allowed
        sl.setDuplicatesAllowed(false);
        sl.initializeWithRandomData(100);
        System.out.println(sl);
        sl.insert(5);
        System.out.println(sl);
        sl.delete(5);
        System.out.println(sl);
        sl.deleteAll(6);
        System.out.println(sl);
        sl.insert(5);
        System.out.println(sl);
        sl.selectionSort();
        System.out.println(sl);
    }

}