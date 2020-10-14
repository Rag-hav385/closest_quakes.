import java.util.*;

/**
 * Write a description of LargestQuakes here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LargestQuakes {
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size());
        
        //int maxindex = indexOfLargest(list);
        //System.out.println(list.get(maxindex));
        
        ArrayList<QuakeEntry> maxQuakes = getLargest(list , 50);
        for(QuakeEntry qe : maxQuakes){
            System.out.println(maxQuakes.indexOf(qe) + "\t" +  qe);
        }
        
        
    }
    
    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
        int maxindex = 0;
        double maxmag = 0;
        for(QuakeEntry qe : quakeData){
            if(qe.getMagnitude() > maxmag){
                maxmag = qe.getMagnitude();
                maxindex = quakeData.indexOf(qe);
            }
        }
        return maxindex;
    }
    
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData , int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        if(quakeData.size() > howMany){
            for(int i =  0 ; i < howMany ; i++){
                ret.add(copy.get(indexOfLargest(copy)));
                copy.remove(copy.get(indexOfLargest(copy)));
                
            }
        }
        else{
            for(int i =  0 ; i < quakeData.size() ; i++){
                ret.add(copy.get(indexOfLargest(copy)));
                copy.remove(copy.get(indexOfLargest(copy)));
        
        }
       }
        
        return ret;
    }
}
