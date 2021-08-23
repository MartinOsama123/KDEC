package com.genesiscreations.kdec.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data

public class NotificationInfo {

    String title;
    String body;
    @DateTimeFormat(fallbackPatterns = "dd/MM/yyyy")
    private Date birthDay;
}
