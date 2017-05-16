package at.jku.csi.marschaller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import at.jku.csi.cdi.Service;

@Service
public class DateMarshaller extends XmlAdapter<String, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

	@Override
	public Date unmarshal(String dateAsString) throws Exception {
		return dateAsString == null ? null : dateFormat.parse(dateAsString);
	}

	@Override
	public String marshal(Date date) throws Exception {
		return date == null ? null : dateFormat.format(date);
	}

}
