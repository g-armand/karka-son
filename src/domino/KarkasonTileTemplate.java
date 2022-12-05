package domino;

import java.util.ArrayList;
import java.util.List;

public interface KarkasonTileTemplate {



    /*
    f = field
    t = track
    c = city
    a = abbey

    S = SPECIAL modifier (shield on city)
     */

    static  char[][] CROSSING_TRACK =
            {{'.', 'f', 't', 'f', '.'},
            {'f', 'f', 't', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};

    static char[][] STRAIGHT_TRACK =
            {{'.', 'f', 'f', 'f', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] ANGLE_TRACK =
            {{'.', 'f', 't', 'f', '.'},
            {'f', 'f', 't', 'f', 'f'},
            {'f', 'f', 't', 't', 't'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] DOUBLE_ANGLE_TRACK =
            {{'.', 'f', 't', 'f', '.'},
            {'f', 'f', 't', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};


    //city tiles
    static char[][] WHOLE_CITY =
            {{'S', 'c', 'c', 'c', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'.', 'c', 'c', 'c', '.'}};

    static char[][] TRACK_CITY_SMALL =
            {{'.', 'c', 'c', 'c', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] RIGHT_ANGLE_TRACK_CITY_SMALL =
            {{'.', 'c', 'c', 'c', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'f', 'f', 't', 't', 't'},
            {'f', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};


    static char[][] LEFT_ANGLE_TRACK_CITY_SMALL =
            {{'.', 'c', 'c', 'c', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'t', 't', 't', 'f', 'f'},
            {'f', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};

    static char[][] ANGLE_TRACK_CITY_BIG =
        {{'.', 'c', 'c', 'c', '.'},
        {'c', 'c', 'f', 'f', 'f'},
        {'c', 'f', 't', 't', 't'},
        {'c', 'f', 't', 'f', 'f'},
        {'.', 'f', 't', 'f', '.'}};

    static char[][] ANGLE_TRACK_CITY_BIG_SPECIAL =
            {{'S', 'c', 'c', 'c', '.'},
            {'c', 'c', 'f', 'f', 'f'},
            {'c', 'f', 't', 't', 't'},
            {'c', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};


    static char [][] ALMOST_WHOLE_CITY_TRACK =
            {{'.', 'c', 'c', 'c', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'f', 't', 'f', 'c'},
            {'.', 'f', 't', 'f', '.'}};

    static char [][] ALMOST_WHOLE_CITY_TRACK_SPECIAL =
            {{'S', 'c', 'c', 'c', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'f', 't', 'f', 'c'},
            {'.', 'f', 't', 'f', '.'}};

    static char[][] ALMOST_WHOLE_CITY =
            {{'.', 'c', 'c', 'c', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'f', 'f', 'f', 'c'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] ALMOST_WHOLE_CITY_SPECIAL =
            {{'S', 'c', 'c', 'c', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'f', 'f', 'f', 'c'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] DOUBLE_ANGLE_TRACK_CITY =
            {{'.', 'c', 'c', 'c', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};

    static char[][] CITY_SMALL =
            {{'.', 'c', 'c', 'c', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'f', 'f', 'f', 'f', 'f'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] CITY_SMALL_DOUBLE =
            {{'.', 'c', 'c', 'c', '.'},
            {'c', 'f', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};


    static char[][] CITY_BIG =
            {{'.', 'c', 'c', 'c', '.'},
            {'c', 'c', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] CITY_BIG_SPECIAL =
            {{'S', 'c', 'c', 'c', '.'},
            {'c', 'c', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'c', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] CITY_CORRIDOR =
            {{'.', 'f', 'f', 'f', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] CITY_CORRIDOR_SPECIAL =
            {{'S', 'f', 'f', 'f', '.'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'c', 'c', 'c', 'c', 'c'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] FIELD_CORRIDOR =
            {{'.', 'f', 'f', 'f', '.'},
            {'c', 'f', 'f', 'f', 'c'},
            {'c', 'f', 'f', 'f', 'c'},
            {'c', 'f', 'f', 'f', 'c'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] ABBEY =
            {{'.', 'f', 'f', 'f', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'f', 'f', 'a', 'f', 'f'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};

    static char[][] ABBEY_TRACK =
            {{'.', 'f', 'f', 'f', '.'},
            {'f', 'f', 'f', 'f', 'f'},
            {'f', 'f', 'a', 'f', 'f'},
            {'f', 'f', 'f', 'f', 'f'},
            {'.', 'f', 'f', 'f', '.'}};


}
