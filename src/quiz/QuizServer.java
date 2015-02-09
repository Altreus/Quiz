package quiz;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Implementation of QuizService
 * @author skooni01
 * @see quiz.QuizService
 */
public class QuizServer implements QuizService {
    private ArrayList<Quiz> quizList;

    public QuizServer(){
        quizList = new ArrayList<>();
    }

    public static void main(String[] args) {
        QuizServer server = new QuizServer();
        server.launch();
    }

    public void launch(){
    }

    /**
     * Play a quiz
     * @param quizId - the id of the quiz to play
     * @return the score
     */
    @Override
    public int playQuiz(int quizId) {
        if (quizList.stream().noneMatch(q -> q.getId() == quizId)){
            System.out.println("Quiz not found. Please try again");
            return -1;
        }
        //TODO - check quiz id
        //TODO - player high scores
        Scanner inputScanner = new Scanner(System.in);
        Quiz thisQuiz = quizList.stream().filter(q -> q.getId() == quizId).findFirst().get();
        int score = 0;
        for (int i=0; i<thisQuiz.getQuestions().size(); i++){
            System.out.println("Question " + (i+1) + ": " + thisQuiz.getQuestions().get(i).getQuestion());   //print the question
            thisQuiz.getQuestions().get(i).getAnswerList().forEach(System.out::println);
            String input = inputScanner.nextLine();
            if (thisQuiz.answerQuestion(i+1,input) == true) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The answer is "+thisQuiz.getQuestions().get(i).getCorrectAnswer());
            }
        }
        System.out.println("At the end of the quiz, your score is " + score);
        return score;
    }

    /**
     * create a new quiz
     * @param quizName
     * @return the ID of the new quiz
     */
    @Override
    public int createQuiz(String quizName) {
        //TODO - check quizName not null
        Quiz newQuiz = new Quiz(quizName);
        quizList.add(newQuiz);
        return newQuiz.getId();
    }

    /**
     * Add a new question to the selected quiz
     * @param quizId the id of the quiz to add the new question to
     * @param question the new question
     * @param answers the four answers to be added
     */
    @Override
    public void addQuestion(int quizId, String question, String...answers){
        //TODO - check question/answers not null
        quizList.stream().filter(q -> q.getId() == quizId).forEach(q -> q.addQuestion(question, answers));
        //TODO - maybe return question number?

    }

    /**
     * Close the selected quiz so nobody else can play it
     * @param quizId - the id of the quiz to close
     */
    @Override
    public void closeQuiz(int quizId) {
        quizList.stream().filter(q -> q.getId() == quizId).forEach(q -> q.closeQuiz());
        //TODO - return high score

    }

    /**
     * Get the internal quiz list
     * @return the list of quizzes
     */
    public ArrayList<Quiz> getQuizList(){ return quizList;}
}
