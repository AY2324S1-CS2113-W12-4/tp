# Faris - Project Portfolio Page


### Summary of Contributions

#### Key Contributions


* **Feature 1:**  Steps Class
    - Wrote the class for `Step` which was used to store the steps taken by the user as an object.
    - Wrote the methods to enable parsing, formatting and other operations required within the object.
    - Added on to the Parser and Storage classes to integrate this feature with the existing functionality of the codebase.


* **Feature 2:**  Add and Delete steps
    - What it does: allows user to add in their steps and view their steps for the day.
    - Calculate the number of steps taken in a particular day.
    - Delete steps that were added by mistake.
    - Justification: this feature helps user to track their steps and compare it with their daily goals.


* **Feature 3:**  List Steps
    - What it does: allows user to view the steps in a list. 
    - Delete steps that were added by mistake.
    - Justification: Enables the user to better decide on what operation they want to do. For example, deleting a step uses the index in the list as a reference and viewing the list of steps will allow them to carefully select which entry to delete.


* **Feature 4:** Calculate the calories consumed
    - What it does: Calculate the calories consumed based on the meals eaten.
    - Justification: Allows the user to keep track of their fitness goals through the calories consumed from meals.


* **Feature 5:** Provide suggestions on how many more steps need to be walked to reach the daily calorie goal.
    - What it does: Calculate the calories consumed based on the number of steps walked.
    - Justification: Allows the user to keep track of their calorie expenses through walking alone.


* **Feature 6:** Provide a suggestion on the ideal weight range for the user.
  - What it does: Calculates the ideal weight range for the user provided their height as entered in the user profile.
  - Justification: Allows the user to be mindful of their weight as they are on the journey to becoming fit.
  

* **JUnit Tests:** JUnit Testing of Code Written.
    - Made an active effort to write as many JUnit tests for the code I wrote as possible.
    - Made sure this was seamlessly integrated with the existing CI pipeline that we were working along.


  
#### Code Contributions to the tp
* **Code Contributed:** [RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=farissirraj&tabRepo=AY2324S1-CS2113-W12-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


* **Project Management:**
    - Contributed to the releases of the Project.
    - Open regular pull requests to update the team on the progress of the project.
    - Assigned pull request to certain issues of the project.
    - Contributed to unit testing of my written code.
    - Communicated with team members using Telegram and MS Teams video call to discuss the features integrated and the progress of the project.
    - Similar channels were used to do code reviews or pair programming for bug fixes when extensive integration with the codebase was required.


* **Documentation:**
    - User Guide:
        - Documented my added features to the user guide.
        - Added documentation for the features `addsteps`, `deletesteps`, `viewsteps`, `caloriesconsumed`, `getstepssuggestion`, `totalsteps` and `checkrecommendedweight` to allow the new user to start using this project.
    - Developer Guide:
        - Documented my added features to the developer guide.
        - Added documentation for the commands pertaining to the `Step` suite of commands which all inherit from the Command superclass to explain the functionality of the commands to another developer.