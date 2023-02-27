package hypixelcryreborn.hypixelcry.intefaces;

import hypixelcryreborn.hypixelcry.utils.server.ServerUtils;
import imgui.type.ImBoolean;

import java.util.ArrayList;

public class CheatCategory {
    private String _name;
    private String _server;
    private boolean _requiredServer = false;
    private ArrayList<CheatCategory> _categories = new ArrayList<>();

    public CheatCategory(String name, String server, boolean requiredServer) {
        _name = name;
        _server = server;
        _requiredServer = requiredServer;
    }
    public void AddCategory(CheatCategory category) {
        _categories.add(category);
    }
    public ArrayList<CheatCategory> GetCategories() {
        return _categories;
    }

    public boolean IsAvalible() {
        if (!IsRequiredServer()) {
            return true;
        }
        else if (ServerUtils.address.equals(GetServer()) && IsRequiredServer()) {
            return true;
        }
        return false;
    }

    public boolean IsRequiredServer() {
        return _requiredServer;
    }
    public String GetName() {
        return _name;
    }

    public String GetServer() {
        return _server;
    }

    public ArrayList<CheatModule> modules = new ArrayList<>();

    public ImBoolean enabled = new ImBoolean(false);
}
