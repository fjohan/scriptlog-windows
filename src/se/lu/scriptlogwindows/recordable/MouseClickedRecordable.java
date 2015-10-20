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
public class MouseClickedRecordable extends Recordable {

    private int mouseX;
    private int mouseY;

    public MouseClickedRecordable(int eventID, long when, int mouseX, int mouseY) {
        super(eventID, when);
        setMouseX(mouseX);
        setMouseY(mouseY);
    }

//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<mouseClicked>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getMouseX()).append(KeyLogConstants.DUMP_SEPARATOR).append(getMouseY()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    /**
     * @return the mouseX
     */
    public int getMouseX() {
        return mouseX;
    }

    /**
     * @param mouseX the mouseX to set
     */
    public void setMouseX(int mouseX) {
        this.mouseX = mouseX;
    }

    /**
     * @return the mouseY
     */
    public int getMouseY() {
        return mouseY;
    }

    /**
     * @param mouseY the mouseY to set
     */
    public void setMouseY(int mouseY) {
        this.mouseY = mouseY;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "mouseClicked");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("mouseX", getMouseX() + "");
        writer.writeAttribute("mouseY", getMouseY() + "");
    }
}
