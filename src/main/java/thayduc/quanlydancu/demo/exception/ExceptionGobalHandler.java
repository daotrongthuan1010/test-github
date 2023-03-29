package thayduc.quanlydancu.demo.exception;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@RestControllerAdvice
public class ExceptionGobalHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";
//    @ExceptionHandler(NotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public  ExceptionResponse NotFound(NotFoundException notFoundException, HttpRequest request){
//        return new ExceptionResponse(HttpStatus.NOT_FOUND, notFoundException.getMessage());
//
//    }
//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public  ExceptionResponse Exception(Exception exception, HttpRequest request){
//        return new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
//
//    }
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null)
            throw e;
        ModelAndView mav = new ModelAndView();
        mav.addObject("path", req.getRequestURL());
        mav.addObject("message", e.getMessage());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}




