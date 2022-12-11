package domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KarkasonTile extends Tile{

    KarkasonTileTemplate template = new KarkasonTileTemplate();


    public KarkasonTile(){
        Random picker = new Random();
        int index = picker.nextInt(template.basicTiles.size());
        this.content = Cell.toCellMatrix(template.basicTiles.get(index));
        template.basicTiles.remove(index);
    }

    private class KarkasonTileTemplate{
        ArrayList<char[][]> basicTiles;
        public KarkasonTileTemplate(){
            this.basicTiles = new ArrayList<>();
            this.basicTiles.addAll(List.of(
                    ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone(), ANGLE_TRACK.clone()
                    ,RIGHT_ANGLE_TRACK_CITY_SMALL.clone(), RIGHT_ANGLE_TRACK_CITY_SMALL.clone(), RIGHT_ANGLE_TRACK_CITY_SMALL.clone()
                    ,ANGLE_TRACK_CITY_BIG_SPECIAL.clone(), ANGLE_TRACK_CITY_BIG_SPECIAL.clone()
                    ,ALMOST_WHOLE_CITY_TRACK.clone()
                    ,ALMOST_WHOLE_CITY_SPECIAL.clone()
                    ,ANGLE_TRACK_CITY_BIG.clone(), ANGLE_TRACK_CITY_BIG.clone(), ANGLE_TRACK_CITY_BIG.clone()
                    ,DOUBLE_ANGLE_TRACK_CITY.clone(), DOUBLE_ANGLE_TRACK_CITY.clone(), DOUBLE_ANGLE_TRACK_CITY.clone()
                    ,STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone(), STRAIGHT_TRACK.clone()
                    ,DOUBLE_ANGLE_TRACK.clone(), DOUBLE_ANGLE_TRACK.clone(), DOUBLE_ANGLE_TRACK.clone(), DOUBLE_ANGLE_TRACK.clone()
                    ,CITY_SMALL.clone(), CITY_SMALL.clone(), CITY_SMALL.clone(), CITY_SMALL.clone(), CITY_SMALL.clone()
                    ,CITY_SMALL_DOUBLE.clone(), CITY_SMALL_DOUBLE.clone()
                    ,ALMOST_WHOLE_CITY.clone(), ALMOST_WHOLE_CITY.clone(), ALMOST_WHOLE_CITY.clone()
                    ,ABBEY.clone(), ABBEY.clone(), ABBEY.clone(), ABBEY.clone()
                    ,ABBEY_TRACK.clone(), ABBEY_TRACK.clone()
                    ,CITY_BIG.clone(), CITY_BIG.clone(), CITY_BIG.clone()
                    ,CITY_CORRIDOR_SPECIAL.clone(), CITY_CORRIDOR_SPECIAL.clone()
                    ,TRACK_CITY_SMALL.clone(), TRACK_CITY_SMALL.clone(), TRACK_CITY_SMALL.clone(), TRACK_CITY_SMALL.clone()
                    ,LEFT_ANGLE_TRACK_CITY_SMALL.clone(), LEFT_ANGLE_TRACK_CITY_SMALL.clone(), LEFT_ANGLE_TRACK_CITY_SMALL.clone()
                    ,CITY_CORRIDOR.clone()
                    ,ALMOST_WHOLE_CITY_TRACK_SPECIAL.clone(), ALMOST_WHOLE_CITY_TRACK_SPECIAL.clone()
                    ,WHOLE_CITY_SPECIAL.clone()
                    ,CROSSING_TRACK.clone()
                    ,CITY_BIG_SPECIAL.clone(), CITY_BIG_SPECIAL.clone()
                    ,FIELD_CORRIDOR.clone(), FIELD_CORRIDOR.clone(), FIELD_CORRIDOR.clone())
            );
        }



    /*
    f = field
    t = track
    c = city
    C = city wall
    a = abbey

    S = SPECIAL modifier (shield on city)
     */
    final char[][] CROSSING_TRACK =
            {{'.', 'f', 't', 'f', '.'},
            {'f', 'f', 't', 'f', 'f'},
            {'t', 't', 't', 't', 't'},
            {'f', 'f', 't', 'f', 'f'},
            {'.', 'f', 't', 'f', '.'}};

        final char[][] STRAIGHT_TRACK =
                {{'.', 'f', 'f', 'f', '.'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'t', 't', 't', 't', 't'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] ANGLE_TRACK =
                {{'.', 'f', 't', 'f', '.'},
                        {'f', 'f', 't', 'f', 'f'},
                        {'f', 'f', 't', 't', 't'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] DOUBLE_ANGLE_TRACK =
                {{'.', 'f', 't', 'f', '.'},
                        {'f', 'f', 't', 'f', 'f'},
                        {'t', 't', 't', 't', 't'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};


        //city tiles
        final char[][] WHOLE_CITY =
                {{'.', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'.', 'c', 'c', 'c', '.'}};
        final char[][] WHOLE_CITY_SPECIAL =
                {{'S', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'.', 'c', 'c', 'c', '.'}};

        final char[][] TRACK_CITY_SMALL =
                {{'.', 'C', 'c', 'C', '.'},
                        {'f', 'f', 'C', 'f', 'f'},
                        {'t', 't', 'f', 't', 't'},
                        {'f', 't', 't', 't', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] RIGHT_ANGLE_TRACK_CITY_SMALL =
                {{'.', 'C', 'c', 'C', '.'},
                        {'f', 'f', 'C', 'f', 'f'},
                        {'f', 'f', 'f', 't', 't'},
                        {'f', 'f', 't', 't', 'f'},
                        {'.', 'f', 't', 'f', '.'}};


        final char[][] LEFT_ANGLE_TRACK_CITY_SMALL =
                {{'.', 'C', 'c', 'C', '.'},
                        {'f', 'f', 'C', 'f', 'f'},
                        {'t', 't', 'f', 'f', 'f'},
                        {'f', 't', 't', 'f', 'f'},
                        {'.', 'f', 't', 'f', '.'}};

        final char[][] ANGLE_TRACK_CITY_BIG =
                {{'.', 'c', 'C', 'C', '.'},
                        {'c', 'C', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 't', 't'},
                        {'C', 'f', 't', 't', 'f'},
                        {'.', 'f', 't', 'f', '.'}};

        final char[][] ANGLE_TRACK_CITY_BIG_SPECIAL =
                {{'S', 'c', 'C', 'C', '.'},
                        {'c', 'C', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 't', 't'},
                        {'C', 'f', 't', 't', 'f'},
                        {'.', 'f', 't', 'f', '.'}};


        final char [][] ALMOST_WHOLE_CITY_TRACK =
                {{'.', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'C', 'C', 'C', 'c'},
                        {'C', 'f', 't', 'f', 'C'},
                        {'.', 'f', 't', 'f', '.'}};

        final char [][] ALMOST_WHOLE_CITY_TRACK_SPECIAL =
                {{'S', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'C', 'C', 'C', 'c'},
                        {'C', 'f', 't', 'f', 'C'},
                        {'.', 'f', 't', 'f', '.'}};

        final char[][] ALMOST_WHOLE_CITY =
                {{'.', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'C', 'C', 'C', 'c'},
                        {'C', 'f', 'f', 'f', 'C'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] ALMOST_WHOLE_CITY_SPECIAL =
                {{'S', 'c', 'c', 'c', '.'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'c', 'C', 'C', 'C', 'c'},
                        {'C', 'f', 'f', 'f', 'C'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] DOUBLE_ANGLE_TRACK_CITY =
                {{'.', 'C', 'C', 'C', '.'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'t', 't', 't', 't', 't'},
                        {'f', 'f', 't', 'f', 'f'},
                        {'.', 'f', 't', 'f', '.'}};

        final char[][] CITY_SMALL =
                {{'.', 'C', 'c', 'C', '.'},
                        {'f', 'f', 'C', 'f', 'f'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] CITY_SMALL_DOUBLE =
                {{'.', 'C', 'C', 'C', '.'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};


        final char[][] CITY_BIG =
                {{'.', 'c', 'C', 'C', '.'},
                        {'c', 'C', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] CITY_BIG_SPECIAL =
                {{'S', 'c', 'C', 'C', '.'},
                        {'c', 'C', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'C', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] CITY_CORRIDOR =
                {{'.', 'f', 'f', 'f', '.'},
                        {'C', 'C', 'C', 'C', 'C'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'C', 'C', 'C', 'C', 'C'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] CITY_CORRIDOR_SPECIAL =
                {{'S', 'f', 'f', 'f', '.'},
                        {'C', 'C', 'C', 'C', 'C'},
                        {'c', 'c', 'c', 'c', 'c'},
                        {'C', 'C', 'C', 'C', 'C'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] FIELD_CORRIDOR =
                {{'.', 'f', 'f', 'f', '.'},
                        {'C', 'f', 'f', 'f', 'C'},
                        {'c', 'C', 'f', 'C', 'c'},
                        {'C', 'f', 'f', 'f', 'C'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] ABBEY =
                {{'.', 'f', 'f', 'f', '.'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'f', 'f', 'a', 'f', 'f'},
                        {'f', 'f', 'f', 'f', 'f'},
                        {'.', 'f', 'f', 'f', '.'}};

        final char[][] ABBEY_TRACK =
                {{'.', 'f', 'f', 'f', '.'},
                {'f', 'f', 'f', 'f', 'f'},
                {'f', 'f', 'a', 'f', 'f'},
                {'f', 'f', 'f', 'f', 'f'},
                {'.', 'f', 'f', 'f', '.'}};

    }

}
