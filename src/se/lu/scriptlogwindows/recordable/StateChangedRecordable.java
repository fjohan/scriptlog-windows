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
public class StateChangedRecordable extends Recordable {

    private int viewX;
    private int viewY;

    public StateChangedRecordable(int eventID, long when, int viewX, int viewY) {
        super(eventID, when);
        setViewX(viewX);
        setViewY(viewY);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<stateChanged>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getViewX()).append(KeyLogConstants.DUMP_SEPARATOR).append(getViewY()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    /**
     * @return the viewX
     */
    public int getViewX() {
        return viewX;
    }

    /**
     * @param viewX the viewX to set
     */
    public void setViewX(int viewX) {
        this.viewX = viewX;
    }

    /**
     * @return the viewY
     */
    public int getViewY() {
        return viewY;
    }

    /**
     * @param viewY the viewY to set
     */
    public void setViewY(int viewY) {
        this.viewY = viewY;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "stateChanged");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("viewX", getViewX() + "");
        writer.writeAttribute("viewY", getViewY() + "");
    }
}
