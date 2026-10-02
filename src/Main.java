import java.util.Scanner;
    public void main() {
        System.out.println("Bienvenido al tres en raya");
        char[][] cancha = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        Scanner scanner = new Scanner(System.in);

        int jugadorActual = sorteoDeDosJugadores();
        int jugadas = 0;

        System.out.println("Sorteo de jugadores...");
        System.out.println("Empieza el jugador " + jugadorActual);

        while (true) {
            dibujarCancha(cancha);

            char ficha = jugadorActual == 1 ? 'X' : 'O';
            System.out.println("Turno del jugador " + jugadorActual + " (" + ficha + ")");

            int fila = leerEntero(scanner, "Ingresa fila (1-3): ") - 1;
            int columna = leerEntero(scanner, "Ingresa columna (1-3): ") - 1;

            try {
                insertarValor(cancha, fila, columna, ficha);
            } catch (IllegalArgumentException e) {
                System.out.println("Movimiento invalido: " + e.getMessage());
                continue;
            }

            jugadas++;

            if (revisarSiHayGanador(cancha, ficha)) {
                dibujarCancha(cancha);
                System.out.println("Gano el jugador " + jugadorActual + " (" + ficha + ")");
                break;
            }

            if (jugadas == 9) {
                dibujarCancha(cancha);
                System.out.println("Empate");
                break;
            }

            jugadorActual = jugadorActual == 1 ? 2 : 1;
        }
    }

    public static void dibujarCancha(char[][] cancha) {
        for (int i = 0; i < cancha.length; i++) {
            System.out.print("|");
            for (int j = 0; j < cancha[i].length; j++) {
                System.out.print(cancha[i][j] + "|");
            }
            System.out.println();
        }
    }

    public static void insertarValor(char[][] cancha, int fila, int columna, char caracter) {
        if (fila < 0 || fila >= cancha.length || columna < 0 || columna >= cancha[0].length) {
            throw new IllegalArgumentException("fila o columna fuera de rango");
        }
        if (!revisarSiExisteValor(cancha, fila, columna)) {
            cancha[fila][columna] = caracter;
        } else {
            throw new IllegalArgumentException("la posicion ya esta ocupada");
        }
    }

    public static boolean revisarSiExisteValor(char[][] cancha, int fila, int columna) {
        return cancha[fila][columna] != ' ';
    }

    public static int sorteoDeDosJugadores() {
        return (int) (Math.random() * 2) + 1;
    }

    public static boolean revisarSiHayGanador(char[][] cancha, char caracter) {
        for (int i = 0; i < cancha.length; i++) {
            if (cancha[i][0] == caracter && cancha[i][1] == caracter && cancha[i][2] == caracter) {
                return true;
            }
        }

        for (int j = 0; j < cancha[0].length; j++) {
            if (cancha[0][j] == caracter && cancha[1][j] == caracter && cancha[2][j] == caracter) {
                return true;
            }
        }

        if (cancha[0][0] == caracter && cancha[1][1] == caracter && cancha[2][2] == caracter) {
            return true;
        }
        if (cancha[0][2] == caracter && cancha[1][1] == caracter && cancha[2][0] == caracter) {
            return true;
        }
        return false;
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Entrada invalida. Debes escribir un numero.");
            scanner.next();
        }
    }









