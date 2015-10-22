/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import se.lu.scriptlogwindows.settingskeeper.SettingsKeeper;

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
        SettingsKeeper.INSTANCE.updateDirectoryPreferences("workingDir", sei.getWorkingDir());

    }

}
