package Screen;
import Alert.UserAlert;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends JFrame{

    public Screen(String name, int width, int height)
    {
        screenConfig(name, new File("img/AppIcon.png"), width, height);
    }


    // Configurações Básicas de Telas
    // screenConfig(
    // - String screenName (nome da tela),
    // - File screenIcon (icone na toolbar da tela),
    // - int width (largura da tela),
    // - int height (altura da tla)
    // )
    private void screenConfig(String screenName, File screenIcon, int width, int height){

        setName(screenName);
        setTitle(screenName);

        setSize(width, height);
        setResizable(false);
        
        setLayout(null);
        getContentPane().setBackground(new Color(38,38,38));
        

        // Adiciona Icone na Toolbar da página
        try {
            setIconImage(ImageIO.read(screenIcon));
        } catch (Exception e) {
            UserAlert userAlert = new UserAlert("ERRO - Erro ao carregar imagem!"); 
        }
    }

    // Deleta tela com o nome enviado se a mesma existir
    // void deleteDuplicatedScreens(
    // - String screenName (nome da tela que se busca)
    // )
    public static void deleteDuplicatedScreens(String screenName){
        Window[] allFrames = Window.getWindows();
        for(Window fr : allFrames){
            if(fr.getName().equals(screenName) && fr.isVisible()){
                fr.dispose();
            }
        }
    }

    // Procura por tela enviada como parâmetro
    // screenAlreadyExists(
    // - String screenName
    // )
    public static boolean screenAlreadyExists(String screenName){
        Window[] allFrames = Window.getWindows();
        for(Window fr : allFrames){
            if(fr.getName().equals(screenName) && fr.isVisible()){
                return true;
            }
        }
        return false;
    }

}
