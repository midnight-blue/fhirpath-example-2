package org.midnightblue.fhirpathexample;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.fhirpath.IFhirPath;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.InstantType;
import org.hl7.fhir.r4.model.StringType;

import java.util.Date;
import java.util.Optional;

public class FhirParser {
  private FhirContext ctx = FhirContext.forR4();
  private IFhirPath fhirPath = ctx.newFhirPath();

  public Date getTimestamp(String receipt) {
    Bundle quittung = (Bundle) ctx.newXmlParser().parseResource(receipt);

    // Initialize FHIRPath evaluator
    Optional<InstantType> timestamp =
        fhirPath.evaluateFirst(quittung, "Bundle.timestamp", InstantType.class);

    return timestamp.map(InstantType::getValue).orElse(null);
  }

  public String getSerialNumber(String receipt) {
    Bundle quittung = (Bundle) ctx.newXmlParser().parseResource(receipt);

    // Initialize FHIRPath evaluator
    fhirPath.evaluateFirst(quittung, "Bundle.timestamp", InstantType.class);

    Optional<StringType> serialNumber =
        fhirPath.evaluateFirst(quittung, "Bundle.entry.resource.ofType(Device).serialNumber",
            StringType.class);

    return serialNumber.map(StringType::getValue).orElse(null);
  }
}
