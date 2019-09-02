package org.curtis.ui.swing.input;

import org.curtis.properties.AppProperties;
import org.curtis.util.StringUtil;

public class ToPdf extends ToInput {
    public ToPdf() {
        setup();
    }

    private void setup() {
        setTitle("PDF File Output: ");

        InputRow inputRow1 = new InputRow();
        inputRow1.setText("Output Directory: ");
        inputRow1.setInputType(InputType.OUTPUT_DIRECTORY);
        inputRow1.setName("outputDirectory");
        getInputRows().add(inputRow1);

        InputRow inputRow2 = new InputRow();
        inputRow2.setText("Output Filename: ");
        inputRow2.setInputType(InputType.INPUT);
        inputRow2.setInputSize(InputRow.SMALL_INPUT_SIZE);
        inputRow2.setName("outputFile");
        getInputRows().add(inputRow2);

        if (StringUtil.isEmpty(AppProperties.getOptionalProperty("location.pdfreader"))) {
            InputRow inputRow3 = new InputRow();
            inputRow3.setInputType(InputType.LABEL);
            inputRow3.setValue("Set PDF Reader Location in Set Properties to open PDF file");
            getInputRows().add(inputRow3);
        } else {
            InputRow inputRow3 = new InputRow();
            inputRow3.setText("Open PDF file on completion: ");
            inputRow3.setInputType(InputType.CHECKBOX);
            inputRow3.setName("openPdf");
            getInputRows().add(inputRow3);
        }
    }
}
