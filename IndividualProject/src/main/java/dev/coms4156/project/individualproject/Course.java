package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a course within an educational institution.
 * This class stores information about the course, including its instructor,
 * location of course, time slot of course, and maximum number of students that an enroll.
 */
public class Course implements Serializable {

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * @param instructorName     The name of the instructor teaching the course.
   * @param courseLocation     The location where the course is held.
   * @param timeSlot           The time slot of the course.
   * @param capacity           The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    if (capacity < 0) {
      throw new IllegalArgumentException("Capacity cannot be negative");
    }
    this.courseLocation = courseLocation;
    this.instructorName = instructorName;
    this.courseTimeSlot = timeSlot;
    this.enrollmentCapacity = capacity;
    this.enrolledStudentCount = 0;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public synchronized boolean enrollStudent() {
    if (enrolledStudentCount == enrollmentCapacity) {
      return false;
    }
    enrolledStudentCount++;
    return true;
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public synchronized boolean dropStudent() {
    if (enrolledStudentCount == 0) {
      return false;
    }
    enrolledStudentCount--;
    return true;
  }


  public String getCourseLocation() {
    return this.courseLocation;
  }


  public String getInstructorName() {
    return this.instructorName;
  }


  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }
  
  /**
   * Returns a string representation of the course details.
   * The returned string includes the instructor's name, course location, and time slot, 
   * formatted with labels and separated by semicolons.
   *
   * @return a formatted string containing the instructor name, course location, and time slot.
   */
  @Override
  public String toString() {
    return "\nInstructor: " + instructorName 
        + "; Location: "  + courseLocation 
        + "; Time: " + courseTimeSlot;
  }

  /**
   * Reassigns the course instructor.
   *
   * @param newInstructorName the new name of the instructor
   */
  public void reassignInstructor(String newInstructorName) {
    this.instructorName = newInstructorName;
  }

  /**
   * Reassigns the location.
   *
   * @param newLocation the new location 
   */
  public void reassignLocation(String newLocation) {
    this.courseLocation = newLocation;
  }

  /**
   * Reassigns the course time.
   *
   * @param newTime the new time slot
   */
  public void reassignTime(String newTime) {
    this.courseTimeSlot = newTime;
  }

  /**
   * Set enrolledStudentCount between 0 and enrollmentCapacity.
   *
   * @param count Number of students for enrollment
   */
  public void setEnrolledStudentCount(int count) {
    if (count >= 0 && count <= enrollmentCapacity) {
      this.enrolledStudentCount = count;
    } else {
      this.enrolledStudentCount = -1;
    }
  }

  public boolean isCourseFull() {
    return enrollmentCapacity <= enrolledStudentCount;
  }

  @Serial
  private static final long serialVersionUID = 123456L;
  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String instructorName;
  private String courseTimeSlot;
}