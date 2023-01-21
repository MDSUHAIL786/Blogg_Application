package com.blog_app.blogg_application.Exception;

import com.blog_app.blogg_application.DTO_dataTransfer.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException excptn){
        String msg=excptn.getMessage();
        ApiResponse m=new ApiResponse(msg,true);
        return new ResponseEntity<>(m, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> resourceNotFoundException(MethodArgumentNotValidException excptn){
        Map<String,String> m=new HashMap<>();
        excptn.getBindingResult().getAllErrors().forEach(error ->{
            String fieldName=((FieldError)error).getField();
            String msg=error.getDefaultMessage();
            m.put(fieldName,msg);

        } );
        return new ResponseEntity<>(m, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String,String>> resourceNotFoundException(HttpRequestMethodNotSupportedException excptn){
        Map<String,String> m=new HashMap<>();
//        String msg=excptn.getMessage();
        m.put("Error", String.format("Requested method %s not allowed here.",excptn.getMessage()));

        return new ResponseEntity<>(m,HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String,String>> notReadableException(HttpMessageNotReadableException excptn){
        Map<String,String> m=new HashMap<>();
//        String msg=excptn.getMessage();
        m.put("Error","something went wrong like JSON parse error:Feature 'ALLOW_COMMENTS' not enabled for parser");

        return new ResponseEntity<>(m,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(JpaSystemException.class)
    public ResponseEntity<Map<String,String>> JpaSystemExcp(JpaSystemException excptn){
        Map<String,String> m=new HashMap<>();
//        String msg=excptn.getMessage();
        m.put("Error","you already commented to this post!!");

        return new ResponseEntity<>(m,HttpStatus.BAD_REQUEST);
    }


}
