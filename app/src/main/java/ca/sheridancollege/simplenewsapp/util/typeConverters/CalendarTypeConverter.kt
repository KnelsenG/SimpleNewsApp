package ca.sheridancollege.simplenewsapp.util.typeConverters

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.*


class CalendarTypeConverter {

    companion object {

        private val sdf = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.CANADA)

        @JvmStatic
        @TypeConverter
        fun toCalendar(calString: String?): Calendar? {
            if (calString.isNullOrBlank())
                return null
            val cal = Calendar.getInstance()
            try {
                cal.time = sdf.parse(calString.replace("Z", "").replace("T", "-"))
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
            return sdf.format(calendar.time)
        }

    }

}
