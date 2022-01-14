package com.projprogiii.lib.objects;

import com.projprogiii.lib.utils.CommonUtil;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class Email implements Serializable, Comparable<Email>{

    private final int mailId;
    private final String sender;
    private final List<String> receivers;
    private final String subject;
    private final String text;
    private boolean isToRead;
    private Date date;

    public Email(String sender, List<String> receivers,
                 String subject, String text){
        this.mailId = this.hashCode();
        this.sender = sender;
        this.subject = subject;
        this.text = text;
        this.receivers = new ArrayList<>(receivers);
        this.date = new Date();
        this.isToRead = false;
    }
    public Email(String sender, List<String> receivers, String subject,
                 String text, Date date) {
        this(sender, receivers, subject, text);
        this.date = date;
        this.isToRead = false;
    }
    public Email(String sender, List<String> receivers, String subject,
                 String text, Date date, boolean isToRead) {
        this(sender, receivers, subject, text, date);
        this.isToRead = isToRead;
    }
    public static Email generateEmptyEmail(){
        return new Email("", List.of(""), "",
                "", null);
    }

    public int getMailId() {
        return mailId;
    }

    public String getSender() {
        return sender;
    }
    public List<String> getReceivers() {
        return receivers;
    }
    public String getSubject() {
        return subject;
    }
    public String getText() {
        return text;
    }

    public Date getDate() { return date; }
    public void setToRead(boolean b){
        this.isToRead = b;
    }
    public boolean isToRead() {
        return isToRead;
    }

    public static boolean isEmpty(Email email){
        return email.equals(generateEmptyEmail());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return getMailId() == email.getMailId() &&
                Objects.equals(getSubject(), email.getSubject()) &&
                Objects.equals(getText(), email.getText());
    }
    @Override
    public int hashCode() {
        return Objects.hash(sender, receivers, subject, text);
    }

    @Override
    public String toString() {
        return String.join(" - ", List.of(this.sender, this.subject));
    }
    public String dateToString(){
        if (this.date == null) return "";
        return CommonUtil.formatDate(this.date);
    }

    @Override
    public int compareTo(Email email) {
        return email.getDate().compareTo(this.date);
    }
}

