import Alert.UserAlert;
import Screen.HomeScreen;

public class Index {
    public static void main(String[] args) throws Exception {

        try {
            new HomeScreen();
        } catch (Exception e) {
            UserAlert userAlert = new UserAlert("ERRO - Erro ao criar Tela Inicial"); 
        }
    }
}
