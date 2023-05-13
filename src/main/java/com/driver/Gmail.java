package com.driver;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import java.util.ArrayList;
import java.util.Date;
public class Gmail extends Email {
    private int inboxCapacity; //maximum number of mails inbox can store
    private ArrayList<Triple<Date,String,String>> Inbox;
    private ArrayList<Triple<Date,String,String>> Trash;
    public Gmail(String emailId, int inboxCapacity) {
        super((emailId));
        this.inboxCapacity=inboxCapacity;
        this.Inbox=new ArrayList<>();
        this.Trash=new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size()==inboxCapacity){
            Trash.add(Inbox.get(0));
            Inbox.remove(0);
        }
//        Pair<String,String> pa=Pair.of()
        Triple<Date,String,String> mail=Triple.of(date,sender,message);
        Inbox.add(mail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<Inbox.size();i++){
            if(message.equals(Inbox.get(i).getRight())){
                Trash.add(Inbox.get(i));
                Inbox.remove(i);
            }
        }
    }
    public String findLatestMessage(){
        if(Inbox.isEmpty()) return null;
        return Inbox.get(Inbox.size()-1).getRight();
    }

    public String findOldestMessage(){
        if(Inbox.isEmpty()) return null;
        return Inbox.get(0).getRight();
    }

    public int findMailsBetweenDates(Date start, Date end){
        int cnt=0;
        for(int i=0;i<Inbox.size();i++){
            if(Inbox.get(i).getLeft().compareTo(start)>=0 && Inbox.get(i).getLeft().compareTo(end)<=0){
                cnt++;
            }
        }
        return cnt;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return  Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
