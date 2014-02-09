package eu.liveandgov.wp1.data;

/**
 * Created by Lukas Härtel on 09.02.14.
 */
public final class Tuple<T, U> implements Comparable<Tuple<T, U>> {
    public final T left;

    public final U right;

    public Tuple(T left, U right) {
        this.left = left;
        this.right = right;
    }

    public static <T, U> Tuple<T, U> create(T left, U right){
        return new Tuple<T, U>(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple tuple = (Tuple) o;

        if (left != null ? !left.equals(tuple.left) : tuple.left != null) return false;
        if (right != null ? !right.equals(tuple.right) : tuple.right != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Tuple<T, U> o) {
        if (this == o) return 0;
        if (o == null) return -1;

        if (left instanceof Comparable) {
            final int pl = ((Comparable) left).compareTo(o.left);

            if (pl != 0) return pl;
        }

        if (right instanceof Comparable) {
            final int pr = ((Comparable) right).compareTo(o.right);

            if (pr != 0) return pr;
        }

        return 0;
    }

    public Tuple<U, T> flip() {
        return new Tuple<U, T>(right, left);
    }

    @Override
    public String toString() {
        return "(" + left + ", " + right + ")";
    }
}
