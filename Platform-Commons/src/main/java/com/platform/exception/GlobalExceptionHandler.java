package com.platform.exception;

import java.time.LocalDateTime;

import javax.security.auth.login.LoginException;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {


    /* --------------------------------------   Login Exception    ----------------------------------------------*/
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ErrorDetails> loginException(LoginException loginException,WebRequest request){

        ErrorDetails err=new ErrorDetails(LocalDateTime.now(), loginException.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }


    /*--------------------------------------------  Student Exception  --------------------------------------------------*/
    @ExceptionHandler(StudentException.class)
    public ResponseEntity<ErrorDetails> StudentNotFoundException(StudentException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Course Exception  --------------------------------------------------*/
    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ErrorDetails> CourseNotFoundException(CourseException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Exception  --------------------------------------------------*/
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> Exception(StudentException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Illegal Argument Exception  --------------------------------------------------*/
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDetails> IllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Null Pointer Exception  --------------------------------------------------*/
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDetails> NullPointerException(NullPointerException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Json Parse Exception  --------------------------------------------------*/
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorDetails> BeanCreationException(JsonParseException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }


    /*--------------------------------------------  Validation Exception  --------------------------------------------------*/
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDetails> BeanCreationException(ValidationException exception, WebRequest webRequest){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}
