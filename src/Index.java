import Alert.UserAlert;
import Screen.HomeScreen;

public class Index {
    public static void main(String[] args) throws Exception {

        try {
            new HomeScreen(); // Chama a primeira tela do programa com os componentes de edição de Imagem
        } catch (Exception e) {
            UserAlert userAlert = new UserAlert("ERRO - Erro ao criar Tela Inicial"); 
        }
    }
}
