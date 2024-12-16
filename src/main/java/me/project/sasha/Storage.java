package me.project.sasha;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Storage {
    private Preferences prefs;

    public Storage() {
        prefs = Preferences.userNodeForPackage(Storage.class);
    }

    public void saveData(String key, String value) {
        prefs.put(key, value);
    }

    public String getData(String key) {
        return prefs.get(key, "default"); // Второй параметр — значение по умолчанию
    }

    public void removeData(String key) {
        prefs.remove(key);
    }

    public void clear() {
        try {
            prefs.clear();
        } catch (BackingStoreException e) {
            System.out.println(e);
        }
    }

}

