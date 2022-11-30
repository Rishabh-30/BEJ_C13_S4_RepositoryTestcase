package org.niit.BEJ_C13_S3_PC_Mongo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Customer with this id not found")
public class CustomerNotFoundException extends  Exception{
}
