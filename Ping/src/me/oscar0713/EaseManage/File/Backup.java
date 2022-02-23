package me.oscar0713.EaseManage.File;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.FileUtil;

public class Backup {
	private static String pluginPath = Bukkit.getWorldContainer().getPath();
	//private String prefix;
	
	private String pathSep = "/";
	private String tempFolderName = "easemanage_temp";
	private String pathToBackup = "EaseManageBackups";
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	private Date date;
	private Timestamp timestamp;
	
	public Backup() {
		File file = new File(pluginPath + pathSep + pathToBackup);
		if (!file.exists()) {
			file.mkdirs();
		}
		date = new Date();
		timestamp = new Timestamp(date.getTime());
		//this.prefix = prefix;
	}
	
	
	public boolean backupWorlds() {
		File des = new File(pluginPath +pathSep +tempFolderName);
		if (!des.exists()) {
			des.mkdirs();
		}
		List<World> worlds = Bukkit.getWorlds();
		for (World world : worlds) {
			File src = world.getWorldFolder();
			try {
				File des_world = new File(des.getAbsolutePath() + pathSep + world.getName());
				if (!des_world.exists()) {
					des_world.mkdirs();
				}
				copyFolder(src,des_world);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		ZipUtils zip = new ZipUtils(des.getAbsolutePath(),pluginPath + pathSep + pathToBackup +pathSep+ "backup_" + df.format(timestamp) + ".zip");
		zip.createZip();
		
		deleteDirectory(des);
		return true;
	}
	
	private void copyFolder(File src, File des) throws IOException{
		File[] files = src.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				File des_sub = new File(des.getAbsolutePath() + pathSep + file.getName());
				if (!des_sub.exists()) {
					des_sub.mkdirs();
				}
				copyFolder(new File(src.getAbsolutePath() + pathSep + file.getName()),des_sub);
			} else {
				FileUtil.copy(file, new File(des.getAbsolutePath() + pathSep + file.getName()));
			}
		}
	}
	
	private boolean deleteDirectory(File directory) {
		File [] allContents = directory.listFiles();
		if (allContents != null) {
			for (File file : allContents) {
				deleteDirectory(file);
			}			
		}
		return directory.delete();
	}
	
	public static void saveWorld() {
		Bukkit.savePlayers();
		for (World world : Bukkit.getWorlds()) {
			world.save();
		}
	}
}
