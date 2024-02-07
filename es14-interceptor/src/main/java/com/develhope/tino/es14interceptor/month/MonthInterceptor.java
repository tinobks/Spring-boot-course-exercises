package com.develhope.tino.es14interceptor.month;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    private List<MonthEntity> months;

    public MonthInterceptor() {
        this.months = new ArrayList<>();
        months.add(new MonthEntity(1, "January", "Gennaio", "Januar"));
        months.add(new MonthEntity(2, "February", "Febbraio", "Februar"));
        months.add(new MonthEntity(3, "March", "Marzo", "März"));
        months.add(new MonthEntity(4, "April", "Aprile", "April"));
        months.add(new MonthEntity(5, "May", "Maggio", "Mai"));
        months.add(new MonthEntity(6, "June", "Giugno", "Juni"));
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberHeader = request.getHeader("monthNumber");

        if (monthNumberHeader == null || monthNumberHeader.isEmpty()) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }

        MonthEntity month = months.stream()
                .filter(monthEntity -> monthEntity.getMonthNumber() == Integer.parseInt(monthNumberHeader))
                .findFirst()
                .orElse(new MonthEntity(Integer.parseInt(monthNumberHeader), "nope", "nope", "nope"));

        request.setAttribute("month", month);
        response.setStatus(HttpStatus.OK.value());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
