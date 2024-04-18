**Big Event Project Documentation**

**Development Tools and Technologies**
  Development Language: Java  
  Framework: SpringBoot3 + Vue3  
  Presentation Layer:  Html + JavaScript + CSS + Vue  
  Business Logic Layer:  Java + Spring + Spring MVC  
  Data Access Layer:  MySQL + MyBatis  
  Development Tools:  IntelliJ IDEA  
  Runtime Environment:  IntelliJ IDEA + JDK 17 + Maven 3

 **Specific Technologies Used** 
 **Backend (SpringBoot):**
- Database Operations: Employed MyBatis for efficient database operations.
- Caching: Implemented Redis for caching purposes.
- Unit Testing: Utilized Junit for comprehensive unit testing.
- Deployment: Deployed the SpringBoot project for optimal performance.

**Frontend (Vue):**
- Vue Version: Vue3.
- Project Scaffold: Builde the project using Vite, a Vue project scaffold.
- Routing/Navigation: Implemente Vue Router for efficient routing and navigation.
- State Management: Use Pinia for effective state management.
- UI Components: Use Element-Plus for a rich set of UI components.

**Overview of Functionality/Modules**
1. **Login Homepage:**
   - Login and registration functionalities.
2. **User Profile:**
   - Allows users to modify basic profile information, change avatars, and update passwords.
3. **Article Categories:**
   - Displays all article categories with corresponding list query functionality.
   - Supports adding, deleting, and modifying categories.
4. **Article Management:**
   - Displays a list of articles with search and pagination functionality.
   - Allows publishing, deleting, and modifying articles.
5. **File Upload to Cloud Platform:**
   - Enables users to upload files to a cloud platform.

**Technical Challenges**
1. The Login-module utilized JWT authentication and implemented Redis Token Revocation Mechanism.
2. Interceptor:  Registered an interceptor for the program, ensuring that all requests pass through it, which allows for unified validation within the interceptor.
3. ThreadLocal:Reduced parameter passing and facilitated data sharing among code executed within the same thread.


**Function display/operation screenshots**
![big-event login](https://github.com/purpleziyi/BigEvent/assets/161695864/6c28934e-4faf-472f-8039-758596037605)
![big-event register](https://github.com/purpleziyi/BigEvent/assets/161695864/366ad3f4-a9d0-4b71-9773-e3f39768d807)
![big-event article_category](https://github.com/purpleziyi/BigEvent/assets/161695864/557a9fe3-2454-4042-ade9-7a48653d3034)
![big-event add_categoty](https://github.com/purpleziyi/BigEvent/assets/161695864/1b9801ed-2410-437e-8a0b-4b97b820c144)
![big-event article_management](https://github.com/purpleziyi/BigEvent/assets/161695864/ba3f50e0-bdce-4f3d-b903-d326c7007b92)
![big-event add article](https://github.com/purpleziyi/BigEvent/assets/161695864/96b2e6ba-635c-48cd-acf6-a3a13c93cc24)
![big-event userInfo](https://github.com/purpleziyi/BigEvent/assets/161695864/c66e8ef8-f937-4f1a-9054-bddaa46032ee)
![big-event modify_info](https://github.com/purpleziyi/BigEvent/assets/161695864/43569c85-242f-4b4d-a938-1edd46d2faad)
![big-event change_avatar](https://github.com/purpleziyi/BigEvent/assets/161695864/679c67d3-8ea0-4ef6-ab43-295bccb1e555)
![big-event logout](https://github.com/purpleziyi/BigEvent/assets/161695864/22f3a938-dff5-4bde-9dbd-e27a44cbe3d1)





