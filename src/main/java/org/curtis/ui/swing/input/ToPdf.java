package org.curtis.ui.swing.input;

import org.curtis.properties.PropertiesHandler;
import org.curtis.properties.PropertiesConstants;
import org.curtis.util.StringUtil;

public class ToPdf extends ToInput {
    public ToPdf() {
        setup();
    }

    private void setup() {
        setTitle("PDF File Output: ");

        getInputRows().add(InputRowFactory.newDirectory("outputDirectory", "Output Directory: "));
        getInputRows().add(InputRowFactory.newInput("outputFile", "Output Filename: ", InputRow.SMALL_INPUT_SIZE));

        if (StringUtil.isEmpty(PropertiesHandler.getOptionalProperty(PropertiesConstants.PDF_LOCATION)))
            getInputRows().add(InputRowFactory.newLabel("Set PDF Reader Location in Set Properties to open PDF file on completion"));
        else
            getInputRows().add(InputRowFactory.newCheckbox("openPdf", "Open PDF file on completion: "));
    }
}
