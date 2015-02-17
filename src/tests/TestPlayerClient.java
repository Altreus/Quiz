package tests;
import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import clients.PlayerClient;
import resource.Player;
import server.QuizServer;
import service.QuizService;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;

/**
 * Testing class for Player Client
 * @see clients.PlayerClient
 */
@RunWith(MockitoJUnitRunner.class)
public class TestPlayerClient {
    private PlayerClient player;
    private static QuizService server;

    @BeforeClass
    public static void doFirst() throws RemoteException {
        server = new QuizServer();
        server.launch();
    }

    @Before
    public void buildUp(){
        player = new PlayerClient();
    }



    @Test
    public void testPlayQuiz(){
        player.launch();
        Player player1 = new Player("Alfred");
        String input = "what is the capital of France\nparis\nlondon\nrome\nbrussels\nY\nWhat is 1+1\n2\n3\n4\n5\nN";
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));
        try {
            server.createQuiz("test quiz");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        String answers = "paris\n2";
        System.setIn(new ByteArrayInputStream(answers.getBytes(StandardCharsets.UTF_8)));
        try {
            player.playQuiz(1, player1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            assertEquals(2,(int)server.getQuizList().get(0).getHighScore().getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
