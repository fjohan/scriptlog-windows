/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.lu.scriptlogwindows;

import java.awt.Dimension;
import javax.swing.JInternalFrame;

/**
 *
 * @author ling-jfr
 */
public class FrameCenterer {

    public static void center(JInternalFrame aJif) {
        Dimension frameSize = aJif.getSize();
        Dimension deskSize = aJif.getParent().getSize();
        if (deskSize.width < frameSize.width) {
            deskSize.width = frameSize.width;
        }
        if (deskSize.height < frameSize.height) {
            deskSize.height = frameSize.height;
        }
        aJif.setLocation((deskSize.width - frameSize.width) / 2, (deskSize.height - frameSize.height) / 2);
    }
}
