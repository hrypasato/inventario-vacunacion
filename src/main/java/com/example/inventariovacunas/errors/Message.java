package com.example.inventariovacunas.errors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Message {
    public static String formatMessage(BindingResult result){
        StringBuilder sb = new StringBuilder();
        sb.append(result.getErrorCount()).append(" error(s): ");
        for (ObjectError error : result.getAllErrors()) {
          sb.append("[").append(error).append("] ");
        }
        return sb.toString();
    }
}

