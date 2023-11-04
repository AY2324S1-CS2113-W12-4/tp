# User Guide

## Introduction

Are you ready to embark on a journey towards a healthier, 
more active lifestyle? Introducing FitTrack, 
your ultimate fitness and nutrition companion. 
FitTrack is more than just an app; 
it's your personal guide to achieving your health and fitness goals.

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `FitTrack` from [here](https://github.com/AY2324S1-CS2113-W12-4/tp/releases).
3. You should find the jar file in your default downloads folder. Please place the jar file into a separate folder that will be used as your `home folder`.
4. Open a command terminal, and change the current working directory to the `home folder`.
5. Type ```java -jar fittrack.jar``` in the terminal to open the application. You should see the welcome message "Hi!" on the next line.
6. The application is now ready for you to use! Type `help` to see a list of commands that you will be able to use in the application.


## Features 

* [Viewing help : `help`](#View-Help-Guide-help)
* [Exiting the application : `exit`](#Exiting-the-application-exit)
* [Editing your profile : `editprofile`](#editing-your-profile-editprofile)
* [Viewing your profile : `viewprofile`](#viewing-your-profile-viewprofile)
* [Adding a Meal : `addmeal`](#adding-a-meal-addmeal)
* [Viewing list of all meals : `viewmeals`](#viewing-list-of-all-meals-viewmeals)
* [Checking your current bmi : `bmi`](#checking-your-current-bmi-bmi)
* [Delete a Meal : `deletemeal`](#delete-a-meal-deletemeal)
* [Adding a workout : `addworkout`](#adding-a-workout-addworkout)
* [Viewing list of workout : `viewWorkout`](#viewing-list-of-all-workouts-viewworkouts)
* [Delete a Workout : `deletework`](#delete-a-workout-deleteworkout)
* [Check weight range : `checkweightrange`](#check-weight-range-checkweightrange)
* [Check total calories burnt on a specific date : `caloriesburnt`](#check-total-calories-burnt-on-specific-date-caloriesburnt)
* [Check total calories burnt : `caloriesum`](#check-total-calories-burnt-caloriesum)
* [Find a meal : `findmeal`](#find-a-meal-findmeal)
* [Find a workout: `findworkout`](#find-a-workout-findworkout)
* [Save to File: `save`](#save-to-file-save)


### View Help Guide: `help`
Shows the list of commands with the command format and short explanation.

**Example of usage:**

```
help
```

**Expected output:**
```
`help` shows help message of the command.
Existing commands:
help, exit, editprofile, viewprofileaddmeal, deletemeal, viewmealsaddworkout, deleteworkout, viewworkouts
Type `help` or `help <COMMAND>` to view help.
```

### Exiting the application: `exit`
Exits Skippy Chat Bot application.

**Example of usage:**

```
exit
```

**Expected output:**
```
Goodbye! Hope to see you again soon!
```

### Editing Your Profile: `editprofile`
Allows user to edit their profile details.

Format: `editprofile h/<height> w/<weight> g/<gender> l/<calories>`

Example of usage: 
```
editprofile h/170 w/70 g/M l/100
```
Expected output:
```
I've edited the following:
Height: 170.0cm
Weight: 70.0kg
Daily calorie limit: 100.00
BMI: 100.00
Gender: Male
```

### Viewing your profile: `viewprofile`
Lists all profile settings and details.

Format: `viewprofile`

**Example of usage:**
```
viewprofile
```

**Expected output:**
```
Your Profile:
Height: 100.0cm
Weight: 100.0kg
Daily calorie limit: 1000kcal
BMI: 100.00
Gender: Male
```

### Checking your current BMI: `bmi`
Calculates your bmi based on your current height and weight, and tells you the category which your bmi falls under.

Format: `bmi`

**Example of usage:**
```
bmi
```

**Expected output:**
```
Your current BMI is 24.22
BMI falls under NORMAL WEIGHT category
```

### Adding a Meal: `addmeal`
Allows user to add meals they have consumed.

Format: `addmeal <meal> c/ <calories> d/ <date>`

Example of usage:
```
addmeal pasta c/ 200 d/ 2023-10-23
```
Expected output:
```
I've added the following meal:
[M] pasta (200.0kcal, 2023-10-23)
```

### Viewing List of All Meals: `viewmeals`
Lists all the meals.

Format: `viewmeals`

**Example of usage:**
```
viewwmeals
```

**Expected output:**
```
These are the meals you have consumed:
1.[M] pasta (200.0kcal, 2023-10-23)
```

### Delete a Meal: `deletemeal`
Allows user to delete a meal they have added.

Format: `deletemeal <index of meal>`

Example of usage:
```
deletemeal 1
```
Expected output:
```
I've deleted the following meal:
[M] pasta (200.0kcal, 2023-10-23)
```

### Adding a Workout: `addworkout`
Allows user to add workouts they have done.

Format: `addworkout <workout> c/ <calories>`

Example of usage:
```
addworkout running c/ 400 d/ 2023-10-23
```
Expected output:
```
I've added the following workout:
[W] running (400.0kcal, 2023-10-23)
```

### Viewing List of All Workouts: `viewworkouts`
Lists all the workouts.

Format: `viewworkouts`

**Example of usage:**
```
viewworkouts
```

**Expected output:**
```
These are the workouts you have done:
1.[W] running (400.0kcal, 2023-10-23)
```

### Delete a Workout: `deleteworkout`
Allows user to delete a workout they have added.

Format: `deleteworkout <index of workout>`

Example of usage:
```
deleteworkout 1
```
Expected output:
```
I've deleted the following workout:
[W] running (400.0kcal, 2023-10-23)
```

### Check Weight Range: `checkweightrange`
Allows user to check their weight range

Format: `checkweightrange`

Example of usage:
```
checkweightrange
```

Expected output:
```
Recommended Weight: 75.116 kg
```

### Check Total Calories Burnt On Specific Date: `caloriesburnt`
Allows user to check their weight range

Format: `caloriesburnt`

Example of usage:
```
caloriesburnt
```

Expected output:
```
Total calories burnt on 2023-10-25: 200cals
```

### Check Total Calories Burnt: `caloriesum`
Allows user to check the total calories burnt

Format: `caloriesum`

Example of usage:
```
caloriesum
```

Expected output:
```
Total Calories: 300kcals
```

### Find a Meal: `findmeal`
Allows user to search for a meal in their meal list

Format: `findmeal`

Example of usage:
```
findmeal pasta
```

Expected output:
```
These meals contain the keyword pasta:
1. [M] pasta         (200.0kcal, 2023-10-28)
2. [M] aglio alio pasta       (100.0kcal, 2023-10-29)
3. [M] cabonara pasta       (100.0kcal, 2023-10-29)
```

### Find a Workout: `findworkout`
Allows user to search for a workout in their workout list

Format: `findworkout`

Example of usage:
```
findworkout run
```

Expected output:
```
These workouts contain the keyword run:
1. [W] fast run   (100.0kcal, 2023-10-29)
2. [W] slow run   (20.0kcal, 2023-10-29)
```

### Save to File: `save`
Allows user to save profile data, meals and workouts to a text file

Format: `save`

Example of usage:
```
save
```

Expected output:
```
Your data has been saved!
```

## FAQ

**Q**: How do I edit my profile? 

**A**: Simply type editprofile, specify your height, weight and daily calories and hit enter. The App will update your details accordingly.

**Q**: How do I check if my bmi is normal?

**A**: Type bmi into the console and it will show you your current bmi and category.

**Q**: How do I save my data that I have added?

**A**: The program automatically saves all your data upon exiting or you can type save.

## Command Summary

* Help List `help`
* Exit Application `exit`
* Edit Profile `editprofile`
* View profile `viewprofile`
* Check BMI `bmi`
* Add Meal `addmeal`
* View all meals consumed `viewmeals`
* Delete Meal `deletemeal`
* Add Work `addworkout`
* View all workouts `viewworkouts`
* Delete Work `deleteworkout`
* Check your weight range `checkweightrange`
* Check total calories burnt on a specific date `caloriesburnt`
* Check total calories burnt `caloriesum`
* Find a meal in meal list `findmeal`
* Find a workout in workout list `findworkout`
* Save to file `save`

