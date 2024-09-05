package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * A Spring Boot test.
 * 
 * <p>Used to test Spring Boot components and configurations in a Spring application context.
 * The test class {@code CourseUnitTests} is run within the Spring context to allow testing
 * of Spring-managed components.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeAll
  public static void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }


  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest() {
    testCourse.setEnrolledStudentCount(250);
    assertEquals(false, testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(100);
    assertEquals(true, testCourse.enrollStudent());
  }

  @Test
  public void dropStudentTest() {
    testCourse.setEnrolledStudentCount(240);
    assertEquals(true, testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(0);
    assertEquals(false, testCourse.dropStudent());
  }

  @Test
  public void isCourseFullTest() {
    testCourse.setEnrolledStudentCount(0);
    assertEquals(false, testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(250);
    assertEquals(true, testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(251);
    assertEquals(true, testCourse.isCourseFull());
  }

  @Test
  public void getCourseLocationTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    String expectedResult = "Griffin Newbold";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void reassignInstructorTest() {
    String newInstructor = "Ella Kim";
    testCourse.reassignInstructor(newInstructor);
    assertEquals(newInstructor, testCourse.getInstructorName());
    testCourse.reassignInstructor("Griffin Newbold");
  }

  @Test
  public void reassignLocationTest() {
    String newLocation = "CSB 451";
    testCourse.reassignLocation(newLocation);
    assertEquals(newLocation, testCourse.getCourseLocation());
    testCourse.reassignLocation("417 IAB");
  }

  @Test
  public void reassignTimeTest() {
    String newTime = "1:00-2:20";
    testCourse.reassignTime(newTime);
    assertEquals(newTime, testCourse.getCourseTimeSlot());
    testCourse.reassignTime("11:40-12:55");
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

