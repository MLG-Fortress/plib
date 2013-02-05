package de.jaschastarke.bukkit.lib.permissions;

import java.util.Arrays;
import java.util.Collection;

import org.bukkit.command.CommandSender;

import de.jaschastarke.bukkit.lib.Core;
import de.jaschastarke.minecraft.lib.permissions.IAbstractPermission;
import de.jaschastarke.minecraft.lib.permissions.IDynamicPermission;
import de.jaschastarke.minecraft.lib.permissions.IPermissionChild;

public class PermissionManager {
    //private Core plugin;
    public PermissionManager(final Core plugin) {
        //this.plugin = plugin;
    }
    public boolean hasPermission(final CommandSender player, final IAbstractPermission perm) {
        if (player.hasPermission(perm.getFullString()))
            return true;
        if (perm instanceof IPermissionChild)
            if (hasSomePermission(player, ((IPermissionChild) perm).getParentPermissions()))
                return true;
        return false;
    }
    public boolean hasPermission(final CommandSender player, final IDynamicPermission perm) {
        return hasSomePermission(player, perm.getPermissions());
    }
    public boolean hasSomePermission(final CommandSender player, final IAbstractPermission[] perms) {
        return hasSomePermission(player, Arrays.asList(perms));
    }
    public boolean hasSomePermission(final CommandSender player, final Collection<IAbstractPermission> perms) {
        for (IAbstractPermission perm : perms) {
            if (hasPermission(player, perm))
                return true;
        }
        return false;
    }
}
