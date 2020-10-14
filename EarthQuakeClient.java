import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData , String where , String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        if(where == "start" || where == "Start"){
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().startsWith(phrase)){
                    answer.add(qe);
                }
            }
        }
        
        if(where == "end" || where == "End"){
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().endsWith(phrase)){
                    answer.add(qe);
                }
            }
        }
        
        if(where == "any" || where == "Any"){
            for(QuakeEntry qe : quakeData){
                if(qe.getInfo().contains(phrase)){ //&& !qe.getInfo().endsWith(phrase) && qe.getInfo().startsWith(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth (ArrayList<QuakeEntry> quakeData , double minDepth , double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for(QuakeEntry qe : quakeData){
            if(qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        // TODO
        int mindist = 0;
        for(QuakeEntry qe : quakeData){
            Location loc = qe.getLocation();
            if(loc.distanceTo(from) < distMax){
                answer.add(qe);
            }
        }

        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> answer = filterByMagnitude(list , 5.0);
        for(QuakeEntry qe : answer){
            System.out.println(qe);
        }

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        // This location is Durham, NC
        Location city = new Location(35.988, -78.907);
        ArrayList<QuakeEntry> DurhamFilterDistList = filterByDistanceFrom(list , 1000 , city);
        for(QuakeEntry qe : DurhamFilterDistList){
            System.out.println(qe.getInfo());
        }
        
        
        // This location is Bridgeport, CA
        Location city2 =  new Location(38.17, -118.82);
        ArrayList<QuakeEntry> BridgeportFilterDistList = filterByDistanceFrom(list , 1000 , city2);
        for(QuakeEntry qe : BridgeportFilterDistList){
            System.out.println(qe.getInfo());
        }

        // TODO
    }
    
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> depthList = filterByDepth(list , -4000.0, -2000.0);
        for(QuakeEntry qe : depthList){
            System.out.println(qe);
        }
        System.out.println(depthList.size());
    }
    
    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        // System.out.println("From Start");
        // ArrayList<QuakeEntry> tittleList1 = filterByPhrase(list , "start" , "Quarry Blast");
        // for(QuakeEntry qe : tittleList1){
            // System.out.println(qe);
            
        // }
        // System.out.println(tittleList1.size());
        // */
        
        // System.out.println("From End");
        // ArrayList<QuakeEntry> tittleList2 = filterByPhrase(list , "end" , "Alaska");
        // for(QuakeEntry qe : tittleList2){
            // System.out.println(qe);
            
        // }
        // System.out.println(tittleList2.size());
        
        System.out.println("From Anywhere");
        ArrayList<QuakeEntry> tittleList3 = filterByPhrase(list , "any" , "Can");
        for(QuakeEntry qe : tittleList3){
            System.out.println(qe);
            
        }
        System.out.println(tittleList3.size());
    }
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
