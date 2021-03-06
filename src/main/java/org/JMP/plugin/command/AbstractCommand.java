package org.JMP.plugin.command;

import org.JMP.plugin.Main;
import org.bukkit.command.*;
import java.util.ArrayList;
import java.util.List;


public abstract class AbstractCommand implements CommandExecutor, TabCompleter {

    public AbstractCommand(String command) {
        PluginCommand pluginCommand = Main.getInstance().getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);
        }

    }
    public abstract void execute(CommandSender sender, String label, String[] args);
    public List<String> complete(CommandSender sender, String[] args) {
        return null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        execute(commandSender, s, strings);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return filter(complete(commandSender, strings), strings);
    }

    private List<String> filter(List<String> list, String[] args) {
        if(list == null) return null;
        String last = args[args.length - 1];
        List<String> result = new ArrayList<>();
        for(String arg : list) {
            if (arg.toLowerCase().startsWith(last.toLowerCase())) result.add(arg);
        }
        return result;
    }
}
