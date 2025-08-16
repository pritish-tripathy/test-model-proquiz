- ### Creating the SQL Database
```CREATE DATABASE quizprodb;```

- ### Inserting a Teacher Record
```
INSERT INTO myusers (user_id, city, email, password, role, username) VALUES (101, 'blr', 'myexample@mailinator.com', 'hello', 'teacher', 'Pritish Tripathy');
```

- ### Inserting a Student Record
```
INSERT INTO myusers (user_id, city, email, password, role, username) VALUES (110, 'blr', 'mystudent@mailinator.com', 'myhello', 'student', 'Sumit Singh');
```

- ### Inserting A Course
```dtd
INSERT INTO mycourses (course_name) VALUES ('Core Java');
```

- ### Inserting the Topics
```dtd
INSERT INTO mycourse_topics (course_id, topic_name) VALUES 
(1, 'Datatypes'),
(1, 'Arrays'),
(1, 'Object Oriented Programming'),
(1, 'Multithreading'),
(1, 'Collections');
```