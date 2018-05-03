package funcional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;


public class Cofre {

    public static void main(String[] args) {
        Cofre programa = new Cofre();

        List<Integer> barra = new ArrayList<>(Arrays.asList(5, 8, 0, 5, 1));
        List<Integer> seq = new ArrayList<>(Arrays.asList(1, 4, 2, 5));

        System.out.println(programa.cofre(5, 4, barra, seq));
    }

    public List<Integer> cofre(int m, int n, List barra, List seq) {

        return recursao(n, n, barra, makeList(0,0), seq);
    }

    public List<Integer> recursao(int vI, int x, List barra, List ret, List seq) {
        if (x == vI) {
            return recursao(vI, x - 1, barra, myZipWith(somaUm(0, mai(seq), barra), ret), myRemove(seq, seq.get(0)));
        } else if (x > 1) {
            return recursao(vI, x - 1, barra, myZipWith(somaUm(men(seq), mai(seq), barra), ret), myRemove(seq, seq.get(0)));
        } else {
            return ret;
        }
    }

    public List<Integer> myZipWith(List l1, List l2) {
        return makeList(0, 1).stream()
                .map(x -> (int) l1.get(x) + (int) l2.get(x))
                .limit(10)
                .collect(Collectors.toList());
    }

    public int mai(List lst) {
        return (int) lst.get(0) < (int) lst.get(1) ? (int) lst.get(1) : (int) lst.get(0) - 1;
    }

    public int men(List lst) {
        return (int) lst.get(0) > (int) lst.get(1) ? (int) lst.get(1) - 1 : (int) lst.get(0);
    }

    public List<Integer> somaUm(int pos1, int pos2, List lst) {
        return each((List) lst.stream()
                .limit(pos2)
                .skip(pos1)
                .collect(Collectors.toList()));
    }

    public List<Integer> each(List lst) {
        return makeList(0, 1).stream()
                .map(x -> soma(x, lst))
                .limit(10)
                .collect(Collectors.toList());
    }

    public int soma(int x, List lst) {
        return (int) lst.stream()
                .filter(val -> x == (Integer) val)
                .count();
    }

    public List<Integer> makeList(int initial, int incremento) {
        return Stream.iterate(initial, i -> i + incremento)
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Integer> myRemove(List lst, Object val) {
        return lst.remove(val) ? lst : lst;
    }
}
