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
// include all subclasses in SeeAlso, and give them all
// a no-arg default constructor
//@XmlSeeAlso({CaretUpdateRecordable.class,KeyPressedRecordable.class})
public class Recordable {

    private int eventID;
    private long when;

    public Recordable() {
    }

    public Recordable(int eventID, long when) {
        setEventID(eventID);
        setWhen(when);
    }

    public String dumpMe() {
        return "<recordable>" + getWhen();
    }

    public String linMe() {
        return "";
    }

    public long getWhenMs() {
        return when / 1000000;
    }

    /**
     * @return the when
     */
    public long getWhen() {
        return when;
    }

    /**
     * @param when the when to set
     */
    public void setWhen(long when) {
        this.when = when;
    }

    /**
     * @return the eventID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * @param eventID the eventID to set
     */
    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void xmlMe(XMLStreamWriter writer) throws XMLStreamException {
        writer.flush();
    }
}
