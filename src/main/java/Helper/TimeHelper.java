package Helper;

import java.time.*;

public class TimeHelper {

    public static ZoneId getLocalZoneId(){
        return ZonedDateTime.now().getZone();
    }

    public static ZonedDateTime convertUTCToLocal(ZonedDateTime utcZonedDateTime){
        return utcZonedDateTime.withZoneSameInstant(getLocalZoneId());
    }

    public static ZonedDateTime convertESTToLocal(ZonedDateTime estZonedDateTime){
        return estZonedDateTime.withZoneSameInstant(getLocalZoneId());
    }

    public static ZonedDateTime convertLocalToUTC(ZonedDateTime localZonedDateTime){
        return localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    public static ZonedDateTime getLocalToUTCConversion(){
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
    }

    public static ZonedDateTime convertLocalToEST(ZonedDateTime localZonedDateTime){
        return localZonedDateTime.withZoneSameInstant(ZoneId.of("EST", ZoneId.SHORT_IDS));
    }

}
