package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
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
public class RouteControllerUnitTests {
  /**
   * Sets up the initial test environment by creating test instances of 
   *  
   * <p>This method is executed once before all tests in the test class.
  */
  @BeforeAll
  public static void setupRouteForTesting() {
    routeController = new RouteController();
  }

  @Test
  public void indexTest() {
    assertEquals(routeController.index(),  
        "Welcome, in order to make an API call direct your browser or Postman to an endpoint "
        + "\n\n This can be done using the following format: \n\n http:127.0.0"
        + ".1:8080/endpoint?arg=value");
  }

  @Test
  public void retrieveDeptTest() {
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("PHYS").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("COMS").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("ECON").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("IEOR").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("CHEM").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("ELEN").getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveDepartment("PSYC").getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.retrieveDepartment("DNC").getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.retrieveDepartment("0").getStatusCode()
    );
  }

  @Test
  public void retrieveCourseTest() {
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveCourse("COMS", 1004).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveCourse("COMS", 3134).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveCourse("COMS", 3157).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveCourse("COMS", 3203).getStatusCode()
    );
    assertEquals(
        HttpStatus.OK,
        routeController.retrieveCourse("COMS", 4156).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.retrieveCourse("COMS", 1000).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.retrieveCourse("BAD", 4156).getStatusCode()
    );
  }

  @Test
  public void isCourseFullTest() {
    assertEquals(
        HttpStatus.OK,
        routeController.isCourseFull("COMS", 4156).getStatusCode()
    );
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.isCourseFull("ECON", 1000).getStatusCode()
    );
  }

  @Test
  public void getMajorCountFromDeptTest() {
    assertEquals(
        HttpStatus.OK,
        routeController.getMajorCtFromDept("COMS").getStatusCode()
    );
    assertEquals("There are: " + 2700 + " majors in the department", 
         routeController.getMajorCtFromDept("COMS").getBody());
    assertEquals(
        HttpStatus.NOT_FOUND,
        routeController.getMajorCtFromDept("BAD").getStatusCode()
    );
    assertEquals("Department Not Found", routeController.getMajorCtFromDept("BAD").getBody());
  }

  @Test
  public void identifyDeptChairTest() {
    assertEquals("Luca Carloni" + " is "
         + "the department chair.", routeController.identifyDeptChair("COMS").getBody());
    assertEquals(HttpStatus.OK, routeController.identifyDeptChair("COMS").getStatusCode());
    assertEquals("Department Not Found", routeController.identifyDeptChair("BAD").getBody());
  }

  @Test
  public void findCourseLocationTest() {
    assertEquals("501 NWC" + " is where the course "
        + "is located.", routeController.findCourseLocation("COMS", 4156).getBody());
    assertEquals(HttpStatus.OK, routeController.findCourseLocation("COMS", 4156).getStatusCode());
    assertEquals("Course Not Found", routeController.findCourseLocation("BAD", 3000).getBody());
    assertEquals(HttpStatus.NOT_FOUND, 
         routeController.findCourseLocation("BAD", 3000).getStatusCode());
  }

  public static RouteController routeController;
}