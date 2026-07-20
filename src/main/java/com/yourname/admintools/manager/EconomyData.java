package com.yourname.admintools.manager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public class EconomyData extends SavedData {

    private static final String DATA_NAME =
            "admintools_economy_data";

    private final CompoundTag data;

    public EconomyData(){

        data =
                new CompoundTag();

    }

    public EconomyData(
            CompoundTag tag
    ){

        data =
                tag;

    }

    public static EconomyData get(
            ServerLevel level
    ){

        return level
                .getDataStorage()
                .computeIfAbsent(

                        EconomyData::load,

                        EconomyData::new,

                        DATA_NAME

                );

    }

    public static EconomyData load(
            CompoundTag tag
    ){

        return new EconomyData(
                tag
        );

    }

    public CompoundTag getData(){

        return data;

    }

    @Override
    public CompoundTag save(
            CompoundTag tag
    ){

        tag.merge(
                data
        );

        return tag;

    }

}
