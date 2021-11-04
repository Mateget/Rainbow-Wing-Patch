package rainbowwingpatch;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class ReloadCommand extends CommandBase {

    @Override
    public String getName() {
        return "rainbowwingpatch";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "&cUsage: /rainbowwingpatch reload";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            FileHandler.readConfig();
            sender.sendMessage(new TextComponentString(TextFormatting.GREEN + "Config reloaded"));
            return;
        }
        sender.sendMessage(new TextComponentString(TextFormatting.RED + "Usage: /rainbowwingpatch reload"));
        
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        List<String> possibleArgs = new ArrayList<>();
        if (args.length == 1)
            possibleArgs.add("reload");
        return getListOfStringsMatchingLastWord(args, possibleArgs);
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return PermissionUtils.canUse(RainbowWingPatch.MOD_ID + ".reload", sender);
    }



}
