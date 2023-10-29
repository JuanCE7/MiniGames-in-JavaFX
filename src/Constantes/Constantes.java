package Constantes;

import javafx.scene.effect.BoxBlur;

public class Constantes {

    public static final String AUDIOERROR = "src/audios/button-error.mp3";
    public static final String AUDIOBOTON = "src/audios/button-click.mp3";
    public static final String AUDIOPERDER = "src/audios/sonido-perder.mp3";
    public static final String AUDIOGANAR = "src/audios/sonido-victoria.mp3";
    public static final String AUDIOACIERTO = "src/audios/acierto.mp3";
    
    public static final String ENCENDERBASE = "C:/xampp/xampp_start.exe";
    public static final String APAGARBASE = "C:/xampp/xampp_stop.exe";
    
    public static final String PACKVISTA = "/Vista";
    public static final String PACKIMAGENES = "/Imagenes";
    
    public static final String LOGIN = PACKVISTA + "/VistaLogin.fxml";
    public static final String MENU = PACKVISTA + "/VistaMenu.fxml";
    public static final String JUEGOAHORCADO = PACKVISTA + "/VistaJuegoAhorcado.fxml";
    public static final String JUEGOPARES = PACKVISTA + "/VistaJuegoPares.fxml";
    public static final String ADMINISTRARJUEGOS = PACKVISTA + "/VistaAdministrarJuegos.fxml";
    public static final String NIVEL = PACKVISTA + "/VistaNiveles.fxml";
    public static final String PRELOADER = PACKVISTA + "/VistaPreloader.fxml";
    
    public static final String FONDO = PACKIMAGENES + "/gaming.png";
    public static final String FONDO1 = PACKIMAGENES + "/gaming2.png";
    public static final String FONDOMENU = PACKIMAGENES + "/gamingMenu.png";
    
    public static final String AHORCADO = PACKIMAGENES + "/ahorcado.png";
    public static final String AHORCADOBRAZOIZQ = PACKIMAGENES + "/brazoIzquierdo.png";
    public static final String AHORCADOBRAZODER = PACKIMAGENES + "/brazoDerecho.png";
    public static final String AHORCADOPIERNAIZQ = PACKIMAGENES + "/piernaIzquierda.png";
    public static final String AHORCADOPIERNADER = PACKIMAGENES + "/piernaDerecha.png";
    public static final String AHORCADOCUERDA = PACKIMAGENES + "/cuerdaAhorcado.png";
    public static final String AHORCADOCUERPO = PACKIMAGENES + "/cuerpoAhorcado.png";
    public static final String AHORCADOCABEZA= PACKIMAGENES + "/cabezaAhorcado.png";
    public static final String PALOSAHORCADO = PACKIMAGENES + "/palosAhorcado.png";
    
    public static final String PISTA = PACKIMAGENES + "/icons8_army_star_64px_1.png";
    public static final String GIFINICIO = PACKIMAGENES + "/gifRedondo.gif";
    public static final String BACKCARDS = PACKIMAGENES + "/backgroundCards.png";
    
    public static final String GAMEOVER = PACKIMAGENES + "/DQS.gif";
    public static final String GAMEWIN = PACKIMAGENES + "/ganador.gif";
    
    public static final String PARES = PACKIMAGENES + "/pares.png";
    public static final String CORONA = PACKIMAGENES + "/icons8_crown_64px_2.png";
    public static final String PERSONA = PACKIMAGENES + "/icons8_customer_64px_4.png";
    public static final String SOBRE = PACKIMAGENES + "/icons8_envelope_24px_1.png";
    public static final String MANDO = PACKIMAGENES + "/icons8_game_controller_64px_2.png";
    public static final String LLAVE = PACKIMAGENES + "/icons8_key_24px_1.png";
    public static final String CANDADO = PACKIMAGENES + "/icons8_key_24px_1.png";
    public static final String PLAY = PACKIMAGENES + "/icons8_play_64px.png";
    public static final String TUERCA = PACKIMAGENES + "/icons8_settings_50px_1.png";
    public static final String USUARIO = PACKIMAGENES + "/icons8_user_24px_3.png";
    public static final String NOMBRE = PACKIMAGENES + "/icons8_male_user_50px_2.png";
    public static final String TROFEO = PACKIMAGENES + "/icons8_trophy_64px_2.png";
    public static final String TOP = PACKIMAGENES + "/icons8_leaderboard_64px_1.png";
    public static final String LOSED = PACKIMAGENES + "/icons8_dead_face_64px_1.png";
    public static final String VIDAS_AHORCADO_0 = PACKIMAGENES +"/v0.png";
    public static final String VIDAS_AHORCADO = PACKIMAGENES +"/v";
    
    public static final String MAS = PACKIMAGENES + "/more.png";
    public static final String LOGO = PACKIMAGENES + "/logo.png";
    
    public static final String IMG_INFORMACION = PACKIMAGENES + "/information.png";
    public static final String IMG_ERROR = PACKIMAGENES + "/error.png";
    public static final String IMG_EXITO = PACKIMAGENES + "/success.png";
    
    public static final String MENSAJE_AGREGADO = "Se ha agregado correctamente";
    public static final String MENSAJE_NO_AGREGADO = "No se ha agregado correctamente";
    public static final String MENSAJE_ERROR_CONEXION_MYSQL = "Error en la conexión";   
    public static final String MENSAJE_DATOS_INSUFICIENTES = "Datos insuficientes";
    public static final String CREDENCIAL_INVALIDA = "Credenciales invalidas";
    public static final String TAMANIO_LISTA = "Se necesita agregar más objetos";
    
    public static final BoxBlur EFECTO_BOX_BLUR = new BoxBlur(3, 3, 3);
    
    public static final String CAMPO_ERROR(String palabra,String motivo){
        return "Campo "+ palabra+" "+motivo;
    }
    
    public static final String LA_PISTA = "la palabra tiene que ver con ";    
    public static final String INTENTOS_RESTANTES = "Te quedan ";
    public static final String IMG_AHORCADO = PACKIMAGENES + "/patitoCaminando.gif";
    public static final String MENSAJE_DE_FRACASO = "Has fallado ";
    public static final String MENSAJE_DE_EXITO = "Lo has logrado ";
}
