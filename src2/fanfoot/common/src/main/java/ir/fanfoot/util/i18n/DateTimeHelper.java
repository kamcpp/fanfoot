package ir.fanfoot.util.i18n;

import com.ibm.icu.text.DateFormat;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.TimeZone;
import com.ibm.icu.util.ULocale;

public class DateTimeHelper {

    public static String getPersianDateFromUtcEpoch(long epoch) {
        ULocale uLocale = new ULocale("fa_IR@calendar=persian");
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"), uLocale);
        cal.setTimeInMillis(epoch);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL, uLocale);
        return df.format(cal);
    }
}
