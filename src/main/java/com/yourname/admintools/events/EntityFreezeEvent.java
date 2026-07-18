package com.yourname.admintools.events;

import net.minecraft.world.entity.LivingEntity;

import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.yourname.admintools.manager.EntityFreezeManager;

@Mod.EventBusSubscriber
public class EntityFreezeEvent {


    @SubscribeEvent
    public static void onLivingTick(
            LivingEvent.LivingTickEvent event
    ) {


        LivingEntity entity = event.getEntity();


        if (!EntityFreezeManager.isFrozen(
                entity.getUUID()
        )) {
            return;
        }


        entity.setDeltaMovement(
                0.0D,
                0.0D,
                0.0D
        );


        entity.setNoGravity(true);


    }

}
