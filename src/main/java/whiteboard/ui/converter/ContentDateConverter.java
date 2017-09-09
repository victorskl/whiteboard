package whiteboard.ui.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        // 2017-09-10 00:42:38.131
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.S");

        if (s == null || s.isEmpty())
            return null;

        try {
            return format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
