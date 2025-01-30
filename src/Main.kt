import java.io.File

// Funcion que selecciona aleatoriamente o número de vitima entre as 1454
fun alea1454(): Int {
    val cifra = 0..1453
    var aleatorio = cifra.random()
    return aleatorio
    }

//Programa principal
fun main() {

    // Declaración de variables
    var intentos=7
    var vitima=0
    var vitimaList = mutableListOf<String>()
    val rm = ReproductorMidi("pugnodollari.mid")
    var letraUnha=""
    var letraMaiuscula=""
    var buscaletra=""
    var acertada=StringBuilder("")

    // Inicio do xogo, reconto de vítimas
    println("Cargando xogo...")
    val lines = File("vitimas.txt").readLines()
    println ("Cargando vitimas...")

    for (item in lines){
        vitima++
        print ("+")
        vitimaList.add(item)
    }
    println()
    Thread.sleep(20000) //la música tarda un ratito y por eso hacemos este sleep....

    // Selección da vítima
    val numero = alea1454()
    val palabra=vitimaList[numero]

    // Crea StringBuilder das vítima con *
    val longo=palabra.length
    for (item in palabra){
        acertada.append("*")
        }

    //Bucle mentras haxa intentos
    while (intentos>0) {

        //Pide unha letra
        println("Nome da vítima: $acertada  (${acertada.length} letras)")
        print("Introduce unha letra: ")
        var letra = readln()

        // Opera e devolve unha letra
        letraMaiuscula=letra.trim().uppercase().replace("Á","A").replace("É","E").replace("Í","I").replace("Ó","O").replace("Ú","U")
        letraUnha=letraMaiuscula[0].toString()

        //Busca a letra
        println(letraUnha)
        if (palabra.contains(letraUnha)){
            intentos++
            for (i in 0..palabra.length - 1) {
                buscaletra=palabra[i].toString()
                if (palabra[i].compareTo(letraUnha[0]) == 0) {
                    acertada[i] = letraUnha[0]
                }
            }
        }

        //Pausa dramática e debuxo aforcado
        Thread.sleep(10000)
        DibujoAhorcado.dibujar(8-intentos)
        intentos--

        //Obxectivo conseguido?
        if (palabra.compareTo(acertada.toString())==0) {
            println("Noraboa!! Salvaches a vida de $acertada")
            rm.cerrar() //si no cerramos el reproductor no acaba el main
            break
        }
    }
    //Fin dos intentos e morte da vitima
    if (intentos==0) {
        println("Asesinaches a $palabra")
        rm.cerrar() //si no cerramos el reproductor no acaba el main
        }
}