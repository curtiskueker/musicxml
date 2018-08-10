package org.curtis.musicxml.score;

import org.curtis.database.DatabaseItem;
import org.curtis.musicxml.link.LinkAttributes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "work")
public class Work extends DatabaseItem {
    @Column(name = "work_number")
    private String workNumber;
    @Column(name = "work_title")
    private String workTitle;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "opus_id")
    private LinkAttributes opus;

    public Work() {

    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getWorkTitle() {
        return workTitle;
    }

    public void setWorkTitle(String workTitle) {
        this.workTitle = workTitle;
    }

    public LinkAttributes getOpus() {
        return opus;
    }

    public void setOpus(LinkAttributes opus) {
        this.opus = opus;
    }
}
