# Backend 14.1 - Create a custom exception - MemberNotFoundException

This lesson plan template is aimed at preparing instructors to deliver a high quality live session to learners who have already prepared to work on the project by setting up any prerequisites before coming to class.

## Prep List

- [ ] Watch the lesson plan overview video. ***Link to be included once lesson plan is complete***
- [ ] After watching the lesson overview, internalize lesson plan in full
- [ ] Set up your local machine to be prepared to teach the live session. You should also go through the Guided Practice videos for the first two modules of Sprint 13. That will help you significantly with this code along. Also, make sure you have AWS access.
- [ ] If you are running code in your live session, make sure it works and the solution code is accurate
- [ ] If your lesson plan includes technical steps, be sure to have walked through them first before teaching

## Lesson Plan

### 1. Engage Classroom (1-2 min.)

- ***Remind learners of classroom expectations during live Code-Along instruction:***
  - Web cameras need to be on to have the best learning experience possible
  - Talk about how we are going to be using specific communication tools like Slido, Zoom, etc. during the live session
  - If you need additional technical support that goes beyond this Code-Along, please use the Hub's knowledge base to start or schedule 1:1 support

- ***Pick one activity*** to help build an online community here at BloomTech and get learners excited for live instruction:
  - ***Icebreaker:*** Fun activity that helps learners get to know one another
  - ***Pulse Check:*** Pose a question for learners to gauge how they are feeling/get a pulse on how where they are at this point in the course
  - ***Do Now:*** Pose a question that involves no guidance from you. Used to activate students’ learning for the lesson, surface prior knowledge from pre-work, and familiarize students with today’s content
  - ***Celebrations:*** Share a learner celebration, job offer, remind students why they are putting in all this hard work


### 2. Getting Started (3-5 min.)

- State that the learners should have already setup their Discussion CLI starter repository. This is the same repository as the one used in Sprint 13. So, if learners have implemented along with prior code-alongs, then they can just use the same repository.
- Remind learners what they’ve worked on up until now to get to this point within the sprint
  - Exceptions
  - Custom exceptions
- Utilize a metaphor or an analogy to get learners excited to learn the key concepts
  - Everyday life is full of situations that we don't anticipate. For example, you get up for work in the morning and look for your phone charger, but you can't find it anywhere. You get in your car, but it won't start. A human is able to cope with such unforeseen circumstances quite easily. Unlike humans in the real world, a program must know exactly how to handle these situations.
- Highlight the key concepts that will be covered during the live session
  - Creating a new custom exception.
  - Handling of this new exception
- Explain how these concepts are designed with the sprint challenge in mind and how it’s critical to the job
  - Creating and reusing existing custom exception is an integral part of backend developer's role. In our sprint challenge also, we will create custom exceptions to handle specific error situations.

### 3. What Will We Be Building In This Code-Along? (2-3 min.)

We will -
  - creating a new exception - MemberNotFoundException.
  - throw this exception when the username provided at the runtime doesn't exist in the database.
  - catch and handle this exception by displaying a special message before creating a new user/member.

***Preview End Result:*** 
```
Image here
```

### 4. Let’s Build (30 min.)


- **Problem:** 
  - In the Discussion CLI, we don't know if the user already existed or it is a new user. Hence, we want to update the system to take inform us of the new user before creating it.

- **Solution:** 
  - Add a new package "exceptions".
  - Add a new exceptions "MemberNotFoundException".
  - Throw this exception from getMember() in MemberDao.
  - Handle it in LoginHandler.

- **Build It:** 
  
   - **Step 1:**  Add a new package "exceptions" under "com.amazon.ata.dynamodbannotationsloadsave" folder. 
   
   - **Step 2:**  Add a new Java class "MemberNotFoundException" in the "exceptions" package.
   
   - **Step 3:**  Write the code for this new Exception class as below. ***Make sure to generate the new serialVersionUID***
      ```
      public class MemberNotFoundException extends Exception{
    
        private static final long serialVersionUID = -1378588467028195764L;

        public MemberNotFoundException(){
          super();
        }

        public MemberNotFoundException(String message){
          super(message);
        }

        public MemberNotFoundException(Throwable cause){
          super(cause);
        }

        public MemberNotFoundException(String message, Throwable cause){
          super(message, cause);
        }
      }
      ```

   - **Step 4:**  Change the getMember() method in com.amazon.ata.dynamodbannotationsloadsave.dynamodb/MemberDao.java file.
     - Current code:
       ```
       public Member getMember(String username){
         if (username.equals("")) {
           throw new IllegalArgumentException("username cannot be empty");
         }
         return mapper.load(Member.class, username);
       }
       ```
       
      - New code: Throw the new exception.
       ```
       public Member getMember(String username) throws MemberNotFoundException {
         if (username.equals("")) {
           throw new IllegalArgumentException("username cannot be empty");
         }
         Member member = mapper.load(Member.class, username);
         if (member == null){
           throw new MemberNotFoundException(String.format("%s member doesn't exist", username));
         }
         return member;
       }
       ```
       
   - **Step 5:**  Change the findOrCreateMember() method in com.amazon.ata.dynamodbannotationsloadsave.handler/LoginHandler.java file. In the handling here we are just printing out a message before creating the new user. You can do more processing as needed for your project.
     - Current code:
       ```
       private Member findOrCreateMember(String username) {
         Member member = memberDao.getMember(username);

         if (null == member) {
           member = new Member(username);
           memberDao.createMember(member);
         }
         return member;
       }
       ```
       
      - New code: Catch and handle this new exception.
       ```
       private Member findOrCreateMember(String username) {
         Member member;
         try{
           member = memberDao.getMember(username);
         } catch(MemberNotFoundException ex){
           System.out.println("Member doesn't exist. Creating a new user...");
           member = new Member(username);
           memberDao.createMember(member);
         }
         return member;
       }
       ```
   - **Step 6:**  Test.
   

### 5. Wrap Up (7-10 min.)

- ***Answer questions*** in a question and answer format, time permitting
- ***Restate*** the key concepts and why the project was important to the job
Q&A for the whole project time permitting
- ***Review*** next steps in the learner journey as it relates to the sprint of learning.  Example:  If you are in Code-Along 1, you would encourage the learner to spend time with the next two learning modules before attending Code-Along 2 for this sprint
- ***Encourage*** learners to sign up for another live Code-Along or repeat this one if they want to review it again
