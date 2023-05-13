package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super();
        this.calendar=new ArrayList<>();
        inboxCapacity=Integer.MAX_VALUE;
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        calendar.sort(Comparator.comparing(Meeting::getEndTime));
        int count=0;
        LocalTime lastEndTime=calendar.get(0).getEndTime();
        for(int i=1;i<calendar.size();i++){
            Meeting currMeeting=calendar.get(i);
            if(currMeeting.getStartTime.isAfter(lastEndTime)){
                count++;
                lastEndTime=currMeeting.getEndTime;
            }
        }
        return count;
    }
}
