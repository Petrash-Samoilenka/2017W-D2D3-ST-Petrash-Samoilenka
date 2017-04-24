package main.java.news.parent.convertors;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class OffsetDateTimeXmlAdapter extends XmlAdapter<String, OffsetDateTime> {

  @Override
  public String marshal(OffsetDateTime arg0) throws Exception {    
    return arg0.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
  }

  @Override
  public OffsetDateTime unmarshal(String arg0) throws Exception { 
    return OffsetDateTime.parse(arg0);
  }    
}
