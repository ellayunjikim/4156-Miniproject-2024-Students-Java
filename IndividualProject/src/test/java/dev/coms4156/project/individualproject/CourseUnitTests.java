package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
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

  /**
   * Sets up the course and test database for use in testing.
   * 
   * <p>This method initializes the test database by loading data from 
   * the specified file ("./data.txt") and retrieves the "COMS" department. 
   * From the "COMS" department, it selects the course "1004" to be used 
   * in test cases. This method is annotated with {@code @BeforeAll}, 
   * meaning it will run once before any of the tests in the class are executed.
   */
  @BeforeAll
  public static void setupCourseForTesting() {
    testDatabase = new MyFileDatabase(0, "./data.txt");
    Map<String, Department> departments = testDatabase.getDepartmentMapping();
    Department comsDept = departments.get("COMS"); 
    testCourse = comsDept.getCourseSelection().get("1004");
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void enrollStudentTest() {
    assertEquals(true, testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(400);
    assertEquals(false, testCourse.enrollStudent());
    testCourse.setEnrolledStudentCount(249);
  }

  @Test
  public void dropStudentTest() {
    assertEquals(true, testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(0);
    assertEquals(false, testCourse.dropStudent());
    testCourse.setEnrolledStudentCount(249);
  }

  @Test
  public void isCourseFullTest() {
    testCourse.setEnrolledStudentCount(0);
    assertEquals(false, testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(200);
    assertEquals(false, testCourse.isCourseFull());
    testCourse.setEnrolledStudentCount(400);
    assertEquals(true, testCourse.isCourseFull());
  }

  @Test
  public void getCourseLocationTest() {
    String expectedResult = "417 IAB";
    assertEquals(expectedResult, testCourse.getCourseLocation());
  }

  @Test
  public void getInstructorNameTest() {
    String expectedResult = "Adam Cannon";
    assertEquals(expectedResult, testCourse.getInstructorName());
  }

  @Test
  public void getCourseTimeSlotTest() {
    String expectedResult = "11:40-12:55";
    assertEquals(expectedResult, testCourse.getCourseTimeSlot());
  }

  @Test
  public void reassignInstructorTest() {
    String newInstructor = "Griffin Newbold";
    testCourse.reassignInstructor(newInstructor);
    assertEquals(newInstructor, testCourse.getInstructorName());
    testCourse.reassignInstructor("Adam Cannon");
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
  public static MyFileDatabase testDatabase;
}

