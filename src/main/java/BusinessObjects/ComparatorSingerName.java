package BusinessObjects;

import java.util.Comparator;

public class ComparatorSingerName implements Comparator<Singer>{
    @Override
    public int compare( Singer s1, Singer s2)
    {
        return s1.getName().compareTo(s2.getName());
    }
}


