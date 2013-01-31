package com.useful.useful;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

public class uPerms {
	private useful plugin;
	public static HashMap<String, PermissionAttachment> permMap = new HashMap<String, PermissionAttachment>();
	public uPerms(useful instance){
		plugin = useful.plugin;
	}
	private FileConfiguration file = useful.uperms;
	//TODO
	public void checkPerms(String playername){
		Player player = plugin.getServer().getPlayer(playername);
		if(player == null){
			return;
		}
		String name = player.getName();
		if(permMap.containsKey(name)){
			permMap.remove(name);
		}
		PermissionAttachment attach = new PermissionAttachment(plugin, player);
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
			users.set(name + "/groups", Arrays.asList("default"));
		}
		return;
	}
	public void ReRegisterPerms(String playername){
		Player player = plugin.getServer().getPlayer(playername);
		if(player == null){
			return;
		}
		String name = player.getName();
		if(!(permMap.containsKey(name))){
			return;
		}
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
			groupname = v;
			ConfigurationSection perms = groups.getConfigurationSection(groupname + "/permissions");
			Set<String> tPerms = perms.getKeys(false);
			for(String perm:tPerms){
				boolean value = perms.getBoolean(perm);
					returnedPerms.setPermission(perm, value);
			}
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
		}
	}
		return returnedPerms;
	}

}
