package me.oscar0713.EaseManage.File;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.FileUtil;

public class Backup {
	private static String pluginPath = Bukkit.getWorldContainer().getPath();
	//private String prefix;
	private String pathSep = "/";
	public Backup() {
		//this.prefix = prefix;
	}
	
	
	public boolean backupWorlds() {
		File des = new File(pluginPath + "/easemanage_temp");
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
		
		ZipUtils zip = new ZipUtils(des.getAbsolutePath(),pluginPath + "/backup_test.zip");
		zip.createZip();
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
				copyFolder(file,new File(des_sub.getAbsolutePath() + pathSep +file.getName()));
			} else {
				FileUtil.copy(file, new File(des.getAbsolutePath() + pathSep + file.getName()));
			}
		}
	}
}
