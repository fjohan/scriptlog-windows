/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows;

/**
 *
 * @author johanf
 */
class ScriptLogInfo {

    private final String fText;
    private final String fText0;
    private final int fLength;
    private final boolean fSelected;
    private final boolean fSelected0;

    public ScriptLogInfo(String text, String text0, int length, boolean selected, boolean selected0) {
        fText = text;
        fText0 = text0;
        fLength = length;
        fSelected = selected;
        fSelected0 = selected0;
    }

    /**
     * @return the fText
     */
    public String getfText() {
        return fText;
    }

    /**
     * @return the fText0
     */
    public String getfText0() {
        return fText0;
    }

    /**
     * @return the fLength
     */
    public int getfLength() {
        return fLength;
    }

    /**
     * @return the fSelected
     */
    public boolean isfSelected() {
        return fSelected;
    }

    /**
     * @return the fSelected0
     */
    public boolean isfSelected0() {
        return fSelected0;
    }

}
