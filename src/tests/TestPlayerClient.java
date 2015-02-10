package tests;
import org.junit.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import quiz.PlayerClient;
import quiz.QuizServer;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Testing class for Player Client
 * @see quiz.PlayerClient
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPlayerClient {
    private PlayerClient player;

    @Mock
    QuizServer server;

    @BeforeClass
    public void buildUp(){
        player = new PlayerClient();
        server.createQuiz("test quiz");
        server.addQuestion(1, "What comes after A?","B","C","D","E");
        server.addQuestion(1, "What is 1+1?","2","3","4","5");
    }

    @Test
    public void testConnect(){
        assertTrue(player.connectServer("//localhost/"));


    }
    /*
    @Test
    public void testPlayQuiz(){
        String input = "B\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

    }*/
}
