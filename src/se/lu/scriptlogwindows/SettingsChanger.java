package se.lu.scriptlogwindows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import se.lu.scriptlogwindows.settingskeeper.SettingsKeeper;
import se.lu.scriptlogwindows.util.ScriptLogConstants;

/**
 *
 * @author johanf
 */
class SettingsChanger implements PropertyChangeListener {

    public SettingsChanger() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsModel sei = (SettingsModel) evt.getNewValue();
        Maf.println("Settings..." + sei.toShortString());
        SettingsKeeper.INSTANCE.updateDirectoryPreferences(ScriptLogConstants.SETTINGS_WORKING_DIR, sei.getWorkingDir());
        SettingsKeeper.INSTANCE.updateDirectoryPreferences(ScriptLogConstants.SETTINGS_DEFAULT_LANGUAGE, sei.getDefaultLanguage());
        SettingsKeeper.INSTANCE.updateDirectoryPreferences(ScriptLogConstants.SETTINGS_SHOW_TIME_IN, sei.getShowTimeIn()+"");
        //SettingsKeeper.INSTANCE.updateDirectoryPreferences(ScriptLogConstants.SETTINGS_TEST_TYPE, sei.getTestType());
        SettingsKeeper.INSTANCE.updateDirectoryPreferences(ScriptLogConstants.SETTINGS_MOUSE_EMUL_EYES, sei.isEmulateEyesUsingMouse()+"");

    }

}
