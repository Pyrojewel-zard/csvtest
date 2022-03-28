//设计课程的格式
import java.sql.*;
public class CourseModel {
    String name;
    String teacher;
    int weekStart;//起始周
    int weekLength;//持续周长度
    String place;
    int dayOfWeek;//哪一天上课
    int timeStart;//开始时间节次
    int timeLength;//节次持续长度

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setWeekStart(int weekStart) {
        this.weekStart = weekStart;
    }

    public void setWeekLength(int weekLength) {
        this.weekLength = weekLength;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public String getName() {
        return name;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getWeekStart() {
        return weekStart;
    }

    public int getWeekLength() {
        return weekLength;
    }

    public String getPlace() {
        return place;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public int getTimeLength() {
        return timeLength;
    }
    public void printAll(){
        System.out.println(name+"+"+teacher+"+"+weekStart+"+"+weekLength+"+"+place+"+"+dayOfWeek+"+"+timeStart+"+"+timeLength);
    }
}
