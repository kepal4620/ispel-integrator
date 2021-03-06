package ispel.integrator.service.dms;

import generated.Part;
import generated.PartsInvoiceLine;
import ispel.integrator.domain.dms.PartInfo;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class PartsInvoiceLinesBuilder {

    public class Builder {

        private List<PartInfo> parts;

        private Builder() {
        }

        public Builder withParts(List<PartInfo> parts) {
            this.parts = parts;
            return this;
        }

        public PartsInvoiceLine[] build() {
            if (parts == null) {
                throw new IllegalStateException("parts is null");
            }
            List<PartsInvoiceLine> lines = new ArrayList<PartsInvoiceLine>();
            for (PartInfo partInfo : parts) {
                PartsInvoiceLine line = new PartsInvoiceLine();
                line.setType(buildType(partInfo));
                Part part = new Part();
                part.setFranchiseName(buildFranchiseName(partInfo));
                part.setIsFranchise("A".equalsIgnoreCase(partInfo.getOriginal_nd()));
                part.setPartNumber(partInfo.getKatalog());
                line.setPart(part);
                line.setQuantity(partInfo.getMnozstvi().abs());
                line.setTotalCost(buildTotalCost(partInfo));
                line.setTotalPrice(partInfo.getCena_bdp().abs());
                line.setTotalListPrice(buildTotalListPrice(partInfo));
                lines.add(line);
            }
            return lines.toArray(new PartsInvoiceLine[lines.size()]);
        }

        private String buildFranchiseName(PartInfo partInfo) {
            if (partInfo.isNissan()) {
                return "nissan";
            } else {
                return "other";
            }
        }

        private BigDecimal buildTotalCost(PartInfo partInfo) {
            if ("A".equalsIgnoreCase(partInfo.getOstatni())) {
                if (BigDecimal.ZERO.compareTo(partInfo.getCena_skl()) == 0) {
                    return partInfo.getCena_bdp().abs();
                } else {
                    return partInfo.getCena_skl().multiply(partInfo.getMnozstvi()).abs();
                }
            } else {
                return partInfo.getCena_skl().multiply(partInfo.getMnozstvi()).abs();
            }
        }

        private BigDecimal buildTotalListPrice(PartInfo partInfo) {
            if ("A".equalsIgnoreCase(partInfo.getOstatni())) {
                return partInfo.getCena_bdp().abs();
            } else {
                return partInfo.getCena_dopor().multiply(partInfo.getMnozstvi()).abs();
            }
        }

        private String buildType(PartInfo partInfo) {
            /*
            if ("PP".equalsIgnoreCase(orderInfo.getForma_uhr())) {
                return "invoice";
            } else if ("A".equalsIgnoreCase(orderInfo.getStorno())) {
                return "return";
            } else {
                return "credit";
            }
            */
            /*
            if ("A".equalsIgnoreCase(orderInfo.getStorno())) {
                return "credit";
            } else {
                return "invoice";
            }
            */
            if (partInfo.getCena_bdp().compareTo(BigDecimal.ZERO) >= 0) {
                return "invoice";
            } else {
                return "credit";
            }
        }
    }

    public Builder newInstance() {
        return new Builder();
    }
}
