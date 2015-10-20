/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import se.lu.scriptlogwindows.directorykeeper.DirectoryKeeper;

/**
 *
 * @author johanf
 */
class SettingsChanger implements PropertyChangeListener {

    public SettingsChanger() {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SettingsInfo sei = (SettingsInfo) evt.getNewValue();
        Maf.println("Settings..." + sei.getfWorkingdir()
        );
        DirectoryKeeper.INSTANCE.updateDirectoryPreferences("workingDir", sei.getfWorkingdir());

    }

}
