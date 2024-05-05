package practicaMockito.practicaSensores;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class SensorTest {

    private GestorSensores gestorSensores;

    @BeforeEach
    public void setUp() {
        this.gestorSensores = new GestorSensores();
    }
    @Test
    public void inicialmenteElNumeroDeSensoresDelGestorEsCero() {
        int numeroDeSensores = gestorSensores.getNumeroSensores();
        assertTrue(numeroDeSensores == 0);
    }
    @Test
    public void siSeBorraUnSensorNoExistenteSeElevaExcepcion(){
        //ISensorTemperatura temperaturaMock = mock(ISensorTemperatura.class);
        Exception exception = assertThrows(SensorExcepcion.class, ()->gestorSensores.borrarSensor("Topo")); //Comprueba que salte una SensorException
        assertTrue(exception.getMessage().equals("Sensor Topo no existe"));
    }
    @Test
    public void siSeObtieneLaTemperaturaMediaEnUnGestorVacioSeElevaExcepcion(){
        Exception exception = assertThrows(SensorExcepcion.class, ()->gestorSensores.getTemperaturaMedia()); //Comprueba que salte una SensorException
        assertTrue(exception.getMessage().equals("Temperatura media no se puede calcular con 0 sensores"));
    }
    @Test
    public void siSeIntroduceUnSensorEnUnGestorLlenoSeElevaExcepcion(){
        //Inserto 100 mocks de sensores
        for (int i = 0; i<100; i++){
            ISensorTemperatura sensorMock = mock(ISensorTemperatura.class);
            gestorSensores.introducirSensor(sensorMock);
        }
        //Compruebo que tiene 100 sensores
        //Introduzco un mock mas y compruebo que lanza la excepción
        ISensorTemperatura sensorMock = mock(ISensorTemperatura.class);
        Exception exception = assertThrows(SensorExcepcion.class,  ()->gestorSensores.introducirSensor(sensorMock)); //Comprueba que salte una SensorException
        assertTrue(exception.getMessage().equals("No se puede introducir en un gestor de sensores lleno"));
    }
    @Test
    public void siSeBorraUnSensorDelGestorSeDecrementaEnUnoElNumeroDeSensores(){
        ISensorTemperatura sensorMock = mock(ISensorTemperatura.class);
        int numeroDeSensoresSinBorrar = gestorSensores.getNumeroSensores();
        gestorSensores.borrarSensor(sensorMock);
        int numeroDeSensoresBorrando = gestorSensores.getNumeroSensores();
        assertEquals(numeroDeSensoresBorrando, numeroDeSensoresSinBorrar - 1);
    }
    @Test
    public void siAlgunSensorTieneTemperaturaFueraDeRangoObtenerLaTemperaturaMediaElevaUnaExcepcion(){
        ISensorTemperatura sensor1 = mock(ISensorTemperatura.class);
        float temperaturaFueraDeRango = -120;
        //Indico al mock como tiene que actuar al invocar sus metodos
        when(sensor1.getTemperaturaCelsius()).thenReturn(temperaturaFueraDeRango);
        when(sensor1.disponible()).thenReturn(true);
        when(sensor1.getNombre()).thenReturn("sensor");
        //Introduzco el sensor en el gestor
        gestorSensores.introducirSensor(sensor1);
        //Llamo al metodo getTemperaturaMedia y debería lanzar una excepción
        Exception exception = assertThrows(SensorExcepcion.class, ()->gestorSensores.getTemperaturaMedia());
        assertEquals("error reading sensor "+sensor1.getNombre(), exception.getMessage());
    }
    @Test
    public void laTemperaturaMediaDeTresSensoresObtenidaATravesDelGestorEsCorrecta(){
        //Primer sensor
        ISensorTemperatura sensor1 = mock(ISensorTemperatura.class);
        float temperatura1 = 10;
        when(sensor1.getTemperaturaCelsius()).thenReturn(temperatura1);
        when(sensor1.disponible()).thenReturn(true);
        //Segundo sensor
        ISensorTemperatura sensor2 = mock(ISensorTemperatura.class);
        float temperatura2 = 20;
        when(sensor2.getTemperaturaCelsius()).thenReturn(temperatura2);
        when(sensor2.disponible()).thenReturn(true);
        //Tercer sensor
        ISensorTemperatura sensor3 = mock(ISensorTemperatura.class);
        float temperatura3 = 30;
        when(sensor3.getTemperaturaCelsius()).thenReturn(temperatura3);
        when(sensor3.disponible()).thenReturn(true);
        //Los introduzco al gestor
        gestorSensores.introducirSensor(sensor1);
        gestorSensores.introducirSensor(sensor2);
        gestorSensores.introducirSensor(sensor3);
        //Llamo al metodo getTemperaturaMedia y compruebo que sea correcto
        assertEquals(gestorSensores.getTemperaturaMedia(), 20);
    }
    @Test
    public void siSeContactaTresVecesConSensoresDisponiblesNoSeBorraNinguno(){
        ISensorTemperatura sensor = mock(ISensorTemperatura.class);
        when(sensor.disponible()).thenReturn(true);
        gestorSensores.introducirSensor(sensor);
        //Veo cuantos sensores tengo para comprobar luego si se ha borrado alguno
        int nSensoresBefore = gestorSensores.getNumeroSensores();
        //Contacto tres veces
        for(int i = 0; i < 3; i++){
            gestorSensores.contactarSensores();
        }
        //Guardo cuantos sensores hay ahora
        int nSensoresAfter = gestorSensores.getNumeroSensores();
        //Compruebo si hay los mismo sensores antes y ahora
        assertEquals(nSensoresBefore, nSensoresAfter);
    }
    @Test
    public void siSeContactaTresVecesConUnSensorNoDisponibleSeBorraDelGestor(){
        ISensorTemperatura sensor = mock(ISensorTemperatura.class);
        when(sensor.disponible()).thenReturn(false);
        gestorSensores.introducirSensor(sensor);
        //Veo cuantos sensores tengo para comprobar luego si se ha borrado alguno
        int nSensoresBefore = gestorSensores.getNumeroSensores();
        //Contacto tres veces
        for(int i = 0; i < 3; i++){
            gestorSensores.contactarSensores();
        }
        //Guardo cuantos sensores hay ahora
        int nSensoresAfter = gestorSensores.getNumeroSensores();
        //Compruebo si hay los mismo sensores antes y ahora
        assertEquals(nSensoresBefore - 1 , nSensoresAfter);
    }

}