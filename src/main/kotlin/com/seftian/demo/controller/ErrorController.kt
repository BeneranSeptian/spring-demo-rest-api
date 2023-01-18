package com.seftian.demo.controller

import com.seftian.demo.model.product.WebResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constaintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
                code=400,
                status= "BAD REQUEST",
                data = constaintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [Exception::class])
    fun handleException(ex: Exception, request: WebRequest): WebResponse<String> {
        return WebResponse(
                code = 500,
                status = "error beneran",
                data = ex.message!!
        )
    }
}