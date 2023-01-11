# recordServer

Hello here is first aproach of coding Task (v4)

As you can see in comments if uploade file contains more than one record with that same primary_key, the last one will be stored. Also if record with specified 
primary key already exist in data storage it will be updated with new data. But this is to discusse.

If record is invalis and can't be stored it is not stored (REQ-04), hoever there is no return information to client (this record is logged, so Servce owner can check it).
Client also has no information if records in file are duplicated. CLient only gets info about records to be stored. So he/she can compare that number with number of 
records in uploaded file. But this is basic solution. I can propose something more advanced if there is requiment.

I decided to use H2 base to storage data. If you have other ideas I'm open to listen them.

In REQ-02 there is returned object of entity class (containing JPA dependences).  If there is requirment i can add record class to be returned 
(witout any JPA/Hibernate dependencies) 

As you can see I impelented REST controller with 3 methods (REQ-01,REQ-02,REQ-04), but should I split into 2 controllers (For client and serwice owner)? 


Regards
