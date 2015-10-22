package se.lu.scriptlogwindows.settingskeeper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import javax.swing.filechooser.FileSystemView;
import se.lu.scriptlogwindows.Maf;

/**
 *
 * @author johanf
 *
 * This keeps all preferences in one class instead of spreading them out.
 * Currently implemented as a singleton.
 */
public enum SettingsKeeper {

    INSTANCE;
    private final Map directoryMap = new HashMap();
    private final Preferences prefs = Preferences.userNodeForPackage(getClass());

    public void setup() {
        Preferences topLevelPrefs = Preferences.userNodeForPackage(getClass());
        String wd = topLevelPrefs.get("workingDir", "unset");
        directoryMap.put("myWorkingDir", wd);
        getDirectories(wd);
    }

    private void getDirectories(String wd) {
        String[] directoryArray = {"openDocumentDir",
            "saveDocumentDir",
            "saveLINDir",
            "autoSaveDir",
            "openLogDir",
            "saveLogDir",
            "openExpConfDir",
            "saveExpConfDir",
            "masterSetupOpenDir",
            "masterSetupSaveDir",
            "batchStatsDir",
            "saveStatsDir",
            "openETDir",
            "analysisDir",
            "openOldLogDir",};

        for (String s : directoryArray) {
            getDirectoryMap().put(s, prefs.get(s, wd));
        }

        getDirectoryMap().put("defaultLanguageInit", prefs.get("defaultLanguage", "0"));
    }

    /**
     * @return the directoryMap
     */
    public Map getDirectoryMap() {
        return directoryMap;
    }

    public void updateDirectoryPreferences(String prefKey, String dirName) {
        if (!dirName.equals(getDirectoryMap().get(prefKey))) {
            getDirectoryMap().put(prefKey, dirName);
        }
        if (!dirName.equals(prefs.get(prefKey, ""))) {
            prefs.put(prefKey, dirName);
        }
    }

    /**
     * @return the prefs
     */
    public Preferences getPrefs() {
        return prefs;
    }

    public void initWorkingDir() {
        setup();
        Preferences prefs = getPrefs();
        // lin: /etc/.java
        // win: HKEY_LOCAL_MACHINE
        // mac: ???
        //Write Preferences information to HKLM (HKEY_LOCAL_MACHINE),
        //HKLM\ Software\ JavaSoft\ Prefs\ java\ util\ prefs because 
        //systemRoot's fully qualified
        //package name is java.util.prefs. 
        //prefs.remove("workingDir");
//        try {
//            prefs.removeNode();
//        } catch (BackingStoreException ex) {
//            Logger.getLogger(SettingsKeeper.class.getName()).log(Level.SEVERE, null, ex);
//        }

        String workingDirectory = prefs.get("workingDir", "unset");

        // global property set?
        if (workingDirectory.matches("unset")) {
            final Preferences systemPrefs = Preferences.systemNodeForPackage(getClass());
            String globalWorkingDir = systemPrefs.get("workingDir", "unset");
            //System.out.println(globalWorkingDir);
            if (!globalWorkingDir.matches("unset")) {
                prefs.put("workingDir", globalWorkingDir);
            }
        }

        // environment variable set?
        workingDirectory = prefs.get("workingDir", "unset");
        if (workingDirectory.matches("unset")) {
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
//                System.out.format("%s=%s%n",
//                        envName,
//                        env.get(envName));
                if (envName.matches("SCRIPTLOG_INITIALWD")) {
                    prefs.put("workingDir", env.get(envName));
//                    System.out.println(env.get(envName));
                }
            }
        }

        // construct one!
        workingDirectory = prefs.get("workingDir", "unset");
        if (workingDirectory.matches("unset")) {
            String userHome = FileSystemView.getFileSystemView().getDefaultDirectory().toString();
            workingDirectory = userHome
                    + System.getProperty("file.separator")
                    + "ScriptLogWD"
                    + System.getProperty("file.separator");
            prefs.put("workingDir", workingDirectory);
            //systemPrefs.put("workingDir", workingDirectory);
            //System.out.println(workingDirectory);
        }
        //Try to create it
        File wdFile = new File(workingDirectory);
        if (!wdFile.canWrite()) {
            wdFile.mkdir();
        }
        // oops we couldn't - ask user
        if (!wdFile.canWrite()) {
            Maf.println("Could not create " + workingDirectory);
            //openSettingsWindow(workingDirectory);
        }
    }
}
