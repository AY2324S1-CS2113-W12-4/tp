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

Format: `editprofile h/<height> w/<weight> l/<calories>`

Example of usage: 
```
editprofile h/170 w/70 l/100
```
Expected output:
```
I've edited the following:
Height: 170.0
Weight: 70.0
Daily calorie limit: 100.0
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
Height: 180.0
Weight: 80.0
Daily calorie limit: 3000.0
```

### Checking your current BMI: `bmi`
Calculates your bmi based on your current height and weight

Format: `bmi`

**Example of usage:**
```
bmi
```

**Expected output:**
```
Your current BMI is 24.22
```

### Adding a Meal: `addmeal`
Allows user to add meals they have consumed.

Format: `addmeal <meal> c/ <calories>`

Example of usage:
```
addmeal pasta c/ 200
```
Expected output:
```
I've added the following meal:
Meal name: pasta 
Calories: 200.0
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
1.Meal name: pasta
Calories: 200.0

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
Meal name: pasta 
Calories: 200.0
```

### Adding a Workout: `addworkout`
Allows user to add workouts they have done.

Format: `addworkout <workout> c/ <calories>`

Example of usage:
```
addworkout running c/ 180
```
Expected output:
```
I've added the following workout:
Workout name: running 
Calories: 180.0
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
1.Workout name: running
Calories: 400.0
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
I've deleted workout 1
```

## FAQ

**Q**: How do I edit my profile? 

**A**: Simply type editprofile, specify your height, weight and daily calories and hit enter. The App will update your details accordingly.

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

