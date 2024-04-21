import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SimuladorRoundRobin {
    private static Queue<Proceso> colaProcesos = new LinkedList<>();
    private static int quantum = 35; // Valor por defecto

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Imprimir datos personales");
            System.out.println("2. Predefinir 6 procesos");
            System.out.println("3. Insertar nuevo proceso");
            System.out.println("4. Imprimir cola de procesos");
            System.out.println("5. Establecer valor del quantum");
            System.out.println("6. Ejecutar round-robin");
            System.out.println("0. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    imprimirDatosPersonales();
                    break;
                case 2:
                    predefinirProcesos();
                    break;
                case 3:
                    insertarProceso();
                    break;
                case 4:
                    imprimirCola();
                    break;
                case 5:
                    establecerQuantum();
                    break;
                case 6:
                    ejecutarRoundRobin();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (opcion != 0);
        scanner.close();
    }

    private static void imprimirDatosPersonales() {
        System.out.println("Nombre Completo: [Tu nombre completo]");
        System.out.println("Cédula: [Tu número de cédula]");
        System.out.println("Código Banner: [Tu código banner]");
    }

    private static void predefinirProcesos() {
        colaProcesos.clear(); // Limpiar la cola antes de agregar procesos predefinidos
        colaProcesos.add(new Proceso("P1", 1002856059, 100));
        colaProcesos.add(new Proceso("P2", 1714196743, 15));
        colaProcesos.add(new Proceso("P3", 1456756888, 40));
        colaProcesos.add(new Proceso("P4", 1234567890, 30));
        colaProcesos.add(new Proceso("P5", 987654321, 25));
        colaProcesos.add(new Proceso("P6", 246813579, 20));
        System.out.println("Procesos predefinidos agregados a la cola.");
    }

    private static void insertarProceso() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del proceso: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el número de cédula: ");
        long cedula = scanner.nextLong();
        System.out.print("Ingrese el tiempo de CPU requerido en ms: ");
        int tiempoCpu = scanner.nextInt();
        colaProcesos.add(new Proceso(id, cedula, tiempoCpu));
        System.out.println("Proceso insertado en la cola.");
    }

    private static void imprimirCola() {
        System.out.println("Cola de procesos:");
        for (Proceso proceso : colaProcesos) {
            System.out.println(proceso);
        }
    }

    private static void establecerQuantum() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el valor del quantum: ");
        quantum = scanner.nextInt();
        System.out.println("Quantum establecido: " + quantum + " ms");
    }

    private static void ejecutarRoundRobin() {
        int tiempoTotal = 0;
        int conmutaciones = 0;

        while (!colaProcesos.isEmpty()) {
            Proceso procesoActual = colaProcesos.poll(); // Obtener y remover el primer proceso de la cola
            System.out.println("Tiempo " + tiempoTotal + ": " + procesoActual.id + " entra a ejecución.");

            if (procesoActual.tiempoCpu <= quantum) {
                tiempoTotal += procesoActual.tiempoCpu;
                procesoActual.tiempoCpu = 0; // El proceso termina su ejecución
                System.out.println("Tiempo " + tiempoTotal + ": " + procesoActual.id + " termina su ejecución.");
            } else {
                tiempoTotal += quantum;
                procesoActual.tiempoCpu -= quantum;
                System.out.println("Tiempo " + tiempoTotal + ": " + procesoActual.id + " se conmuta. Pendiente por ejecutar " + procesoActual.tiempoCpu + " ms.");
                colaProcesos.add(procesoActual); // Volver a encolar el proceso al final
                conmutaciones++;
            }
        }

        System.out.println("\nESTADÍSTICAS GENERADAS:");
        System.out.println("Total tiempo de ejecución de todos los procesos: " + tiempoTotal + " ms");
        System.out.println("Total de conmutaciones: " + conmutaciones);
    }

}
