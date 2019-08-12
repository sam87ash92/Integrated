# Please detail the design including the main classes that you would write to provide such a component. 
1. Once application is up and running, main class should be calling a controller to initiate this process and provide poll timers
2. Controller would call POST and PULL service methods in a scheduler (which is timed based on poller frequency)
3. Create CRUD respositoring to persist data coming from/to database
4. Make the application fail-safe so that process should not terminate untill force restart.
5. Once database records are read and POST successfully, we should set a flag in DB table saying POST OK (so that these records are not read again) - this feature is not added in my sample project
6. Add monitoring feature by saving all operation traces in database

# Please state what you would expect in terms of good practise for such a component.
1. Proper naming conventions for objects, variables and methods
2. Allow re-usability of business logic
3. Separate design structure into proper modules
4. Double check usage of memory by not allowing more global variables
5. This component should be more like a gateway rather than actual microservice making the executions.

# How would you make this component scalable so that it could to push 1000’s (or 100k‘s) of data objects into the third party api in a very short time?
For huge datasets, we should avoid using cache and object creation. We can download database records in .csv file and FTP/SFTP to 3rd party location. if possible we can upload them in secured cloud location and 3rd party can pick it up.
Based on performance we can create pagination of our data, and send data in batches.

# If also asked to add a monitoring feature to this so that some monitoring tool can gather stats on this component, what would you suggest?
All operations, their status and related information should be either put into log files or saved in database. A separate monitoring application should be able to read this information and make necessary decisions.

1. In order to save in database, our API can make a JMS call and send message to message broker. These message should be pulled and saved into DB by another application.
2. If we log tracking information in log files, then log files should be written in patterns i.e. creating 5/10 logs on roll-over each with sufficient maxSize. Another file listener should be reading this log file for specific regex pattern, and provide monitoring feature