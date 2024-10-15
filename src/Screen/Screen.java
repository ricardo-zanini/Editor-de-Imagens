package Screen;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

import Alert.UserAlert;


public class Screen extends JFrame{

    public Screen(String name)
    {
        screenConfig(name, new File("img/AppIcon.png"));
    }


    // Configurações gerais para todas as telas, recebe o nome da tela para a nomear
    private void screenConfig(String screenName, File screenIcon){
        setName(screenName);
        setTitle("");
        setBackground(Color.gray);
        setSize(600, 500);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        
        // Adiciona Icone ao cabeçalho da página
        try {
            setIconImage(ImageIO.read(screenIcon));
        } catch (Exception e) {
            UserAlert userAlert = new UserAlert("ERRO - Erro ao carregar imagem!"); 
        }
    }


    public boolean screenAlreadyExists(String screenName){
        Window[] allFrames = Window.getWindows();
        for(Window fr : allFrames){
            if(fr.getName().equals(screenName) && fr.isVisible()){
                return true;
            }
        }
        return false;
    }

}
