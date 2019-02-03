package com.smstofindphone.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmsData implements Serializable {

  /**
   * The body of the message.
   */
  private String body;

  /**
   * DATE : The date the message was received.
   */
  private Date receivedDate;

  /**
   * DATE_SENT : The date the message was sent.
   */
  private Date sentDate;

  /**
   * ERROR_CODE : Error code associated with sending or receiving this message
   */
  private String errorCode;

  /**
   * LOCKED : Is the message locked?
   */
  private boolean locked;

  /**
   * Has the message been read?
   */
  private boolean read;

  /**
   * Has the message been seen by the user? The "seen" flag determines whether we need to show a
   * notification.
   */
  private boolean seen;

  /**
   * The subject of the message, if present.
   */
  private String subject;
}
