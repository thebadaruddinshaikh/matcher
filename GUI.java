import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class GUI {
    public static String[] labels = new String[0];
    public static void main(String[] args) {
        Match match = new Match();
        List<String> res = new ArrayList<>();
        JFrame frame = new JFrame("Matcher");
        //*******************Creating Components **********************
        //master file
        JLabel lb1 = new JLabel("Master File:");
        lb1.setBounds(10, 20, 100, 20);

        JLabel tf1 = new JLabel("No file Selected");
        tf1.setBounds(100, 19, 350, 20);

        JButton jb1 = new JButton("Add");
        jb1.setBounds(500, 20, 60, 20);

        JLabel shlb1 = new JLabel("Sheet : ");
        shlb1.setBounds(30,40, 80,20);
        JTextField shtf1 = new JTextField();
        shtf1.setBounds(90,40,40,20);

        JLabel collb1 = new JLabel("Column : ");
        collb1.setBounds(180,40, 80,20);
//
        JTextField coltf1 = new JTextField();
        coltf1.setBounds(250,40,40,20);

        //slave file
        JLabel lb2 = new JLabel("Slave File:");
        lb2.setBounds(10, 80, 100, 20);

        JLabel tf2 = new JLabel("No file Selected");
        tf2.setBounds(100, 80, 350, 20);

        JButton jb2 = new JButton("Add");
        jb2.setBounds(500, 80, 60, 20);

        JLabel shlb2 = new JLabel("Sheet : ");
        shlb2.setBounds(30,100, 80,20);
        JTextField shtf2 = new JTextField();
        shtf2.setBounds(90,100,40,20);

        JLabel collb2 = new JLabel("Column : ");
        collb2.setBounds(180,100, 80,20);
//
        JTextField coltf2 = new JTextField();
        coltf2.setBounds(250,100,40,20);


        JButton findMatch = new JButton("Find");
        findMatch.setBounds(250,150,70,30);

        //List of matches
//        JList list = new JList();
//        JScrollPane scrollPane = new JScrollPane();
        JList list = new JList(labels);
        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(20,200,150,100);
//        Container contentPane = frame.getContentPane();
//        contentPane.add(scrollPane);


        //************adding to Frame****************
        //master
        frame.add(jb1);
        frame.add(tf1);
        frame.add(lb1);
        frame.add(shlb1);
        frame.add(shtf1);
        frame.add(collb1);
        frame.add(coltf1);
        //slave
        frame.add(jb2);
        frame.add(tf2);
        frame.add(lb2);
        frame.add(shlb2);
        frame.add(shtf2);
        frame.add(collb2);
        frame.add(coltf2);

        //find match button
        frame.add(findMatch);

        //result
        frame.add(scrollPane);
        //frame.add(jp);

        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setVisible(true);


        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int openDialog = chooser.showOpenDialog(null);

                if (openDialog == JFileChooser.APPROVE_OPTION) {
                   tf1.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                int openDialog = chooser.showOpenDialog(null);

                if (openDialog == JFileChooser.APPROVE_OPTION) {
                    tf2.setText(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        findMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labels = new String[] {"badar","fuzel"};
                String master = tf1.getText();
                int sh1 = Integer.parseInt(shtf1.getText());
                int col1 = Integer.parseInt(coltf1.getText());

                String slave = tf2.getText();
                int sh2 = Integer.parseInt(shtf2.getText());
                int col2 = Integer.parseInt(coltf2.getText());

                HashSet<String> resSet = match.matchEx(master,slave,sh1,col1,sh2,col2);
                Iterator<String> itr = resSet.iterator();
                while(itr.hasNext()){
                    System.out.println(itr.next());
                }
            }
        });
    }
}
