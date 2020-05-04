package programwindows;

import database.DatabaseHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LoginWindow {
    DatabaseHandler db;
    GraphicsObj ob;
    JPanel background;
    JPanel mainActivity;
    JPanel title;
    JPanel error;
    JLabel message;
    JPanel username;
    JLabel usernameLabel;
    JTextField usernameField;
    JPanel password;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JPanel buttonsField;
    JButton login;
    static int WIDTH = 300;
    Window win;

    public LoginWindow(){
        ob = new GraphicsObj();
        db = new DatabaseHandler();
        background = ob.makeWindow(0);

        mainActivity = new JPanel();
        mainActivity.setLayout(new BoxLayout(mainActivity,BoxLayout.Y_AXIS));
        mainActivity.setOpaque(false);

        title = ob.titleBar("Login");

        error = new JPanel();
        error.setPreferredSize(new Dimension(WIDTH, 20));
        error.setOpaque(false);

        message = new JLabel ("Enter Your Connection Details");
        message.setForeground(Color.WHITE);
        error.add(message);

        username = new JPanel();
        username.setPreferredSize(new Dimension(WIDTH, 30));
        username.setOpaque(false);

        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setPreferredSize(new Dimension(100, 30));

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 30));

        username.add(usernameLabel);
        username.add(usernameField);

        password = new JPanel();
        password.setPreferredSize(new Dimension(WIDTH, 30));
        password.setOpaque(false);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setPreferredSize(new Dimension(100, 30));

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));

        password.add(passwordLabel);
        password.add(passwordField);

        buttonsField = new JPanel();
        buttonsField.setPreferredSize(new Dimension(300, 30));
        buttonsField.setOpaque(false);

        login = ob.thisButton(30,50, "LOGIN");
        buttonsField.add(login);

        mainActivity.add(title);
        mainActivity.add(error);
        mainActivity.add(username);
        mainActivity.add(password);
        mainActivity.add(buttonsField);
        background.add(mainActivity);
        win = new Window(background, "Login", 0);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println(usernameField.getText());
                // System.out.println(new String(passwordField.getPassword()));
                /*TODO: I am assuming db.login will try to create the connection while logging in
                conn returns false if the connection has failed else true if it succeeds.
                This will work if it is implemented that way
                 */
                boolean conn = db.login(usernameField.getText(), new String(passwordField.getPassword()));
                if(conn){
                    new MainMenuWindow(db);
                    win.closeWindow();
                } else {
                    message.setText("WRONG CREDENTIALS");
                }
            }
        });

    }
}
