package com.devsuperior.dslist.config.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionHandlerDTO implements Serializable {

    @Serial private static final long serialVersionUID = -9166141977378458752L;

    @JsonProperty("mensagem")
    private String message;

    @JsonProperty("httpStatusCode")
    private String statusCode;

    @JsonIgnoreProperties
    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant timestamp;

    @JsonProperty("dataHora")
    public String getTimestamp() {
        this.timestamp = (Objects.isNull(this.timestamp)) ? Instant.now() : this.timestamp;
        return this.timestamp.toString();
    }

    public String getStatusCode() {
        this.httpStatus = (Objects.isNull(this.httpStatus)) ? HttpStatus.INTERNAL_SERVER_ERROR : this.httpStatus;
        return String.format("%s - %s", this.httpStatus.value(), this.httpStatus.getReasonPhrase());
    }
}
