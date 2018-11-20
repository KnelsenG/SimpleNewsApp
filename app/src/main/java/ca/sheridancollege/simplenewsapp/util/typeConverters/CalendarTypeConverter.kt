package ca.sheridancollege.simplenewsapp.util.typeConverters

import androidx.room.TypeConverter
import ca.sheridancollege.simplenewsapp.util.DateUtil
import java.util.*


class CalendarTypeConverter {

    companion object {

        @JvmStatic
        @TypeConverter
        fun toCalendar(calString: String?): Calendar? {
            if (calString.isNullOrBlank())
                return null
            val cal = Calendar.getInstance()
            try {
                cal.time = DateUtil.outSdf.parse(calString.replace("Z", ""))
            } catch (ex: Exception) {
                return null
            }
            return cal
        }

        @JvmStatic
        @TypeConverter
        fun fromCalendar(calendar: Calendar?): String? {
            if (calendar == null)
                return null
            return DateUtil.outSdf.format(calendar.time)
        }

    }

}
