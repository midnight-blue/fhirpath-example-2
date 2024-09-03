package org.midnightblue.fhirpathexample;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FhirParserTest {

  @Test
  public void testTimestamp() throws IOException {
    InputStream stream =
        FhirParserTest.class.getClassLoader().getResourceAsStream("PZN_Nr1_ErxReceipt.xml");

    assert stream != null;

    String content = new String(stream.readAllBytes());
    Date timestamp = new FhirParser().getTimestamp(content);

    Date expected = Date.from(Instant.parse("2024-11-30T10:40:00Z"));
    assertEquals(expected, timestamp);
  }


  @Test
  public void testParse() throws IOException {
    InputStream stream =
        FhirParserTest.class.getClassLoader().getResourceAsStream("PZN_Nr1_ErxReceipt.xml");

    assert stream != null;

    String content = new String(stream.readAllBytes());
    String serialNumber = new FhirParser().getSerialNumber(content);

    assertEquals("1.4.0", serialNumber);
  }
}
