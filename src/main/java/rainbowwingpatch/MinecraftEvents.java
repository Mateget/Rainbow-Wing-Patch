package rainbowwingpatch;

import java.util.ArrayList;

import com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon;
import com.pixelmonmod.pixelmon.enums.forms.IEnumForm;

import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class MinecraftEvents {
		
	public MinecraftEvents() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
    public void onPlayerRightClickEntity(PlayerInteractEvent.EntityInteractSpecific event){
		if(FileHandler.config.isEnabled()) {
			if(!event.getItemStack().isEmpty()) {
	        	if(event.getItemStack().getItem().getRegistryName().toString().equals("pixelmon:rainbow_wing")) {
	        		Entity entity = event.getTarget();
	        		if(entity instanceof EntityPixelmon) {
	            		EntityPixelmon pixelmon = (EntityPixelmon) entity;
	            		ArrayList<IEnumForm> enumlist = (ArrayList<IEnumForm>) pixelmon.getSpecies().getPossibleForms(true);
	            		boolean isRainbow = false;
	            		for(IEnumForm form : enumlist) {
	            			if(form.getLocalizedName().equals("Rainbow")) {
	            				isRainbow = true;
	            				return;
	            			}
	            		}
	            		if(!isRainbow) event.setCanceled(true);

	            	}
	        	}
	        }
		}
        
    }
}
