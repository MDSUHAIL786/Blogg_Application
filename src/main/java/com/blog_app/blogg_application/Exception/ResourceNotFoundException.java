package com.blog_app.blogg_application.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldvalue;
    public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
        super(resourceName+" Not found with "+fieldName+" : "+fieldvalue);
        this.resourceName=resourceName;
        this.fieldvalue=fieldvalue;
        this.fieldName=fieldName;

    }
}
