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
public class CaretUpdateRecordable extends Recordable {

    private int dot;
    private int mark;

    public CaretUpdateRecordable(int eventID, long when, int dot, int mark) {
        super(eventID, when);
        setDot(dot);
        setMark(mark);
    }

//    @Override
//    public String dumpMe() {
//        StringBuilder sb = new StringBuilder("");
//        sb.append("<caretUpdate>").append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getWhen()).append(KeyLogConstants.DUMP_SEPARATOR);
//        sb.append(getDot()).append(KeyLogConstants.DUMP_SEPARATOR).append(getMark()).append(KeyLogConstants.NEW_LINE);
//        return sb.toString();
//    }

    /**
     * @return the dot
     */
    public int getDot() {
        return dot;
    }

    /**
     * @param dot the dot to set
     */
    public void setDot(int dot) {
        this.dot = dot;
    }

    /**
     * @return the mark
     */
    public int getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.writeAttribute("type", "caretUpdate");
        writer.writeAttribute("time", getWhen() + "");
        writer.writeAttribute("dot", getDot() + "");
        writer.writeAttribute("mark", getMark() + "");
    }
}
