/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.sj;

import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import se.lu.scriptlogwindows.sj.gui.ScriptJFrame;

/**
 *
 * @author johanf
 */
public class ScriptJ {

    private final ArrayList<ScriptJFrame> alSjf;

    public ScriptJ() {
        this.alSjf = new ArrayList<ScriptJFrame>();
    }

    public void newLog(JDesktopPane jdp) {
        ScriptJFrame sjf = new ScriptJFrame();
        alSjf.add(sjf);
        sjf.setTitle("ScriptJ - Frame " + alSjf.size());
        jdp.add(sjf, JLayeredPane.DEFAULT_LAYER);
        sjf.setVisible(true);

        //sjf.addPropertyChangeListener("PROP_SJLOGGING", new JLabel1Changer(jLabel1));

    }

    public void startRecording() {
        for (ScriptJFrame sjf : alSjf) {
            sjf.record();
        }
    }

    public void stopRecording() {
        for (ScriptJFrame sjf : alSjf) {
            sjf.stop();
        }

    }
}
