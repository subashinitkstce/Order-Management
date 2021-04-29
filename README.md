## Order Management

This repo contains both front-end(**frontend**) and backend(**assessment**) project.

## Steps to run the Application
### Backend setup
  1. clone/download the repo to local system.
  2. Open Eclipse or IntelliJ IDE and open the project with 'Existing maven Projects' option
  3. Right click and 'Run As' 'Spring Boot App'
  4. Hit the url "http://localhost:8080/api/orders" to check the app is running.

### Frontend setup
  1. Clone the project into local
  2. Open Visual studio code and open the folder 'frontend'
  3. Install all the npm packages. Go into the project folder and type the following command to install all npm packages

      ```bash
      npm install
      ```

  4. In order to run the application Type the following command

      ```bash
      npm start
      ```

  5. The Application Runs on **localhost:3000**
 ## Test the application
  1. Click 'Choose file' and select the csv file which contains orders.
  2. Click 'Upload' to upload the selected file.
  3. Once the file uploaded succesfully, 'File uploaded Succesfully' message gets displayed and the orders table get loaded with the order records.
  4. Can upload another CSV file to migrate more order records.
