package br.fepi.si.app;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelasApp {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("financeiroPU");
		emf.close();
	}
}
