package Helper;

import java.time.*;

/**
 * Helper classed used to handle common time related tasked used throughout program.
 *
 * @author Robert Brod
 */
public class TimeHelper {

    /**
     * Retrieves local Zone ID to display in login form and is used for conversion in other methods.
     *
     * @return The local Zone ID.
     */
    public static ZoneId getLocalZoneId(){
        return ZonedDateTime.now().getZone();
    }

    /**
     * Converts UTC to local. UTC is the timezone used by database and Java Model Objects.
     *
     * @param utcZonedDateTime UTC time
     * @return                 Local time
     */
    public static ZonedDateTime convertUTCToLocal(ZonedDateTime utcZonedDateTime){
        return utcZonedDateTime.withZoneSameInstant(getLocalZoneId());
    }

    /**
     * Converts EST to local. EST is needed to ensure appointments are stored inside of business hours, as businesses hours
     * are relative to EST.
     *
     * @param estZonedDateTime EST time
     * @return                 Local time
     */
    public static ZonedDateTime convertESTToLocal(ZonedDateTime estZonedDateTime){
        return estZonedDateTime.withZoneSameInstant(getLocalZoneId());
    }

    /**
     * Converts local to UTC. This is needed when storing time in either database or Java Model Objects.
     *
     * @param localZonedDateTime Local time
     * @return                   UTC time
     */
    public static ZonedDateTime convertLocalToUTC(ZonedDateTime localZonedDateTime){
        return localZonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
    }

    /**
     * Gets current time in UTC. This is needed when storing time in either database or Java Model Objects.
     *
     * @return UTC time
     */
    public static ZonedDateTime getLocalToUTCConversion(){
        return ZonedDateTime.now().withZoneSameInstant(ZoneId.of("UTC"));
    }

    /**
     * Converts local to EST. EST is needed to ensure appointments are stored inside of business hours, as businesses hours
     * are relative to EST.
     *
     * @param localZonedDateTime Local time
     * @return                   EST time
     */
    public static ZonedDateTime convertLocalToEST(ZonedDateTime localZonedDateTime){
        return localZonedDateTime.withZoneSameInstant(ZoneId.of("EST", ZoneId.SHORT_IDS));
    }

}
