package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
@TestMethodOrder(OrderAnnotation.class)  // Enforces test method execution order
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
    testDatabase = new MyFileDatabase(0, "./data.txt");
    HashMap<String, Department> departments = testDatabase.getDepartmentMapping();
    comsDepartment = departments.get("COMS");
  }


  @Test
  @Order(1)
  public void toStringTest() {
    String expectedResult = "COMS 3827: \n" 
         + "Instructor: Daniel Rubenstein; Location: 207 Math; Time: 10:10-11:25\n" 
         +  "COMS 1004: \n" 
         +  "Instructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55\n"
         +  "COMS 3203: \n" 
         +  "Instructor: Ansaf Salleb-Aouissi; Location: 301 URIS; Time: 10:10-11:25\n"
         +  "COMS 4156: \n"
         +  "Instructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25\n" 
         +  "COMS 3157: \n" 
         +  "Instructor: Jae Lee; Location: 417 IAB; Time: 4:10-5:25\n"
         +  "COMS 3134: \n" 
         +  "Instructor: Brian Borowski; Location: 301 URIS; Time: 4:10-5:25\n"
         +  "COMS 3251: \n"
         +  "Instructor: Tony Dear; Location: 402 CHANDLER; Time: 1:10-3:40\n" 
         +  "COMS 3261: \n"
         +  "Instructor: Josh Alman; Location: 417 IAB; Time: 2:40-3:55\n";
    assertEquals(expectedResult, comsDepartment.toString());
  }

  @Test
  @Order(2)
  public void getNumberOfMajorsTest() {
    int expectedResult = 2700;
    assertEquals(expectedResult, comsDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    String expectedResult = "Luca Carloni";
    assertEquals(expectedResult, comsDepartment.getDepartmentChair());
  }

  @Test
  @Order(3)
  public void addPersonToMajorTest() {
    comsDepartment.addPersonToMajor();
    assertEquals(2701, comsDepartment.getNumberOfMajors());
  }

  @Test
  @Order(4)
  public void dropPersonFromMajorTest() {
    comsDepartment.dropPersonFromMajor();
    assertEquals(2700, comsDepartment.getNumberOfMajors());
  }

  public static MyFileDatabase testDatabase;
  public static Department comsDepartment;
}

