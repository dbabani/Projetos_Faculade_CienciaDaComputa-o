public class Domino {
    public final int a;
    public final int b;

    public Domino(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Domino flipped() {
        return new Domino(b, a);
    }

    @Override
    public String toString() {
        return "[" + a + "/" + b + "]";
    }
}