package com.yourname.admintools.manager;


import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



public class ShopManager extends SavedData {



    private static final String DATA_NAME =
            "admintools_shop";



    private final List<ShopItem> listings =
            new ArrayList<>();





    public ShopManager(){}





    public ShopManager(
            CompoundTag tag
    ){


        ListTag list =
                tag.getList(
                        "Listings",
                        10
                );



        for(int i = 0; i < list.size(); i++){


            CompoundTag data =
                    list.getCompound(i);



            listings.add(

                    new ShopItem(

                            UUID.fromString(
                                    data.getString("seller")
                            ),

                            data.getString("item"),

                            data.getInt("amount"),

                            data.getInt("price")

                    )

            );


        }


    }






    public static ShopManager get(
            ServerLevel level
    ){


        return level.getDataStorage()
                .computeIfAbsent(

                        ShopManager::load,

                        ShopManager::new,

                        DATA_NAME

                );


    }





    public static ShopManager load(
            CompoundTag tag
    ){

        return new ShopManager(tag);

    }







    public void addListing(
            UUID seller,
            String item,
            int amount,
            int price
    ){


        listings.add(

                new ShopItem(
                        seller,
                        item,
                        amount,
                        price
                )

        );


        setDirty();


    }






    public List<ShopItem> getListings(){

        return listings;

    }






    public ShopItem getListing(
            int id
    ){


        if(
                id < 1
                ||
                id > listings.size()
        ){

            return null;

        }



        return listings.get(
                id - 1
        );


    }







    public boolean removeListing(
            int id
    ){


        ShopItem item =
                getListing(id);



        if(item == null){

            return false;

        }



        listings.remove(
                id - 1
        );


        setDirty();


        return true;


    }






    public boolean isOwner(
            int id,
            UUID player
    ){


        ShopItem item =
                getListing(id);



        return item != null
                &&
                item.seller.equals(player);


    }








    @Override
    public CompoundTag save(
            CompoundTag tag
    ){


        ListTag list =
                new ListTag();



        for(ShopItem shop : listings){



            CompoundTag data =
                    new CompoundTag();



            data.putString(
                    "seller",
                    shop.seller.toString()
            );



            data.putString(
                    "item",
                    shop.item
            );



            data.putInt(
                    "amount",
                    shop.amount
            );



            data.putInt(
                    "price",
                    shop.price
            );



            list.add(
                    data
            );


        }



        tag.put(
                "Listings",
                list
        );



        return tag;


    }







    public static class ShopItem {


        public UUID seller;

        public String item;

        public int amount;

        public int price;




        public ShopItem(
                UUID seller,
                String item,
                int amount,
                int price
        ){

            this.seller = seller;
            this.item = item;
            this.amount = amount;
            this.price = price;


        }


    }



}
