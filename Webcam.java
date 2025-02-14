/** Creo la clase Webcam */

public class Webcam {

    /** Determino los atributos que tiene la clase Webcam: */

    private String resolucion; // Establezco la resolución como un String porque lo necesito en formato cadena "ancho x alto"
    private int fps; // Frecuencia de cuadros o fotogramas por segundo como número entero
    private boolean encendida; // Vamos a poder determinar si la webcam está encendida o no (booleano)
    private double tamanioEstimado; // Tamaño estimado del archivo en MB y tipo double

    /** Los pongo en private para encapsularlos, es decir, evitar que estos atributos se modifiquen y hago más seguro mi código */

    /** Creo el constructor de la clase Webcam:
     * Este inicializa la resolución, los FPS y establecerá la cámara como apagada por defecto:
     * @param resolucion: Resolución en formato "ancho x alto"
     * @param fps: Fotogramas por segundo
     */

    public Webcam(String resolucion, int fps) {
        this.resolucion = resolucion;
        this.fps = fps;
        this.encendida = false;  // La webcam va a estar apagada por defecto
        this.tamanioEstimado = calcularTamanio();
    }

    /** Implementación de getters y setters necesarios:
     * A través de un get obtenemos la resolución de la cámara 
     * */

    public String getResolucion() {
        return resolucion;
    }

    /** Con un set establecemos la nueva resolución y recalculamos el tamaño del archivo */

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
        this.tamanioEstimado = calcularTamanio();
    }

    /** Ahora, vamos a obtener los fotogramas por segundo de la webcam */

    public int getFps() {
        return fps;
    }

    /** Modificamos la cantidad de fps y recalculamos el tamaño estimado del archivo */

    public void setFps(int fps) {
        this.fps = fps;
        this.tamanioEstimado = calcularTamanio();
    }

    /** También, vamos a comprobar si la webcam está encendida o no:
     * Utilizo isEncendida como nombre al ser una variable booleana y esperar un true o false.
     */

    public boolean isEncendida() {
        return encendida;
    }

    /** Encendemos la cámara con el método encender(): */

    public void encender() {
        encendida = true;
        System.out.println("-- La webcam está encendida --");
    }

    /** Y apagamos la cámara con el método apagar() */

    public void apagar() {
        encendida = false;
        System.out.println("-- La webcam está apagada --");
    }

    /** Ahora, con el método tomarFoto(), en el caso de que la webcam esté apagada, mostramos un error; si está encendida, hacemos la foto. */

    public void tomarFoto() {
        if (!encendida) {
            System.out.println("¡Error! Enciende la webcam para hacer la foto");
            return;
        }
        System.out.println("La foto ha sido realizada con una resolución de " + resolucion + " y tamaño estimado de " + tamanioEstimado + " MB.");
    }

    //** Una vez hecho esto, calculamos el tamaño estimado del archivo de la iamgen basándonos en la resolución y los fps.  */ 

    private double calcularTamanio() {

        /** Guardo las dimensiones en un String y utilizo el método .split("x") para dividir ese string en dos partes, utilizando la "x" como separador */
        String[] dimensiones = resolucion.split("x");

        /** Con el método .parseInt convertimos las cadenas del string "1920" y "1080" en números enteros */
        int ancho = Integer.parseInt(dimensiones[0]);
        int alto = Integer.parseInt(dimensiones[1]);

        /** Para calcular el tamaño estimado del archivo, tenemos que muultiplicar el ancho, el alto y los fps. Finalmente lo dividimos entre 1000000 para pasarlo a megabytes */
        return (ancho * alto * fps) / 1000000.0;
    }

    /** Extras:
     * - Método para cambiar la resolución en tiempo de ejecución y recalcular el tamaño de la foto:
     */

    public void cambiarResolucion(String nuevaResolucion) {
        this.resolucion = nuevaResolucion;
        System.out.println("Resolución modificada a " + nuevaResolucion);

        /** Recalculamos el tamaño de la foto: */

        this.tamanioEstimado = calcularTamanio();
        System.out.println("El tamaño estimado ahora es de: " + tamanioEstimado + " MB");
    }

    /** Extra:
     * - Método para calcular el consumo de datos de una videollamada según la resolución, tiempo en segundos y FPS
     */

    public void calcularConsumoDatos(int tiempo) {
        String[] nuevasDimensiones = resolucion.split("x");
        int ancho = Integer.parseInt(nuevasDimensiones[0]);
        int alto = Integer.parseInt(nuevasDimensiones[1]);

        /**
         * Vamos a calcular el consumo de datos en MB:
         * Multiplicamos por 3 porque cada píxel en una imagen RGB usa aproximadamente 3 bytes (uno para cada color: rojo, verde y azul).
         * La división por 8000000 se utiliza como una aproximación a 1024 * 1024 * 3 (el número exacto de bytes por MB de una imagen en RGB)
         */

        double consumoPorSegundo = (ancho * alto * fps * 3 / 8000000.0);

        // Y ahora, el consumo total (multiplicamos por el tiempo en segundos de la videollamada)

        double consumoTotal = consumoPorSegundo * tiempo;
        System.out.println("El consumo total de datos en una videollamada de " + tiempo + " segundos, en una resolución de " + resolucion + " es de " + consumoTotal + " MB");
    }

    public static void main(String[] args) {

        /** Por último, probamos la clase en en el main con los datos dados en el README.md */

        Webcam webcam = new Webcam("1920x1080", 30);
        webcam.tomarFoto();
        webcam.encender();
        webcam.tomarFoto();
        webcam.apagar();

        /** Prueblo en el main las funcionalidades extras */

        System.out.println("\nExtra: Volvemos a encender, cambiamos la resolución, tomamos una foto y hacemos videollamada:\n");

        webcam.encender();
        webcam.cambiarResolucion("1280x720");
        webcam.tomarFoto();
        webcam.calcularConsumoDatos(120);
        webcam.apagar();
    }

}
