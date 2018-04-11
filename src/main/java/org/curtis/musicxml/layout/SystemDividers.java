package org.curtis.musicxml.layout;

import org.curtis.database.DatabaseItem;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "system_dividers")
public class SystemDividers extends DatabaseItem {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "left_divider_id")
    private PrintObjectStyleAlign leftDivider;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_divider_id")
    private PrintObjectStyleAlign rightDivider;

    public SystemDividers() {

    }

    public PrintObjectStyleAlign getLeftDivider() {
        return leftDivider;
    }

    public void setLeftDivider(PrintObjectStyleAlign leftDivider) {
        this.leftDivider = leftDivider;
    }

    public PrintObjectStyleAlign getRightDivider() {
        return rightDivider;
    }

    public void setRightDivider(PrintObjectStyleAlign rightDivider) {
        this.rightDivider = rightDivider;
    }
}
