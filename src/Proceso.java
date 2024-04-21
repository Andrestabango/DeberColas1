import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Proceso {
    String id;
    long cedula;
    int tiempoCpu;

    public Proceso(String id, long cedula, int tiempoCpu) {
        this.id = id;
        this.cedula = cedula;
        this.tiempoCpu = tiempoCpu;
    }

    public String toString() {
        return id + ", " + cedula + ", " + tiempoCpu + " ms";
    }
}

