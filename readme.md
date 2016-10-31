# Infection

## How to run
- java -jar intput output
- input holds the test input data
- output is the list of infected people

## Input data
- there are 2 entities for this prject: people and course. and people are connected by courses
- two tables we are care about for this infection task:
- table 1: course instructor connection table:
Course_ID | People_ID
-------------------------
1         | 1
1         | 2
2         | 2
3         | 3
for course 1, there are 2 instructor: 1, and 2
for course 2, ther are 1 instructor: 2
- table 2: course student connection table:
Course_ID | People_ID
-------------------------
1         | 3
1         | 4
2         | 5
2         | 6
3         | 7
3         | 8
for course 1, there are 2 students: 3, and 4
for course 2, there are 2 students: 5, and 6


##Solution
- used 2 HashMap to record course/instructor and instructor/course connection
- used 2 HashMap to record course/student and student/course connection
- in total infection, use single work queue to go over all of infection people
- in limited infection, use to 2 work queues, people at same course will be add to infection list together, so when number of infected people exceeds max limited number, we only stoped at one course. (which means only one course which has half people get new feature, the other part not)

##Ideas for optimization: 
- support distributed data and process, people data could be stored in differet machine. we try to gether people who has similar coursed into one machine
- Dynamicly update infected people, not rebuild the whole list
- use course density(how many people take this course) to get optimise limit infection algorithm
