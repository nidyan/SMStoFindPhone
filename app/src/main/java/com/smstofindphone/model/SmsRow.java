package com.smstofindphone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class SmsRow implements Serializable {

  /**
   * Check whether message is read or unread
   */
  private boolean unreadMessage;

  /**
   * number of unread message;
   */
  @Default
  private int unreadMessageCount = 0;
  /**
   * icon of the sender
   */
  private String icon;

  /**
   * name of the sender ,creator :  The identity of the sender of a sent message.
   */
  private String senderName;

  /**
   * Number from which the message is sent. The address of the other party.
   */
  private String senderNumber;

  /**
   * last updated message from the sender
   */
  private Date lastUpdatedTime;

  /**
   * list of messages from the sender.
   */
  @Default
  private List<SmsData> messageList = new ArrayList<>();

  public void increaseUnreadMessageCount() {
    unreadMessageCount += 1;
  }

  public void decreaseUnreadMessageCount() {

    if (unreadMessageCount == 0) {
      return;
    }
    unreadMessageCount -= 1;
  }
}
