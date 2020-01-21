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
    private SystemDivider leftDivider;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_divider_id")
    private SystemDivider rightDivider;

    public SystemDividers() {

    }

    public SystemDivider getLeftDivider() {
        return leftDivider;
    }

    public void setLeftDivider(SystemDivider leftDivider) {
        this.leftDivider = leftDivider;
    }

    public SystemDivider getRightDivider() {
        return rightDivider;
    }

    public void setRightDivider(SystemDivider rightDivider) {
        this.rightDivider = rightDivider;
    }
}
