/*
GanttProject is an opensource project management tool.
Copyright (C) 2004-2011 GanttProject Team

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
*/
package net.sourceforge.ganttproject.calendar;

import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.sourceforge.ganttproject.IGanttProject;
import net.sourceforge.ganttproject.task.TaskLength;
import net.sourceforge.ganttproject.time.TimeUnit;

/**
 * @author bard
 */
public class AlwaysWorkingTimeCalendarImpl extends GPCalendarBase implements
        GPCalendar {
    @Override
    public List<GPCalendarActivity> getActivities(Date startDate, Date endDate) {
        return Collections.singletonList((GPCalendarActivity) new CalendarActivityImpl(startDate,
                endDate, true));
    }

    @Override
    protected List<GPCalendarActivity> getActivitiesForward(Date startDate, TimeUnit timeUnit,
            long unitCount) {
        Date activityStart = timeUnit.adjustLeft(startDate);
        Date activityEnd = activityStart;
        while (unitCount-- > 0) {
            activityEnd = timeUnit.adjustRight(activityEnd);
        }
        return Collections.singletonList((GPCalendarActivity)new CalendarActivityImpl(
                activityStart, activityEnd, true));
    }

    @Override
    protected List<GPCalendarActivity> getActivitiesBackward(Date startDate, TimeUnit timeUnit,
            long unitCount) {
        Date activityEnd = timeUnit.adjustLeft(startDate);
        Date activityStart = activityEnd;
        while (unitCount-- > 0) {
            activityStart = timeUnit.jumpLeft(activityStart);
        }
        return Collections.singletonList((GPCalendarActivity) new CalendarActivityImpl(
                activityStart, activityEnd, true));
    }

    @Override
    public void setWeekDayType(int day, DayType type) {
        if (type == GPCalendar.DayType.WEEKEND) {
            throw new IllegalArgumentException(
                    "I am always working time calendar, I don't accept holidays!");
        }
    }

    @Override
    public DayType getWeekDayType(int day) {
        // Every day is a working day...
        return GPCalendar.DayType.WORKING;
    }

    @Override
    public Date findClosestWorkingTime(Date date) {
        // No days off, so given date is good
        return date;
    }

    @Override
    public void setPublicHoliDayType(int month, int date) {
        // Nothing to do, as this calendar does not support holidays
    }

    @Override
    public boolean isPublicHoliDay(Date curDayStart) {
        // Always return false, as this calendar does not support holidays
        return false;
    }

    @Override
    public boolean isNonWorkingDay(Date curDayStart) {
        // Always return false, as this calendar only has working days
        return false;
    }

    @Override
    public DayType getDayTypeDate(Date curDayStart) {
        return GPCalendar.DayType.WORKING;
    }

    @Override
    public boolean getOnlyShowWeekends() {
        // Weekends are always working days for this calendar
        return true;
    }

    @Override
    public void setOnlyShowWeekends(boolean onlyShowWeekends) {
        // Ignore onlyShowWeekends, since weekends are always
        // working days for this calendar
    }

    @Override
    public void setPublicHoliDayType(Date curDayStart) {
        // Nothing to do, as this calendar does not support holidays
    }

    @Override
    public void setPublicHolidays(URL calendar) {
        // Nothing to do, as this calendar does not support holidays
    }

    @Override
    public Collection<Date> getPublicHolidays() {
        // Return an empty collection, as there are no holidays in this calendar
        return Collections.emptyList();
    }

    @Override
    public void clearPublicHolidays() {
        // Nothing needs to be done
    }

    @Override
    public List<GPCalendarActivity> getActivities(Date startingFrom, TaskLength period) {
        return getActivities(startingFrom, period.getTimeUnit(), period
                .getLength());
    }

    @Override
    public GPCalendar copy() {
        return new AlwaysWorkingTimeCalendarImpl();
    }

    @Override
    public URL getPublicHolidaysUrl() {
        return null;
    }
}
