package Comparators;

import DTOs.Singer;
import Enumerators.SortType;
import java.util.Comparator;

public class ComparatorSingerRate implements Comparator<Singer>{

    private SortType sortType;

    public ComparatorSingerRate(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare( Singer s1, Singer s2)
    {
        int direction = (int) sortType.getValue();
        return (int) (direction * (s1.getRate() - s2.getRate()));
    }

}


