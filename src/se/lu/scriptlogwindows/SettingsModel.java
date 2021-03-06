package se.lu.scriptlogwindows;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import se.lu.scriptlogwindows.settingskeeper.SettingsKeeper;
import se.lu.scriptlogwindows.util.ScriptLogConstants;

/**
 *
 * @author johanf
 */
class SettingsModel {

    private String workingDir;
    private String defaultLanguage;
    private int showTimeIn;
    private TestType testType;
    private boolean emulateEyesUsingMouse;

    public SettingsModel() {
        SettingsKeeper sk = SettingsKeeper.INSTANCE;
        Preferences prefs = sk.getPrefs();
        workingDir = prefs.get(ScriptLogConstants.SETTINGS_WORKING_DIR, "unset");
        defaultLanguage
                = prefs.get(ScriptLogConstants.SETTINGS_DEFAULT_LANGUAGE, "Swedish");
        showTimeIn = Integer.parseInt(
                prefs.get(ScriptLogConstants.SETTINGS_SHOW_TIME_IN, "0"));
        testType = new TestType("default");
        emulateEyesUsingMouse = Boolean.parseBoolean(
                prefs.get(ScriptLogConstants.SETTINGS_MOUSE_EMUL_EYES, "True"));
    }

    /**
     * @return the workingDir
     */
    public String getWorkingDir() {
        return workingDir;
    }

    /**
     * @param workingDir the workingDir to set
     */
    public void setWorkingDir(String workingDir) {
        this.workingDir = workingDir;
    }

    /**
     * @return the defaultLanguage
     */
    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    /**
     * @param defaultLanguage the defaultLanguage to set
     */
    public void setDefaultLanguage(String defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    /**
     * @return the showTimeIn
     */
    public int getShowTimeIn() {
        return showTimeIn;
    }

    /**
     * @param showTimeIn the showTimeIn to set
     */
    public void setShowTimeIn(int showTimeIn) {
        this.showTimeIn = showTimeIn;
    }

    /**
     * @return the testType
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * @param testType the testType to set
     */
    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    /**
     * @return the emulateEyesUsingMouse
     */
    public boolean isEmulateEyesUsingMouse() {
        return emulateEyesUsingMouse;
    }

    /**
     * @param emulateEyesUsingMouse the emulateEyesUsingMouse to set
     */
    public void setEmulateEyesUsingMouse(boolean emulateEyesUsingMouse) {
        this.emulateEyesUsingMouse = emulateEyesUsingMouse;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        result.append(" Object {");
        result.append(newLine);

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(": ");
                //requires access to private field:
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            result.append(newLine);
        }
        result.append("}");

        return result.toString();
    }

    public String toShortString() {
        StringBuilder result = new StringBuilder();

        //determine fields declared in this class only (no fields of superclass)
        Field[] fields = this.getClass().getDeclaredFields();

        //print field names paired with their values
        for (Field field : fields) {
            try {
                //requires access to private field:
                //result.append(field.get(this)).append(" ");
                result.append(field.get(this)).append(",");
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(SettingsModel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(SettingsModel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        result.append(getTestType().getfTestTypeName());

        return result.toString();
    }

}
