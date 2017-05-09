package ispel.integrator.service;


import ispel.integrator.adapter.AdapterRequest;
import ispel.integrator.adapter.Result;
import localhost.ImportSZV;
import localhost.ImportSZVResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

@Component
public class ImportSzvResultBuilder {

    @Autowired
    private Jaxb2Marshaller importSzvMarshallerFormattedOutput;

    public class Builder {

        private ImportSZV importSZV;
        private ImportSZVResponse importSZVResponse;
        private AdapterRequest adapterRequest;

        private Builder() {
        }

        public Builder withImportSZV(ImportSZV importSZV) {
            this.importSZV = importSZV;
            return this;
        }

        public Builder withImportSZVResponse(ImportSZVResponse importSZVResponse) {
            this.importSZVResponse = importSZVResponse;
            return this;
        }

        public Builder withAdapterRequest(AdapterRequest adapterRequest) {
            this.adapterRequest = adapterRequest;
            return this;
        }

        public Result build() {
            Result result = Result.getInstance(adapterRequest);
            if (importSZV != null) {
                result.setXmlInput(marshal(importSZV));
            }
            if (importSZVResponse == null) {
                result.setProcessed(Result.UNPROCESSED);
            } else if ("Zápis prebehol úspešne".equals(importSZVResponse.getImportSZVResult())) {
                result.setProcessed(Result.UNPROCESSED);
                result.setXmlOutput(marshal(importSZVResponse));
            } else {
                result.setProcessed(Result.UNPROCESSED);
                result.setErrorText(importSZVResponse.getImportSZVResult());
                result.setXmlOutput(marshal(importSZVResponse));
            }
            return result;
        }

        private String marshal(Object o) {
            StringWriter sw = new StringWriter();
            importSzvMarshallerFormattedOutput.marshal(o, new StreamResult(sw));
            return sw.toString();
        }
    }

    public ImportSzvResultBuilder.Builder newInstance() {
        return new Builder();
    }
}
