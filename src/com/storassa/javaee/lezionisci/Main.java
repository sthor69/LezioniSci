package com.storassa.javaee.lezionisci;

import java.awt.Component;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class Main implements Runnable {

	private List<Client> clients;
	private List<Lesson> lessons;
	
	EntityManagerFactory emf;
	
	private JFrame frame;
	
	private JMenuBar menuBar;

	private JMenu clientMenu;
	private JMenuItem clientInsertMenuItem;
	private JMenuItem clientModifyMenuItem;
	private JMenuItem clientRemoveMenuItem;

	private JMenu lessonMenu;
	private JMenuItem lessonInsertMenuItem;
	private JMenuItem lessonModifyMenuItem;

	private JMenu reportMenu;
	private JMenuItem reportView;
	
	public Main () {
		emf = Persistence.createEntityManagerFactory("LaralezioniSci");
		
		clients = retrieveStoredClients();
		lessons = retreiveStoredLessons();
	}
	
	private List<Client> retrieveStoredClients() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Client c");
		clients = query.getResultList();
		em.getTransaction().commit();

		return clients;
	}
	
	private List<Lesson> retreiveStoredLessons() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT l FROM Lesson l");
		lessons = query.getResultList();
		em.getTransaction().commit();

		return lessons;
	}
	
	public void run() {

		frame = new JFrame();

		menuBar = new JMenuBar();

		// build the clients menu
		clientMenu = new JMenu("Clienti");
		clientInsertMenuItem = new JMenuItem("Inserisci");
		clientInsertMenuItem.addActionListener(new InsertClientActionListener(this));
		clientModifyMenuItem = new JMenuItem("Modifica");
		clientModifyMenuItem.addActionListener(new ModifyClientActionListener(this));
		clientRemoveMenuItem = new JMenuItem("Elimina");
		clientRemoveMenuItem.addActionListener(new RemoveClientActionListener(this));
		clientMenu.add(clientInsertMenuItem);
		clientMenu.add(clientModifyMenuItem);
		clientMenu.add(clientRemoveMenuItem);

		// build the lessons menu
		lessonMenu = new JMenu("Lezioni");
		lessonInsertMenuItem = new JMenuItem("Inserisci");
		lessonInsertMenuItem.addActionListener(new InsertLessonActionListener(this));
		lessonModifyMenuItem = new JMenuItem("Modifica");
		lessonInsertMenuItem.addActionListener(new ModifyLessonActionListener(this));
		lessonMenu.add(lessonInsertMenuItem);
		lessonMenu.add(lessonModifyMenuItem);

		// build the lessons menu
		reportMenu = new JMenu("Report");
		reportView = new JMenuItem("Mostra");
		reportMenu.add(reportView);

		// add menus to menubar
		menuBar.add(clientMenu);
		menuBar.add(lessonMenu);
		menuBar.add(reportMenu);

		// put the menubar on the frame
		frame.setJMenuBar(menuBar);

		frame.setSize(800, 600);
		frame.setLocation(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public List<Client> getClients() {
		return clients;
	}

	public void insertClient(Client client) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(client);
		em.getTransaction().commit();
		
		clients.add(client);
	}
	
	public void insertLesson(Lesson lesson) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(lesson);
		em.getTransaction().commit();
		
		lessons.add(lesson);
	}

	public Client getClientFromFiscalCode(String fiscalCode) {
		Client client;
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("SELECT c FROM Client c WHERE c.fiscalCode = " + fiscalCode);
		client = (Client)query.getResultList().get(0);
		em.getTransaction().commit();

		return client;

	}
	
	public void removeClient (String fiscalCode) {
		
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		String q = "DELETE FROM Client c WHERE c.fiscalCode = " + "'" + fiscalCode + "'"; 
		Query query = em.createQuery(q);
		query.executeUpdate();
		em.getTransaction().commit();
		
		Client clientToRemove = null;
		for (Client client : clients) 
			if (client.getFiscalCode().equals(fiscalCode))
				clientToRemove = client;

		if (clientToRemove != null)
			clients.remove(clientToRemove);
	}
	
	public Component getFrame() {
		return frame;
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Main());
	}

}
