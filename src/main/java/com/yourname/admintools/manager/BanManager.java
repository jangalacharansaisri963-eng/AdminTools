package com.yourname.admintools.manager;


import com.google.gson.*;

import net.minecraftforge.fml.loading.FMLPaths;


import java.io.*;
import java.nio.file.*;
import java.util.*;


public class BanManager {


    private static final Set<UUID> bannedPlayers = new HashSet<>();


    private static final Path FILE =
            FMLPaths.GAMEDIR.get()
            .resolve("config")
            .resolve("admintools_bans.json");



    public static void load(){


        try {


            if(!Files.exists(FILE)){

                save();
                return;

            }


            JsonObject object =
                    JsonParser.parseReader(
                            new FileReader(FILE.toFile())
                    )
                    .getAsJsonObject();



            JsonArray array =
                    object.getAsJsonArray("banned");



            for(JsonElement e : array){

                bannedPlayers.add(
                        UUID.fromString(
                        e.getAsString()
                        )
                );

            }


        } catch(Exception e){

            e.printStackTrace();

        }

    }





    public static void save(){


        try{


            Files.createDirectories(
                    FILE.getParent()
            );


            JsonObject object =
                    new JsonObject();


            JsonArray array =
                    new JsonArray();



            for(UUID uuid : bannedPlayers){

                array.add(
                        uuid.toString()
                );

            }


            object.add(
                    "banned",
                    array
            );



            FileWriter writer =
                    new FileWriter(
                    FILE.toFile()
                    );


            new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(
                    object,
                    writer
                    );


            writer.close();



        }catch(Exception e){

            e.printStackTrace();

        }

    }





    public static void ban(UUID uuid){

        bannedPlayers.add(uuid);

        save();

    }




    public static void unban(UUID uuid){

        bannedPlayers.remove(uuid);

        save();

    }



    public static boolean isBanned(UUID uuid){

        return bannedPlayers.contains(uuid);

    }


}
