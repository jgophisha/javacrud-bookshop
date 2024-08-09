package javacrud;

import java.sql.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class javacrud {

    private JFrame frame;
    private JTextField txtbname;
    private JTextField txtprice;
    private JTextField txtedition;
    private JTable table;
    private JTextField txdid;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    javacrud window = new javacrud();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public javacrud() {
        initialize();
        Connect();
        table_load();
    }

    /**
     * Initialize the contents of the frame.
     */
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    public void Connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/cruddb", "root", "");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void table_load() {
        try {
            pst = con.prepareStatement("select * from book");
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 907, 678);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("BOOK SHOP");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
        lblNewLabel.setBounds(357, 46, 235, 49);
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "REGISTRATION", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        panel.setBounds(10, 118, 451, 322);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Book Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1.setBounds(40, 74, 133, 45);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Edition");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_1.setBounds(40, 154, 133, 45);
        panel.add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Price");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_1_2.setBounds(40, 236, 133, 45);
        panel.add(lblNewLabel_1_2);

        txtbname = new JTextField();
        txtbname.setBounds(181, 74, 261, 45);
        panel.add(txtbname);
        txtbname.setColumns(10);

        txtprice = new JTextField();
        txtprice.setColumns(10);
        txtprice.setBounds(181, 236, 261, 45);
        panel.add(txtprice);

        txtedition = new JTextField();
        txtedition.setColumns(10);
        txtedition.setBounds(181, 154, 261, 45);
        panel.add(txtedition);

        JButton btnNewButton = new JButton("SAVE");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bname, edition, price;
                bname = txtbname.getText();
                edition = txtedition.getText();
                price = txtprice.getText();

                try {
                    pst = con.prepareStatement("insert into book(name,edition,price) values(?,?,?)");
                    pst.setString(1, bname);
                    pst.setString(2, edition);
                    pst.setString(3, price);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added!");
                    table_load();

                    txtbname.setText("");
                    txtedition.setText("");
                    txtprice.setText("");
                    txtbname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnNewButton.setBounds(20, 437, 104, 49);
        frame.getContentPane().add(btnNewButton);

        JButton btnEdit = new JButton("EDIT");
        btnEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bname, edition, price, bid;

                bname = txtbname.getText();
                edition = txtedition.getText();
                price = txtprice.getText();
                bid = txdid.getText();

                try {
                    pst = con.prepareStatement("update book set name= ?, edition=?, price=? where id =?");
                    pst.setString(1, bname);
                    pst.setString(2, edition);
                    pst.setString(3, price);
                    pst.setString(4, bid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!");
                    table_load();

                    txtbname.setText("");
                    txtedition.setText("");
                    txtprice.setText("");
                    txtbname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnEdit.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnEdit.setBounds(179, 437, 104, 49);
        frame.getContentPane().add(btnEdit);

        JButton btnClear = new JButton("CLEAR");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtbname.setText("");
                txtedition.setText("");
                txtprice.setText("");
                txdid.setText("");
            }
        });
        btnClear.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnClear.setBounds(335, 437, 104, 49);
        frame.getContentPane().add(btnClear);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(482, 131, 401, 397);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "SEARCH", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        panel_1.setBounds(10, 526, 444, 77);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1_1_1 = new JLabel("BOOK ID");
        lblNewLabel_1_1_1.setBounds(34, 32, 82, 22);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel_1.add(lblNewLabel_1_1_1);

        txdid = new JTextField();
        txdid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String id = txdid.getText();

                    pst = con.prepareStatement("select name, edition, price from book where id = ?");
                    pst.setString(1, id);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        String name = rs.getString(1);
                        String edition = rs.getString(2);
                        String price = rs.getString(3);

                        txtbname.setText(name);
                        txtedition.setText(edition);
                        txtprice.setText(price);
                    } else {
                        txtbname.setText("");
                        txtedition.setText("");
                        txtprice.setText("");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        txdid.setBounds(150, 25, 284, 37);
        txdid.setColumns(10);
        panel_1.add(txdid);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String bid;
                bid = txdid.getText();

                try {
                    pst = con.prepareStatement("delete from book where id =?");
                    pst.setString(1, bid);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Deleted!");
                    table_load();

                    txtbname.setText("");
                    txtedition.setText("");
                    txtprice.setText("");
                    txdid.setText("");
                    txtbname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDelete.setBounds(684, 554, 188, 49);
        frame.getContentPane().add(btnDelete);
    }
}
