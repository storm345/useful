package com.useful.useful;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public FileConfiguration getConfig(){
		return this.file;
	}
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
						if(t.equalsIgnoreCase("useful.*") && map.get(t)){
							attach.setPermission("useful.*", map.get(t));
							Map<String, Map<String, Object>> cmds = plugin.getDescription().getCommands();
							  Set<String> ikeys = cmds.keySet();
							  Object[] commandsavailable = ikeys.toArray();
							  for(int y=0;y<cmds.size();y++){
								  String v = commandsavailable[i].toString();
								  cmds.get(v);
								  Map<String, Object> vmap = cmds.get(v);
								  String perm = vmap.get("permission").toString();
								  attach.setPermission(perm, true);
							  }
						}
						else{
						attach.setPermission(t, map.get(t));
						}
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
		groupname = this.groupExists(groupname);
		if(groupname == "^^error^^"){
			return returnedPerms;
		}
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
			boolean validInherit = true;
			Map<String, Object> tgroups = file.getConfigurationSection("groups/").getValues(false);
	    	Set<String> groupnames = tgroups.keySet();
	    	for(String g : groupnames){
	    		if(g.equalsIgnoreCase(groupname)){
	    			groupname = g;
	    		}
	    	}
	    	if(validInherit){
	    	ConfigurationSection groupSec = groups.getConfigurationSection(groupname);
			if(!groupSec.contains("permissions")){
				validInherit = false;
			}
	    	}
			if(validInherit){
			ConfigurationSection perms = groups.getConfigurationSection(groupname + "/permissions");
			Set<String> tPerms = perms.getKeys(false);
			for(String perm:tPerms){
				boolean value = perms.getBoolean(perm);
					returnedPerms.setPermission(perm, value);
			}
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
    	file.set(path + "/" + perm, value);
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
    public void createGroup(String name, List<String> inheritance){
    	ConfigurationSection group = file.getConfigurationSection("").createSection("groups/" + name);
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
    public String groupExists(String gname){
    	Map<String, Object> groups = file.getConfigurationSection("groups/").getValues(false);
    	Set<String> groupnames = groups.keySet();
    	for(String g : groupnames){
    		if(g.equalsIgnoreCase(gname)){
    			return g;
    		}
    	}
    	String error = "^^error^^";
    	return error;
    }
    public List<String> listGroups(){
    	List<String> groups = new ArrayList<String>();
    	Set<String> keys = file.getConfigurationSection("groups/").getKeys(false);
    	Object[] array = keys.toArray();
    	for(int i=0;i<array.length;i++){
    		groups.add((String) array[i]);
    	}
    	return groups;
    }
    public Map<String, Object> viewPerms(String path){
    	Map<String, Object> result = new HashMap<String, Object>();
    	List<String> inherited = file.getStringList(path + "/inheritance");
    	if(!inherited.isEmpty()){
    		for(int i=0;i<inherited.size();i++){
    			String toInheritFrom = inherited.get(i);
    			toInheritFrom = plugin.permManager.groupExists(toInheritFrom);
    			boolean validInherit = true;
				if(toInheritFrom == "^^error^^"){
					validInherit = false;
				}
				if(validInherit){
    			Map<String, Object> inherit = viewPerms("groups/"+toInheritFrom);
    			Set<String> iKeys = inherit.keySet();
    			for(String perm:iKeys){
    				result.put(perm, inherit.get(perm));
    			}
				}
    		}
    	}
    	boolean hasPerms = true;
	    	ConfigurationSection groupSec = file.getConfigurationSection(path);
			if(!groupSec.contains("permissions")){
				hasPerms = false;
			}
			if(hasPerms){
			ConfigurationSection area = file.getConfigurationSection(path + "/permissions");
    	Map<String, Object> self = area.getValues(false);
    	Set<String> sKeys = self.keySet();
		for(String perm:sKeys){
			result.put(perm, self.get(perm));
		}
			}
    	return result;
    }
    
}
