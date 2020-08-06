package net.violantic.vc;

import net.milkbowl.vault.economy.Economy;
import net.violantic.vc.listener.DeathListener;
import net.violantic.vc.listener.ObeliskListener;
import net.violantic.vc.obelisk.Obelisk;
import net.violantic.vc.obelisk.ObeliskManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class HardcorePlugin extends JavaPlugin {

    public static final String PREFIX =
            ChatColor.translateAlternateColorCodes('&', "&d&lVextraHC »&r");

    private ObeliskManager obeliskManager;
    private Location obeliskLocation;
    private Material obeliskMaterial;
    private int obeliskHeight;

    private double killerPayment;
    private double globalPayment;
    private double deathPrice;
    private double obeliskPrice;
    private int banPeriodDays;

    private Economy economy;

    @Override
    public void onEnable() {
        initConfig();
        initVault();

        obeliskManager = new ObeliskManager(this);
        obeliskManager.register(new Obelisk(
                obeliskLocation,
                obeliskMaterial,
                obeliskPrice,
                obeliskHeight
        ));
    }

    public ObeliskManager getObeliskManager() {
        return obeliskManager;
    }

    public Location getObeliskLocation() {
        return obeliskLocation;
    }

    public Material getObeliskMaterial() {
        return obeliskMaterial;
    }

    public int getObeliskHeight() {
        return obeliskHeight;
    }

    public double getKillerPayment() {
        return killerPayment;
    }

    public double getGlobalPayment() {
        return globalPayment;
    }

    public double getDeathPrice() {
        return deathPrice;
    }

    public int getBanPeriodDays() {
        return banPeriodDays;
    }

    public Economy getEconomy() {
        return economy;
    }

    private void initConfig() {
        saveDefaultConfig();

        // Save obelisk values to memory

        double x = getConfig().getDouble("obelisk.x");
        double y = getConfig().getDouble("obelisk.y");
        double z = getConfig().getDouble("obelisk.z");
        String world = getConfig().getString("obelisk.world");
        obeliskHeight = getConfig().getInt("obelisk.height");

        String materialString = getConfig().getString("obelisk.material");
        try {
            obeliskMaterial = Material.valueOf(materialString);
        } catch (Exception e) {
            getServer().getLogger().log(Level.SEVERE, materialString + " is not a material");
        }

        obeliskLocation = new Location(Bukkit.getWorld(world), x, y, z);
        obeliskPrice = getConfig().getDouble("values.obelisk_price");

        // Save econ values to memory

        killerPayment = getConfig().getDouble("values.killer_pay");
        globalPayment = getConfig().getDouble("values.global_pay");
        deathPrice = getConfig().getDouble("values.death_price");
        banPeriodDays = getConfig().getInt("values.bantime");

        getServer().getPluginManager().registerEvents(new ObeliskListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);

    }

    public void updateObelisk(double x, double y, double z) {
        getConfig().set("obelisk.x", x);
        getConfig().set("obelisk.y", y);
        getConfig().set("obelisk.z", z);
    }

    private void initVault() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            System.out.println("Vault is not installed!");
            return;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            System.out.println("Economy service is null!");
        }
        economy = rsp.getProvider();
    }


}
