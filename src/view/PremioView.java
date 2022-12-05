package src.view;

import java.util.ArrayList;

import src.Premio;

public class PremioView {

	private final int ganancia;
	private final int [] combinacion;
	
	public PremioView(Premio premio) {
		ArrayList<Integer> temp = premio.getCombinacion();
		ganancia = premio.getGanancia();
		
		
		combinacion = new int[temp.size()];
		for (int i = 0; i < temp.size(); i++)
		{
			combinacion[i] = temp.get(i);
		}
	}

	public int getGanancia() {
		return ganancia;
	}

	public int[] getCombinacion() {
		return combinacion;
	}

}
