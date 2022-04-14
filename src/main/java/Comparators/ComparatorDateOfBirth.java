package Comparators;

import DTOs.Singer;
import Enumerators.SortType;
import java.util.Comparator;

public class ComparatorDateOfBirth  implements Comparator<Singer> {

    private SortType sortType;

    public ComparatorDateOfBirth(SortType sortType)
    {
        this.sortType = sortType;
    }


    @Override
    public int compare(Singer s1, Singer s2)
    {

        return  (s1.getDob().compareTo(s2.getDob()));

    }
}
