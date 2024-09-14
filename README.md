Name: Ella Kim
UNI: yk3040

# Static Bug Finder - PMD

This project utilizes PMD, a static code analysis tool, to detect potential bugs, code style issues, and performance problems in Java code. PMD helps to enforce best practices and improve overall code quality by scanning the source code for predefined patterns and rule violations.

## Running the Bug Finder

### Maven
Ensure that you're in the 4156-Miniproject-2024-Students-Java directory by running the following command: 
```bash 
cd 4156-Miniproject-2024-Students-Java
```
To execute PMD and generate a report (pmd-report.txt) containing the analysis results, use the command below (for MacOS):
```bash
pmd check -d ./IndividualProject -R rulesets/java/quickstart.xml -f text | sed -e 's/.\/IndividualProject\/src\/main\/java\/dev\/coms4156\/project\/individualproject/.../g' > pmd-report.txt 
```
This will check the code for any issues, but you should observe that no PMD issues are found with the current code.

Alternatively, you can run PMD directly with the following command:
```bash
pmd check -d ./4156-Miniproject-2024-Students-Java/IndividualProject -R rulesets/java/quickstart.xml -f text
```
