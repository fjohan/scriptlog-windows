/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.sj;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JLabel;

/**
 *
 * @author johanf
 */
public class JLabel1Changer implements PropertyChangeListener {

    JLabel fLabel;

    public JLabel1Changer(JLabel aLabel) {
        fLabel = aLabel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Boolean b = (Boolean) evt.getNewValue();

        if (b) {
            fLabel.setText("Recording...");
        } else {
            fLabel.setText("Idle.");
        }

    }

}
