package BusinessObjects;

import java.util.Comparator;

public class ComparatorDateOfBirth  implements Comparator<Singer> {

    @Override
    public int compare(Singer s1, Singer s2)
    {

        return  (s1.getDob().compareTo(s2.getDob()));

    }
}
