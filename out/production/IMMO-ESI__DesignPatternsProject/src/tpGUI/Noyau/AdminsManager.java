package tpGUI.Noyau;

import java.util.Map;
import java.util.TreeMap;

public class AdminsManager {

    private static Map<String, String> admins = new TreeMap<String, String>();

    public static boolean existeAdmin(String key, String val) {
        if(admins.containsKey(key)) {
            if(((String)admins.get(key)).equals(val)) return true;
            else return false;
        }
        else return false;
    }

    public static void createAdmins() {
        admins.put("20042000", "Yasmine");
        admins.put("01052001", "Saadia");
        admins.put("15062017", "Annabelle");
    }

}
