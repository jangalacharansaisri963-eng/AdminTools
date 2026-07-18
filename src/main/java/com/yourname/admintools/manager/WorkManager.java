package com.yourname.admintools.manager;

import java.util.ArrayList;
import java.util.List;


public class WorkManager {


    private static final List<String> works =
            new ArrayList<>();



    public static void addWork(
            String work
    ){

        works.add(
                work
        );

    }



    public static List<String> getWorks(){

        return new ArrayList<>(
                works
        );

    }



    public static boolean removeWork(
            int id
    ){

        if(
                id < 0 ||
                id >= works.size()
        ){

            return false;

        }


        works.remove(id);


        return true;

    }


}
