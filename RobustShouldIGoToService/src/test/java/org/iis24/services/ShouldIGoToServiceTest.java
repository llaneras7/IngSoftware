package org.iis24.services;
import org.junit.jupiter.api.*;
import java.time.LocalDate;
import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShouldIGoToServiceTest {
    private final int MAX_WS = 5;
    private final int MAX_MS = 5;

    LinkedList<IWeatherService> ws;
    LinkedList<IMapService> ms;
    ShouldIGoToService service;

    @BeforeEach
    void setUp(){
        ws = new LinkedList<>();
        for(int i = 1; i <= MAX_WS; i++){
            ws.add(mock(IWeatherService.class));
        }
        ms = new LinkedList<>();
        for(int i = 1; i <= MAX_MS; i++){
            ms.add(mock(IMapService.class));
        }
        service = new RobustShouldIGoToService(ms, ws);
    }
    @AfterEach
    void tearDown(){
        service = null;
        ws = null;
        ms = null;
    }
    @Test
    public void shouldRaiseInvalidServiceInstanceExceptionWhenListsContainNullReferences(){
        //Añado un servicio con valor nulo en la lista
        ms.set(3, null);

        //Assert
        assertThrows(InvalidServiceInstanceException.class, ()->service = new RobustShouldIGoToService(ms, ws));
    }
    @Test
    void shouldRaiseInvalidMapServiceExecutionExceptionIfMapServiceFails() {
        ms.set(1, null);

        //Assert
        assertThrows(InvalidMapServiceExecutionException.class, ()->service.shouldIGoTo("Nerja", LocalDate.now()));
    }
    @Test
    void shouldInvokeWeatherServiceWithValidCoordinatesIfOneMapServiceWorksWell(){
        service.
    }

}
