package AntiBuild;

import java.util.Map.Entry;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
public class EventListener implements Listener {
	AntiBuild plugin;
    public EventListener(AntiBuild instance) {
	      this.plugin = instance;
	}
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onPlayerInteractEvent(PlayerInteractEvent e) {
    	Player p = e.getPlayer();
    	if(p.isOp()) {
    		return;
    	}
    	if(e.getItem()!=null) {
        	ItemStack item = e.getItem();
        	String itemstr = item(item);
        	if(b(p, itemstr, item, "AntiBuild.Item.Interact.", "AntiBuild.Interact.Remove.")) {
        		e.setCancelled(true);
        		return;
        	}
    	}
    	if(e.getClickedBlock()!=null) {
        	Block item = e.getClickedBlock();
        	String itemstr = item(item);
        	if(b(p, itemstr, item, "AntiBuild.Block.Interact.")) {
        		e.setCancelled(true);
        	}
    	}
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onBlockPlaceEvent(BlockPlaceEvent e) {
    	Player p = e.getPlayer();
    	if(p.isOp()) {
    		return;
    	}
    	ItemStack item = e.getItemInHand();
    	String itemstr = item(item);
    	if(b(p, itemstr, item, "AntiBuild.Place.")) {
		    e.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void onBlockBreakEvent(BlockBreakEvent e) {
    	Player p = e.getPlayer();
    	if(p.isOp()) {
    		return;
    	}
    	Block item = e.getBlock();
    	String itemstr = item(item);
    	if(b(p, itemstr, item, "AntiBuild.Break.")) {
    		e.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void InventoryClick(InventoryClickEvent  e) {
    	Player p = (Player) e.getWhoClicked();
        if(p.isOp()) return;
    	if(e.getHotbarButton()!=-1) {
    		ItemStack item = p.getInventory().getContents()[e.getHotbarButton()];
    		if(item!=null) {
	    		String itemstr = item(item);
		    	if(b(p, itemstr, item, "AntiBuild.Click.", "AntiBuild.Click.Remove.")) {
			    	e.setCancelled(true);
		    	}
    		}
    	}
    	ItemStack item = e.getCurrentItem();
    	ItemStack itemc = e.getCursor();
    	if(item!=null) {
	    	String itemstr = item(item);
	    	if(b(p, itemstr, item, "AntiBuild.Click.", "AntiBuild.Click.Remove.")){
		    	e.setCancelled(true);
		    }
    	}
    	if(itemc!=null) {
	    	String itemstr = item(itemc);
	    	if(b(p, itemstr, item, "AntiBuild.Click.", "AntiBuild.Click.Remove.")) {
		    	e.setCancelled(true);
	    	}
    	}
    }
    
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = false)
    public void InventoryClick(InventoryDragEvent  e) {
    	Player p = (Player) e.getWhoClicked();
    	if(p.isOp()) return;
    	for(Entry<Integer, ItemStack> items : e.getNewItems().entrySet()) {
    		ItemStack item = items.getValue();
    		String itemstr = item(item);
	    	if(b(p, itemstr, item, "AntiBuild.Click.", "AntiBuild.Click.Remove.")) {
	    		e.setCancelled(true);
	    	}
    	}
    }
    
    String item(ItemStack item) {
		return item.getTypeId()+":"+item.getData().getData();
    }
    
    String item(Block item) {
		return item.getTypeId()+":"+item.getData();
    }
    
    boolean b(Player p, String item, ItemStack itemstr, String cancel, String remove) {
    	if(p.hasPermission(cancel+item) || p.hasPermission(remove+item)) { 
	    	p.sendMessage("§6"+item+" §4запрещен");
	    	if(p.hasPermission(remove+item)) {
	    		p.getInventory().remove(itemstr);
	    	}
	    	return true;
    	}
		return false;
    }
    
    boolean b(Player p, String item, ItemStack itemstr, String cancel) {
    	if(p.hasPermission(cancel+item)) { 
	    	p.sendMessage("§6"+item+" §4запрещен");
	    	return true;
    	}
		return false;
    }
    
    boolean b(Player p, String item, Block itemstr, String cancel) {
    	if(p.hasPermission(cancel+item)) { 
	    	p.sendMessage("§6"+item+" §4запрещен");
	    	return true;
    	}
		return false;
    }
}
