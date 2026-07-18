package com.yourname.admintools.client;

import com.yourname.admintools.features.EntityInspector;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = "AdminTools",
        value = Dist.CLIENT
)
public class EntityOverlay {


    @SubscribeEvent
    public static void onRender(RenderGuiEvent event) {


        Minecraft minecraft = Minecraft.getInstance();


        if (minecraft.player == null) {
            return;
        }


        Entity entity = minecraft.crosshairPickEntity;


        if (entity != null) {

            EntityInspector.setEntity(entity);

        }


        if (EntityInspector.getEntity() != null) {


            event.getGuiGraphics().drawString(
                    minecraft.font,

                    "Entity: "
                            + EntityInspector.getName()
                            + "\nUUID: "
                            + EntityInspector.getUUID()
                            + "\nHealth: "
                            + EntityInspector.getHealth()
                            + "\n"
                            + EntityInspector.getPosition(),

                    10,
                    10,

                    0xFFFFFF

            );

        }

    }

}
