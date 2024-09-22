import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Member {
    private String name;
    private String email;

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}


public class LibraryManagementSystem extends JFrame {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private JTable bookTable;
    private JTable memberTable;

    public LibraryManagementSystem() {
        setTitle("Digital Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabs for Books and Members
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Books", createBookPanel());
        tabbedPane.addTab("Members", createMemberPanel());

        add(tabbedPane);
    }

    private JPanel createBookPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        bookTable = new JTable();
        DefaultTableModel bookTableModel = new DefaultTableModel(new Object[]{"Title", "Author", "ISBN", "Available"}, 0);
        bookTable.setModel(bookTableModel);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Book");
        JButton deleteButton = new JButton("Delete Book");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = JOptionPane.showInputDialog("Enter Book Title:");
                String author = JOptionPane.showInputDialog("Enter Author:");
                String isbn = JOptionPane.showInputDialog("Enter ISBN:");
                if (title != null && author != null && isbn != null) {
                    Book book = new Book(title, author, isbn);
                    books.add(book);
                    bookTableModel.addRow(new Object[]{book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable() ? "Yes" : "No"});
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    books.remove(selectedRow);
                    bookTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a book to delete.");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.add(new JScrollPane(bookTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createMemberPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        memberTable = new JTable();
        DefaultTableModel memberTableModel = new DefaultTableModel(new Object[]{"Name", "Email"}, 0);
        memberTable.setModel(memberTableModel);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Member");
        JButton deleteButton = new JButton("Delete Member");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Member Name:");
                String email = JOptionPane.showInputDialog("Enter Member Email:");
                if (name != null && email != null) {
                    Member member = new Member(name, email);
                    members.add(member);
                    memberTableModel.addRow(new Object[]{member.getName(), member.getEmail()});
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = memberTable.getSelectedRow();
                if (selectedRow != -1) {
                    members.remove(selectedRow);
                    memberTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a member to delete.");
                }
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.add(new JScrollPane(memberTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryManagementSystem().setVisible(true);
            }
        });
    }
}
