# Static Bug Finder - PMD

This project utilizes PMD, a powerful static code analysis tool, to detect potential bugs, code style issues, and performance problems in Java code. PMD helps to enforce best practices and improve overall code quality by scanning the source code for predefined patterns and rule violations.

## Running the Bug Finder

### Maven
To run SpotBugs with Maven, execute the following command:

```bash
mvn pmd:pmd
```

Then direct to `target` directory consisting of the `pmd.html` file, indicating whether there are issues found or none.
