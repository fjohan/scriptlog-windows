/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.lu.scriptlogwindows.recordable;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author johanf
 */
public class KeyReleasedRecordable extends Recordable {

    private int keyCode;

    public KeyReleasedRecordable(int eventID, long when, int keyCode) {
        super(eventID, when);
        setKeyCode(keyCode);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<keyReleased>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getKeyCode()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    /**
     * @return the keyCode
     */
    public int getKeyCode() {
        return keyCode;
    }

    /**
     * @param keyCode the keyCode to set
     */
    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "keyReleased");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("key", getKeyCode() + "");
    }
}
