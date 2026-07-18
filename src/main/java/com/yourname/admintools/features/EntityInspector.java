package com.yourname.admintools.features;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class EntityInspector {

    private static Entity selectedEntity;


    public static void setEntity(Entity entity) {

        selectedEntity = entity;

    }


    public static Entity getEntity() {

        return selectedEntity;

    }


    public static String getName() {

        if (selectedEntity != null) {

            return selectedEntity.getName().getString();

        }

        return "None";

    }


    public static String getUUID() {

        if (selectedEntity != null) {

            return selectedEntity.getUUID().toString();

        }

        return "None";

    }


    public static String getType() {

        if (selectedEntity != null) {

            return selectedEntity.getType()
                    .builtInRegistryHolder()
                    .key()
                    .location()
                    .toString();

        }

        return "None";

    }


    public static String getPosition() {

        if (selectedEntity != null) {

            return "X: "
                    + (int) selectedEntity.getX()
                    + " Y: "
                    + (int) selectedEntity.getY()
                    + " Z: "
                    + (int) selectedEntity.getZ();

        }

        return "None";

    }


    public static String getHealth() {

        if (selectedEntity instanceof LivingEntity living) {

            return living.getHealth()
                    + " / "
                    + living.getMaxHealth();

        }

        return "N/A";

    }

}
