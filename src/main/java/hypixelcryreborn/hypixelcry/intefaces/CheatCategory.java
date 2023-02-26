package hypixelcryreborn.hypixelcry.intefaces;

import imgui.type.ImBoolean;

import java.util.ArrayList;

public class CheatCategory {
    private String _name;
    private String _server;

    public CheatCategory(String name, String server) {
        _name = name;
        _server = server;
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
