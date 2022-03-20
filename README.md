# Notification-Microservice

## Introduction 
Notification Microservice: The Notification microservice is used to send emails to users 

## Service Provider used
Mailtrap


### Endpoints
Send mail Endpoints : http://localhost:9093/api/v1/notification/mail     [Post]

## payload:
Request Payload : 
{

    "subject" : "Subject of the mail" //Required

    "recipient" : "recipient name" //Required

    "body" : "Body of the message" // Required

    "sender" : "sender name" // Required

    "attachment" : "attachments" 
    
    "cc" : "cc"
    
    "bcc" : "bcc" ;

}
