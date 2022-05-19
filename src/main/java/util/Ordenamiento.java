package util;

import java.util.ArrayList;
import logicadenegocios.*;

/**
 *
 * @author yahir
 */
public class Ordenamiento {
    public static void insercion(Comparable [] arreglop) {
        for (int i = 1; i < arreglop.length; i ++) {
            Comparable nuevo = arreglop [i] ;
            int j = i - 1;
            while (j >= 0 && ! arreglop [j].menorQue(nuevo)) {
                arreglop [j + 1] = arreglop [j];
                j --;
            }
            arreglop [j + 1] = nuevo;
        }
    }
}