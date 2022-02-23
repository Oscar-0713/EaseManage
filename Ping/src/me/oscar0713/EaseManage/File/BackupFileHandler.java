package me.oscar0713.EaseManage.File;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.util.FileUtil;

public class BackupFileHandler {
	private static String pluginPath = Bukkit.getWorldContainer().getPath();
	//private String prefix;
	
	private String pathSep = "/";
	private String tempFolderName = "easemanage_temp";
	private String pathToBackup = "EaseManageBackups";
	
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	private Date date;
	private Timestamp timestamp;
	
	public BackupFileHandler() {
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
	
	public int getNumberOfFiles() {
		File directory = new File (pluginPath + pathSep + pathToBackup);
		File [] allContents = directory.listFiles();
		return allContents.length;
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
	
	private static FileTime getFileCreationTime(File file) {
		if (!file.exists()) {
			return null;
		}
		try {
			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			return attr.creationTime();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	private File getOldestFile(File directory) throws IOException {
		File [] allContents = directory.listFiles();
		File oldest = null;
		for (int i = 0; i < allContents.length; i++) {
			File file = allContents[i];
			if (i == 0) {
				oldest = file;
			}
			int compareValue = getFileCreationTime(file).compareTo(getFileCreationTime(oldest));
			if (compareValue < 0) {
				oldest = file;
			}
		}
		return oldest;
	}
	
	public void deleteOldestBackup() {
		try {
			File file = getOldestFile(new File(pluginPath + pathSep + pathToBackup));
			file.delete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveWorld() {
		Bukkit.savePlayers();
		for (World world : Bukkit.getWorlds()) {
			world.save();
		}
	}
}
