package com.example.shomarappstudents;

import android.os.Parcel;
import android.os.Parcelable;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LevelsAndStudentsResponse implements Parcelable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("message")
    @Expose
    private String message;

    protected LevelsAndStudentsResponse(Parcel in) {
        byte tmpSuccess = in.readByte();
        success = tmpSuccess == 0 ? null : tmpSuccess == 1;
        message = in.readString();
    }

    public static final Creator<LevelsAndStudentsResponse> CREATOR = new Creator<LevelsAndStudentsResponse>() {
        @Override
        public LevelsAndStudentsResponse createFromParcel(Parcel in) {
            return new LevelsAndStudentsResponse(in);
        }

        @Override
        public LevelsAndStudentsResponse[] newArray(int size) {
            return new LevelsAndStudentsResponse[size];
        }
    };

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (success == null ? 0 : success ? 1 : 2));
        parcel.writeString(message);
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("programme_id")
        @Expose
        private String programmeId;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("lesson_days")
        @Expose
        private List<LessonDay> lessonDays = null;
        @SerializedName("performance_keys")
        @Expose
        private List<PerformanceKey> performanceKeys = null;
        @SerializedName("active")
        @Expose
        private Boolean active;
        @SerializedName("lesson")
        @Expose
        private Lesson lesson;
        @SerializedName("students")
        @Expose
        private List<Student> students = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getProgrammeId() {
            return programmeId;
        }

        public void setProgrammeId(String programmeId) {
            this.programmeId = programmeId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<LessonDay> getLessonDays() {
            return lessonDays;
        }

        public void setLessonDays(List<LessonDay> lessonDays) {
            this.lessonDays = lessonDays;
        }

        public List<PerformanceKey> getPerformanceKeys() {
            return performanceKeys;
        }

        public void setPerformanceKeys(List<PerformanceKey> performanceKeys) {
            this.performanceKeys = performanceKeys;
        }

        public Boolean getActive() {
            return active;
        }

        public void setActive(Boolean active) {
            this.active = active;
        }

        public Lesson getLesson() {
            return lesson;
        }

        public void setLesson(Lesson lesson) {
            this.lesson = lesson;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

    }

    public class LessonDay {

        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("day")
        @Expose
        private String day;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

    }

    public class PerformanceKey {

        @SerializedName("active")
        @Expose
        private String active;
        @SerializedName("key")
        @Expose
        private String key;

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    public class Attendance  {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("student_id")
        @Expose
        private String studentId;
        @SerializedName("lesson_id")
        @Expose
        private String lessonId;
        @SerializedName("attendance_status")
        @Expose
        private String attendanceStatus;
        @SerializedName("start_point")
        @Expose
        private String startPoint;
        @SerializedName("end_point")
        @Expose
        private String endPoint;
        @SerializedName("performance")
        @Expose
        private int performance;
        @SerializedName("lesson")
        @Expose
        private String lesson;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        public String getLessonId() {
            return lessonId;
        }

        public void setLessonId(String lessonId) {
            this.lessonId = lessonId;
        }

        public String getAttendanceStatus() {
            return attendanceStatus;
        }

        public void setAttendanceStatus(String attendanceStatus) {
            this.attendanceStatus = attendanceStatus;
        }

        public String getStartPoint() {
            return startPoint;
        }

        public void setStartPoint(String startPoint) {
            this.startPoint = startPoint;
        }

        public String getEndPoint() {
            return endPoint;
        }

        public void setEndPoint(String endPoint) {
            this.endPoint = endPoint;
        }

        public int getPerformance() {
            return performance;
        }

        public void setPerformance(int performance) {
            this.performance = performance;
        }

        public String getLesson() {
            return lesson;
        }

        public void setLesson(String lesson) {
            this.lesson = lesson;
        }

    }

    public class Student{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("level_id")
    @Expose
    private String levelId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("active")
    @Expose
    private Boolean active;
    @SerializedName("attendance")
    @Expose
    private Attendance attendance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

}

    private class Lesson {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("level_id")
        @Expose
        private String levelId;
        @SerializedName("lesson_date")
        @Expose
        private String lessonDate;
        @SerializedName("title")
        @Expose
        private String title;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getLevelId() {
            return levelId;
        }

        public void setLevelId(String levelId) {
            this.levelId = levelId;
        }

        public String getLessonDate() {
            return lessonDate;
        }

        public void setLessonDate(String lessonDate) {
            this.lessonDate = lessonDate;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
