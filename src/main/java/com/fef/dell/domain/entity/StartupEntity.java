package com.fef.dell.domain.entity;

import com.fef.dell.domain.enums.Events;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Getter()
@Setter()
public class StartupEntity implements Comparable<StartupEntity>{
    private static int counter = 0;
    int id;

    String name;
    String slogan;
    String foundationYear;
    int points;
    ArrayList<Events> events;

    public void resetEvents(){events.clear();}
    public void addEvent(Events event){events.add(event);}
    public void removeEvent(Events event){events.remove(event);}

    public StartupEntity(String name, String slogan, String foundationYear){
        id = counter++;
        this.name = name;
        this.slogan = slogan;
        this.foundationYear = foundationYear;
        points = 70;
        this.events = new ArrayList<>();
    }

    public void applyEvents(){
        for(Events event : events){
            points += event.getPoints();
        }
    }

    public int compareTo(StartupEntity other) {
        return Integer.compare(this.points, other.points);
    }

    public void addPoints(int points){
        this.points += points;
    }
}
