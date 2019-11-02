package com.example.crm.domain.dto.out;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ExceptionResponse
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
  private LocalDateTime timestamp;
  private String message;
  private int error;
  private String httpCodeMessage;
  private String exceptionMessage;
}