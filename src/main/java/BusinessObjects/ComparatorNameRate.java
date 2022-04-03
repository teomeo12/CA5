package BusinessObjects;

import java.util.Comparator;

public class ComparatorNameRate implements Comparator<Singer> {
    private SortType sortType;

    public ComparatorNameRate(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Singer s1, Singer s2)
    {
//        int comparison=0;
//        comparison = s1.getName().compareTo(s2.getName());
//
//        if(comparison ==0 ){
//            comparison = s1.getName().compareTo(s2.getName());
//        }

        String name1 =s1.getName();
        String name2 = s2.getName();
        double rate1 =s1.getRate();
        double rate2 = s2.getRate();

        if(name1 == null){
            return (int) (-1 * sortType.getValue());
        }else if(name2 == null){
            return (int) sortType.getValue();
        }else if (name1.equalsIgnoreCase(name2)){
            if(rate1 > rate2){
                return (int) sortType.getValue();
            }
            if(rate1 < rate2){
                return (int) (-1 * sortType.getValue());
            }
            return 0;
        }
        return (int) (sortType.getValue() * name1.compareToIgnoreCase(name2));


//        boolean nameCompare =
//                s1.getName().equalsIgnoreCase(s2.getName());
//
//        if(nameCompare)
//        {
//            //so, compare based on name
//           // return  (s1.getRate() == (s2.getRate());
//            return (int)  (s1.getRate() - s2.getRate());
//        }
//        else
//        {
//            return s1.getName().compareToIgnoreCase(s2.getName());
//        }
    }

}
