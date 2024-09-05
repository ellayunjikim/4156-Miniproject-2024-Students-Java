package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
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
public class DepartmentUnitTests {

  /**
   * Sets up the initial test environment by creating test instances of 
   * the Department and Course classes. This method initializes a 
   * 'COMS' department and a 'PHYS' department, each with a predefined 
   * course. The course COMS3456 is created with a name, location, time, 
   * capacity, and an enrolled student count of 240.
   *  
   * <p>This method is executed once before all tests in the test class.
  */
  @BeforeAll
  public static void setupDepartmentForTesting() {
    Course coms3456 = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
    coms3456.setEnrolledStudentCount(240);
    HashMap<String, Course> courses = new HashMap<>();
    courses.put("3456", coms3456);
    testDept = new Department("COMS", courses, "Luca Carloni", 200);
    physDept = new Department("PHYS", courses, "Tester 2", 0);
  }


  @Test
  public void toStringTest() {
    String expectedCourse = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    String expectedResult = "COMS 3456: " + expectedCourse + "\n";
    assertEquals(expectedResult, testDept.toString());
  }

  @Test
  public void getNumberOfMajorsTest() {
    int expectedResult = 200;
    assertEquals(expectedResult, testDept.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    String expectedResult = "Luca Carloni";
    assertEquals(expectedResult, testDept.getDepartmentChair());
  }

  @Test
  public void addPersonToMajorTest() {
    int expectedResult = 201;
    testDept.addPersonToMajor();
    assertEquals(expectedResult, testDept.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorTest() {
    testDept.dropPersonFromMajor();
    assertEquals(199, testDept.getNumberOfMajors());
    physDept.dropPersonFromMajor();
    assertEquals(0, physDept.getNumberOfMajors());
  }

  @Test
  public void creatCourseTest() {
    testDept.createCourse("4200", "Ella Kim", "CSB 451", "4:10-6:40", 100);
    String expectedResult = "COMS 3456: " 
        + "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55\n" 
        + "COMS 4200: \nInstructor: Ella Kim; Location: CSB 451; Time: 4:10-6:40\n";
    assertEquals(expectedResult, testDept.toString());
  }

  /** The test department instances used for testing. */
  public static Department testDept;
  public static Department physDept;
}

