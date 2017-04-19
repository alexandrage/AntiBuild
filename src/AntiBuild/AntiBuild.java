package AntiBuild;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
public class AntiBuild extends JavaPlugin {
	  Plugin plugin;
	  public void onEnable() {
		  plugin = this;
		  PluginManager pm = getServer().getPluginManager();
		  pm.registerEvents(new EventListener(this), this);
	  }
	  
	  public void onReload() {
		  
	  }
	  
	  public void onDisable() {
		  
	  }
}
