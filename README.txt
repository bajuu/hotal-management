                                Technical details

1. Architecture used (https://medium.com/idealo-tech-blog/hexagonal-ports-adapters-architecture-e3617bcf00a0)

2. Java version USED : 11

3. Database USED : H2 (in memory database) check HELP.md for how to connect h2 console

4. Swagger url : http://localhost:8080/swagger-ui/index.html#/

                                Assumption made by me

1. Only admin can create the account for newly joined employee/staff. And she/he will receive her/his
   login credentials via sms i.e (EmployeeId and Password)

                Things not implemented due to lack of time. (office work/ home work / projects)

1. If patient admitted multiple time I am not maintaining history. Can be done via token. Will create a patient_history table where
   log patient details, date and time of admit/discharge, staff person name who admitted/discharged

2. Notification system is just a dummy use case where I am logging message. We can build real one.

3. I can build payment tracking system for patient. Need to implement receive payment api, due payment api.

                                            Design doc
Please refer design doc directory in project.
