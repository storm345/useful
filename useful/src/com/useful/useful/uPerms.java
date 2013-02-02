package com.useful.useful;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;

public class uPerms {
	private useful plugin;
	public static HashMap<String, PermissionAttachment> permMap = new HashMap<String, PermissionAttachment>();
	public uPerms(useful instance){
		plugin = useful.plugin;
	}
	private FileConfiguration file = useful.uperms;
	//TODO
	public void checkPerms(Player player){
		String name = player.getName();
		PermissionAttachment attach = new PermissionAttachment(plugin, player);
		if(permMap.containsKey(name)){
			attach = permMap.get(name);
		}
		ConfigurationSection users = file.getConfigurationSection("users");
		if(users.getKeys(false).contains(name)){
			List<String> groups = users.getStringList(name + "/groups");
			for(int i = 0;i<groups.size();i++){
				String group = (String) groups.get(i);
				ConfigurationSection tGroups = file.getConfigurationSection("groups");
				Set<String> theGroups = tGroups.getKeys(false);
				for(String groupName:theGroups){
					if(group.equalsIgnoreCase(groupName)){
						group = groupName;
					PermissionAttachment thePerms = this.loadGroupPerms(group, player);
					Map<String, Boolean> map = thePerms.getPermissions();
					Set<String> keys = map.keySet();
					for(String t:keys){
						attach.setPermission(t, map.get(t));
					}
					}
				}
			}
			String personalSection = "users/" + name + "/permissions";
			ConfigurationSection personal = file.getConfigurationSection(personalSection);
			Set<String> perms = personal.getKeys(false);
			for(String perm:perms){
				boolean value = personal.getBoolean(perm);
					attach.setPermission(perm, value);
			}
			permMap.put(name, attach);
		}
		else{
			users.createSection(name+"/permissions");
			file.set("users/"+name+"/groups", Arrays.asList("default"));
			try {
				file.save(useful.upermsFile);
			} catch (IOException e) {
			}
			checkPerms(player);
		}
		return;
	}
	public void ReRegisterPerms(Player player){
		String name = player.getName();
		if(!(permMap.containsKey(name))){
			return;
		}
		player.recalculatePermissions();
		PermissionAttachment attach = permMap.get(name);
		Map<String,Boolean> perms = attach.getPermissions();
		Set<String> keys = perms.keySet();
		for(String v:keys){
            //TODO why isnt it working??
			player.addAttachment(plugin, v, perms.get(v));
			player.recalculatePermissions();
		}
		return;
	}
	public PermissionAttachment loadGroupPerms(String groupname, Player player){
		PermissionAttachment returnedPerms = new PermissionAttachment(plugin, player);
	ConfigurationSection groups = file.getConfigurationSection("groups");
	Set<String> tGroups = groups.getKeys(false);
	for(String v:tGroups){
		if(v.equalsIgnoreCase(groupname)){
			ConfigurationSection groupSect = groups.getConfigurationSection(groupname);
			if(groupSect.contains("/inheritance")){
				List<String> inherited = groupSect.getStringList("/inheritance");
				for(int i=0;i<inherited.size();i++){
					PermissionAttachment inheritedPerms = loadGroupPerms(inherited.get(i), player);
					Map<String, Boolean> map = inheritedPerms.getPermissions();
					Set<String> set = map.keySet();
					for(String u: set){
						returnedPerms.setPermission(u, map.get(u));
					}
				}
			}
			groupname = v;
			ConfigurationSection perms = groups.getConfigurationSection(groupname + "/permissions");
			Set<String> tPerms = perms.getKeys(false);
			for(String perm:tPerms){
				boolean value = perms.getBoolean(perm);
					returnedPerms.setPermission(perm, value);
			}
		}
	}
		return returnedPerms;
	}
	public void refreshPerms(Player player){
		String playername = player.getName();
		if(!player.isOnline()){
			return;
		}
		Set<PermissionAttachmentInfo> current = player.getEffectivePermissions();
		Object[] currentPerms = current.toArray();
		PermissionAttachment oldperms = new PermissionAttachment(plugin, player);
		for(int i=0;i<currentPerms.length;i++){
			PermissionAttachmentInfo info = (PermissionAttachmentInfo) currentPerms[i];
			PermissionAttachment old = info.getAttachment();
			Map<String, Boolean> map = null;
			try {
				map = old.getPermissions();
				if(map != null){
					Set<String> keys = map.keySet();
					for(String v:keys){
						oldperms.setPermission(v, map.get(v));
					}
				}
			} catch (Exception e) {
			}	
		}
		oldperms.remove();
		if(permMap.containsKey(playername)){
			try {
				player.removeAttachment(permMap.get(playername));
			} catch (Exception e) {
			}
			permMap.remove(playername);
		}
		PermissionAttachment attacher = player.addAttachment(plugin);
		permMap.put(playername, attacher);
		checkPerms(player);
		ReRegisterPerms(player);
		player.recalculatePermissions();
		return;
	}
	public void loadPerms(Player player){
		String playername = player.getName();
		PermissionAttachment attacher = player.addAttachment(plugin);
		permMap.put(playername, attacher);
		checkPerms(player);
		ReRegisterPerms(player);
		player.recalculatePermissions();
		return;
	}
    public void unLoadPerms(String playername){
    	//TODO
    	Player player = plugin.getServer().getPlayer(playername);
    	if(permMap.containsKey(playername)){
			try {
				player.removeAttachment(permMap.get(playername));
			} catch (Exception e) {
				//Did not have it set!
			}
			permMap.remove(playername);
		}
    	player.recalculatePermissions();
    	return;
    }
    public void setGroups(String playername, List<String> groups){
    	String name = playername;
    	ConfigurationSection users = file.getConfigurationSection("users");
    	if(users.getKeys(false).contains(name)){
    		Object[] names = users.getKeys(false).toArray();
    		for(int i=0;i<names.length;i++){
    			if(((String)names[i]).equalsIgnoreCase(name)){
    				name = (String) names[i];
    			}
    		}
    	}
    	users.set(name+"/groups", groups);
    	try {
			file.save(useful.upermsFile);
		} catch (IOException e) {
		}
    	return;
    }
    public void setPerm(String path, String perm, Object value){
    	file.set(path, value);
    	try {
			file.save(useful.upermsFile);
		} catch (IOException e) {
		}
    	return;
    }
    public void unSetPerm(String path, String perm){
    	file.set(path, null);
    	try {
			file.save(useful.upermsFile);
		} catch (IOException e) {
		}
    	return;
    }
    public void createGroup(String name, Map<String, Boolean> perms, List<String> inheritance){
    	ConfigurationSection group = file.getConfigurationSection("").createSection("groups/" + name);
    	ConfigurationSection permSect = group.createSection("/permissions");
    	Set<String> permList = perms.keySet();
    	for(String perm:permList){
    		permSect.set("/" + perm, perms.get(perm));
    	}
    		group.set("/inheritance", inheritance);
    		try {
    			file.save(useful.upermsFile);
    		} catch (IOException e) {
    		}
    	return;
    }
    public void removeGroup(String name){
    	file.set("groups/"+name, null);
    	try {
			file.save(useful.upermsFile);
		} catch (IOException e) {
		}
    	return;
    }
    public void unRegisterPlayer(String name){
    	ConfigurationSection users = file.getConfigurationSection("").createSection("users/");
    	Boolean contains = false;
    	Map<String, Object> vals = users.getValues(false);
    	Set<String> names = vals.keySet();
    	for(String tname:names){
    		if(tname.equalsIgnoreCase(name)){
    			contains = true;
    			name = tname;
    		}
    	}
    	if(contains){
    		users.set(name, null);
    	}
    	return;
    }
}
