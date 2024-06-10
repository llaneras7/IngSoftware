package org.iis2024.mayo2024;

import static org.iis2024.mayo2024.PEGIService.PEGI_CODE.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.iis2024.mayo2024.PEGIService.PEGI_CODE;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class OnlineGameServiceTest {
    OnlineGameService onlineGameService;
    @BeforeEach
    void setUp() {
        onlineGameService = new OnlineGameService();
    }
    @AfterEach
    void tearDown(){
        onlineGameService = null;
    }

    @Test
    void compruebaQueLaListaDeJuegosEsteVacia(){
        List<VideoGame> listGame = onlineGameService.getAllGames();
        assertTrue(listGame.isEmpty());
    }
    @Test
    void siSeAñadeUnJuegoLaListaTieneUnJuego(){
        onlineGameService.addGame(mock(VideoGame.class));
        List<VideoGame> listGame = onlineGameService.getAllGames();
        assertEquals(1, listGame.size());
    }
    @Test
    void cuandoSeBorraUnJuegoSeElevaExcepcion(){
        VideoGame videoGame = mock(VideoGame.class);
        when(videoGame.title()).thenReturn("Titulo");
        Exception exception = assertThrows(GameNotInTheServiceException.class, () ->  onlineGameService.removeGame(videoGame));
        assertEquals( "Game "+ videoGame.title() + " is not in the game service", exception.getMessage());
    }
    @Test
    void siAñadimosUnJuegoHayUnJuegoMas(){
        VideoGame videoGame = mock(VideoGame.class);
        onlineGameService.addGame(videoGame);
        List<VideoGame> listGames = onlineGameService.getAllGames();
        int nBefore = listGames.size();
        VideoGame videoGame2 = mock(VideoGame.class);
        onlineGameService.addGame(videoGame2);
        List<VideoGame> listGames2 = onlineGameService.getAllGames();
        int nAfter = listGames2.size();
        assertEquals(nBefore + 1, nAfter);
    }
    @Test
    void siHayDosJuegosYSeBorraUnoQueNoExisteElevaExcepcion(){
        VideoGame videoGame1 = mock(VideoGame.class);
        VideoGame videoGame2 = mock(VideoGame.class);
        VideoGame videoGame3 = mock(VideoGame.class);
        when(videoGame3.title()).thenReturn("Titulo");
        when(videoGame1.title()).thenReturn("Titulo1");
        when(videoGame2.title()).thenReturn("Titulo2");
        onlineGameService.addGame(videoGame1);
        onlineGameService.addGame(videoGame2);
        Exception exception = assertThrows(GameNotInTheServiceException.class,
                ()-> onlineGameService.removeGame(videoGame3));
        assertEquals("Game " + videoGame3.title() + " is not in the game service", exception.getMessage());
    }
    @Test
    void siTieneTresJuegosYSeInvocaplayGameSeElevaExcepcion(){
        VideoGame videoGame1 = mock(VideoGame.class);
        VideoGame videoGame2 = mock(VideoGame.class);
        VideoGame videoGame3 = mock(VideoGame.class);
        when(videoGame1.title()).thenReturn("Titulo1");
        when(videoGame2.title()).thenReturn("Titulo2");
        when(videoGame3.title()).thenReturn("Titulo3");
        onlineGameService.addGame(videoGame1);
        onlineGameService.addGame(videoGame2);
        onlineGameService.addGame(videoGame3);

        Player player = mock(Player.class);
        PEGIService pegiService = mock(PEGIService.class);

        Exception exception = assertThrows(GameNotInTheServiceException.class, () -> onlineGameService.playGame("titulo4", player, pegiService));
        assertEquals("Game titulo4 is not in the game service", exception.getMessage());

        verify(pegiService, times(0)).validateAge(PEGI3, 3);
    }
    @Test
    void penultimoTest(){
        VideoGame videoGame1 = mock(VideoGame.class);
        VideoGame videoGame2 = mock(VideoGame.class);
        when(videoGame1.title()).thenReturn("Titulo1");
        when(videoGame2.title()).thenReturn("Titulo2");
        onlineGameService.addGame(videoGame1);
        onlineGameService.addGame(videoGame2);

        when(videoGame1.pegiCode()).thenReturn(PEGI12);
        Player player = mock(Player.class);
        when(player.age()).thenReturn(10);
        PEGIService pegiService = mock(PEGIService.class);
        Exception exception = assertThrows(InvalidAgeForPlayingTheGameException.class, () -> onlineGameService.playGame("Titulo1", player, pegiService));
        assertEquals("The player's age is not valid to play the game", exception.getMessage());
        verify(pegiService, times(1)).validateAge(videoGame1.pegiCode(), player.age());
    }
    @Test
    void ultimoTest(){
        VideoGame videoGame1 = mock(VideoGame.class);
        VideoGame videoGame2 = mock(VideoGame.class);
        when(videoGame1.title()).thenReturn("Titulo1");
        when(videoGame2.title()).thenReturn("Titulo2");
        onlineGameService.addGame(videoGame1);
        onlineGameService.addGame(videoGame2);

        when(videoGame1.pegiCode()).thenReturn(PEGI18);
        Player player = mock(Player.class);
        when(player.age()).thenReturn(18);
        PEGIService pegiService = mock(PEGIService.class);
        when(pegiService.validateAge(PEGI18, 18)).thenReturn(true);

        VideoGame game = onlineGameService.playGame("Titulo1", player, pegiService);
        assertEquals(videoGame1, game);
        verify(pegiService, times(1)).validateAge(videoGame1.pegiCode(), player.age());
    }
}