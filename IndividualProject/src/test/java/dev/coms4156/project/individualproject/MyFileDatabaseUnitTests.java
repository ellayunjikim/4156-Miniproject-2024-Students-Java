package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * A Spring Boot test.
 * 
 * <p>Used to test Spring Boot components and configurations in a Spring application context.
 * The test class {@code MyFileDatabaseUnitTests} is run within the Spring context to allow testing
 * of Spring-managed components.
 */
@SpringBootTest
@ContextConfiguration
public class MyFileDatabaseUnitTests {
  
  /**
   * Sets up the database and relevant items for testing.
   */
  @BeforeAll
  public static void setUp() {
    testDatabase = new MyFileDatabase(0, DATA_FILE);
    Map<String, Department> departments = testDatabase.getDepartmentMapping();
    comsDept = departments.get("COMS"); 
    courses = comsDept.getCourseSelection();
  }

  @Test
  public void testDeSerializeObjectFromInvalidFile() {
    MyFileDatabase invalidDatabase = new MyFileDatabase(0, INVALID_FILE);
    assertEquals(Collections.emptyMap(), invalidDatabase.deSerializeObjectFromFile());
  }

  @Test
  public void testSetAndGetMapping() {
    Map<String, Department> departmentMapping = new HashMap<>();
    Department newDept = new Department("Engineering", courses, "Ella Kim", 50);
    departmentMapping.put("Engineering", newDept);
    testDatabase.setMapping(departmentMapping);
    assertEquals(departmentMapping, testDatabase.getDepartmentMapping());
  }

  @Test
  public void toStringTest() {
    Map<String, Department> departmentMapping = new HashMap<>();
    Department newDept = new Department("Engineering", courses, "Ella Kim", 50);
    departmentMapping.put("Engineering", newDept);
    testDatabase.setMapping(departmentMapping);
    String expectedString = "For the Engineering department: \n" + newDept.toString();
    assertEquals(expectedString, testDatabase.toString());
  }

  /** The test course instance used for testing. */
  public static MyFileDatabase testDatabase;
  public static MyFileDatabase testDatabase2;
  public static Map<String, Course> courses;
  public static Department comsDept;
  public static String INVALID_FILE = "./empty.txt";
  public static String DATA_FILE = "./data.txt";
}

