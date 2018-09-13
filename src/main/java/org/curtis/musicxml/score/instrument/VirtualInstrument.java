package org.curtis.musicxml.score.instrument;

import org.curtis.database.DatabaseItem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "virtual_instrument")
public class VirtualInstrument extends DatabaseItem {
    @Column(name = "virtual_library")
    private String virtualLibrary;
    @Column(name = "virtual_name")
    private String virtualName;

    public VirtualInstrument() {

    }

    public String getVirtualLibrary() {
        return virtualLibrary;
    }

    public void setVirtualLibrary(String virtualLibrary) {
        this.virtualLibrary = virtualLibrary;
    }

    public String getVirtualName() {
        return virtualName;
    }

    public void setVirtualName(String virtualName) {
        this.virtualName = virtualName;
    }
}
