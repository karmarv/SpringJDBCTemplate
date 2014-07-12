/**
 * 
 */
package com.rhl.vo;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Rahul Vishwakarma
 *
 */
public class DateTimeAdapter extends XmlAdapter<String, Timestamp> {
  
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    @Override
    public String marshal(Timestamp v) throws Exception {
        return dateFormat.format(v);
    }

    @Override
    public Timestamp unmarshal(String v) throws Exception {
        return new Timestamp(dateFormat.parse(v).getTime());
    }
}
