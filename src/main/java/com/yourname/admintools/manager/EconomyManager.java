package com.yourname.admintools.manager;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.UUID;



public class EconomyManager extends SavedData {


    private static final String DATA_NAME =
            "admintools_economy";



    private final CompoundTag moneyData;



    public EconomyManager(){

        moneyData =
                new CompoundTag();

    }




    public EconomyManager(
            CompoundTag tag
    ){

        moneyData =
                tag;

    }





    public static EconomyManager get(
            ServerLevel level
    ){


        return level
                .getDataStorage()
                .computeIfAbsent(
                        EconomyManager::load,
                        EconomyManager::new,
                        DATA_NAME
                );


    }





    public static EconomyManager load(
            CompoundTag tag
    ){

        return new EconomyManager(
                tag
        );

    }





    public int getBalance(
            UUID player
    ){


        return moneyData.getInt(
                player.toString()
        );


    }





    public void addMoney(
            UUID player,
            int amount
    ){


        int current =
                getBalance(player);



        moneyData.putInt(
                player.toString(),
                current + amount
        );


        setDirty();


    }





    public boolean removeMoney(
            UUID player,
            int amount
    ){


        int current =
                getBalance(player);



        if(current < amount){

            return false;

        }



        moneyData.putInt(
                player.toString(),
                current - amount
        );



        setDirty();



        return true;


    }





    public void setMoney(
            UUID player,
            int amount
    ){


        moneyData.putInt(
                player.toString(),
                Math.max(
                        amount,
                        0
                )
        );


        setDirty();


    }





    public boolean hasMoney(
            UUID player,
            int amount
    ){


        return getBalance(player)
                >= amount;


    }





    @Override
    public CompoundTag save(
            CompoundTag tag
    ){


        tag.put(
                "Money",
                moneyData
        );


        return tag;


    }


}
