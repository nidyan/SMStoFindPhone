package com.smstofindphone.model;

import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SMSData1 {


  /**
   * sender from which the message is sent
   */
  private String senderNumber;

  /**
   * message Content
   */
  private String messageBody;

  /**
   * date and time on which the message is sent
   */
  private Date messageDate;
}
