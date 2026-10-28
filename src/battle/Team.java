package battle;

import models.Droid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    String name;
    ArrayList<Droid> droids;
    Random random=new Random();

    public Team(String n){
        this.name=n;
        this.droids=new ArrayList<>();
    }
    public void addDroid(Droid droid){
    this.droids.add(droid);
    }
    public boolean isAlive(){
        for(int i=0;i< droids.size();i++){
            Droid d=this.droids.get(i);
        if (d.isAlive()){
            return true;
        }
    }
        return false;
}
public Droid getRandomAliveDroid(){
    List<Droid> aliveList=new ArrayList<>();
    for(int i=0;i<this.droids.size();i++){
        Droid d=this.droids.get(i);
    if(d.isAlive()){
        aliveList.add(d);
    }
    }
    if (aliveList.isEmpty()) {
        return null;
    }
    int randomIndex = random.nextInt(aliveList.size());
    return aliveList.get(randomIndex);
}
    public String getName() {
        return this.name;
    }

    public List<Droid> getDroids() {
        return this.droids;
    }
}
