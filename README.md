# mingspetstore3
Automation project made for petstore3
As per the request I focused on the 1 positive user journey that involves creating, updating and deleting a pet from this petstore.

There is a basic application.properties file which contains just the URL for now (just in case we get a petstore3.1 or 4) so the URL can be changed which is where otherwise auth files etc could go.
It is powered by a LoadProperties.java file
Ensure that maven dependencies are imported (or set to auto-import) before attempting to execute

To execute the test do one of the following
Run the testrunner in src > test > java > TestRunners
Run a maven job (mvn test in cmdline or maven widget)
Execute the feature individually in features directory

Outcome of test can be seen in the output or there is a HTML output of the tests that you can view (through your browser etc)
[target > cucumber-reports]

Notes -
There is many issues with petstore 3, which do cause the test to fail at some steps (I have sent some gifs to the recruiter to display one of these issues), perhaps a Jira is needed :)
This is an issue with the petstore as I get inconsistent responses rather than the code itself
I chose to parameterize all of the values however they could easily be changed to not use parameters but rather generate values on the fly
There are some things that I have not done due to time constraints however they're relatively basic things (e.g. some hardcoded values that could easily be placed in an enum for good practise)
.gitignore has not been configured for this test so there is some extra files
