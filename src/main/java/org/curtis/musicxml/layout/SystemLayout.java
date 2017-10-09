package org.curtis.musicxml.layout;

public class SystemLayout {
    private SystemMargins systemMargins;
    // TODO: system distance
    // TODO: top system distance
    private SystemDividers systemDividers;

    public SystemLayout() {

    }

    public SystemMargins getSystemMargins() {
        return systemMargins;
    }

    public void setSystemMargins(SystemMargins systemMargins) {
        this.systemMargins = systemMargins;
    }

    public SystemDividers getSystemDividers() {
        return systemDividers;
    }

    public void setSystemDividers(SystemDividers systemDividers) {
        this.systemDividers = systemDividers;
    }
}
