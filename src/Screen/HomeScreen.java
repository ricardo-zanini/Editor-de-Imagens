package Screen;

import Content.HomeScreenContent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class HomeScreen extends Screen{

    private HomeScreenContent homeScreenContent;
    private JScrollPane jScrollPane;

    public HomeScreen(){
        super("Tela Inicial", 420, 650);

        setLocation(20, 20);
        
        createComponents();
        addComponents();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void createComponents() {
        setHomeScreenContent(new HomeScreenContent(0, 1500));
        setJScrollPane(new JScrollPane(homeScreenContent));
    }
    
    private void addComponents() {
        jScrollPane.setBounds(0, 0, 405, 615);
        jScrollPane.setBackground(Color.BLACK);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jScrollPane);
        
    }

    public HomeScreenContent getHomeScreenContent() {
        return homeScreenContent;
    }
    public void setHomeScreenContent(HomeScreenContent homeScreenContent) {
        this.homeScreenContent = homeScreenContent;
    }

    public JScrollPane getJScrollPane() {
        return jScrollPane;
    }
    public void setJScrollPane(JScrollPane jScrollPane) {
        this.jScrollPane = jScrollPane;
    }
}