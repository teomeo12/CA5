package BusinessObjects;

import java.util.Comparator;

public class ComparatorNameAge implements Comparator<Singer> {
    private SortType sortType;
//    public ComparatorNameAge(SortType sortType){
//        this.sortType = sortType;
//    }
//
//    public void ComparatorSingerName(SortType sortType){
//        this.sortType = sortType;
//    }


    @Override
    public int compare(Singer s1, Singer s2)
    {
        boolean nameSame =
                s1.getName().equalsIgnoreCase(s2.getName());


        if(nameSame)
        {
            //so, compare based on name
            return  (s1.getDob().compareTo(s2.getDob()));
        }
        else
        {
            return s1.getName().compareToIgnoreCase(s2.getName());
        }
    }


}
